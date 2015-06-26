package Machiavelli.Models;

import Machiavelli.Controllers.BeurtController;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Observers.SpelerObserver;
import Machiavelli.Interfaces.Remotes.*;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * De speler is onderdeel van een spel. De speler beheerd zijn portemonnee en
 * heeft een hand met kaarten. Bij het aanmaken van een speler krijgt deze
 * een nieuwe hand, portemonnee en stad toegewezen. De speler kan vervolgens
 * verschillende acties uitvoeren.
 *
 * @author Sander de Jong
 * @version 0.1
 *
 */
public class Speler extends UnicastRemoteObject implements SpelerRemote, Serializable {
	// Variables
	private PortemonneeRemote portemonnee;
	private Karakter karakter;
	private HandRemote hand;
	private SpelRemote spel;
	private StadRemote stad;
	//private BeurtRemote beurt;
	private ArrayList<SpelerObserver> observers = new ArrayList<>();
    private int gebouwdeGebouwen = 0;
    private boolean eigenschapGebruikt = false;

	// Speler toewijzen aan spel en een nieuwe portemonnee, hand en stad maken.
	public Speler() throws RemoteException {

	}

	// Haalt goud van de bank en zet het in de portemonnee
	public void getGoudVanBank(BankRemote bank, int aantal) throws RemoteException {
		this.portemonnee.ontvangenGoud(aantal);
        notifyObservers();
	}

	// Haalt goud uit de portemonnee en geeft dit aan de bank
	public void setGoudOpBank(PortemonneeRemote portemonnee, int aantal) throws RemoteException {
		this.portemonnee.bestedenGoud(this.spel.getBank(), aantal);
        notifyObservers();
	}

	// Plaats een gebouwkaart in de stad van de speler
	public void bouwenGebouw(GebouwKaartRemote gebouw) throws RemoteException {
        // Haal bouwlimiet op van karakter
        int bouwLimiet = this.karakter.getBouwLimiet();
		int kosten = gebouw.getKosten();
        int saldo = portemonnee.getGoudMunten();
        if ((saldo-kosten) >= 0 && gebouwdeGebouwen < bouwLimiet) {
            this.stad.addGebouw(gebouw);
            this.hand.removeGebouw(gebouw);
            this.portemonnee.bestedenGoud(this.spel.getBank(), kosten);
            gebouw.setStad(this.stad);
            gebouwdeGebouwen++;
            notifyObservers();
        }
	}

	// Trekken van twee kaarten uit de stapel
	public ArrayList<GebouwKaartRemote> trekkenKaart() throws RemoteException {
		ArrayList<GebouwKaartRemote> tempList = new ArrayList<>();
		for (int i = 0; i < 2; i++)
		{
			tempList.add(this.spel.getGebouwFactory().trekKaart());
		}
		return tempList;
	}

	// Trekken van een x aantal kaarten van de stapel
	public ArrayList<GebouwKaartRemote> trekkenKaart(int aantal) throws RemoteException {
		ArrayList<GebouwKaartRemote>tempList = new ArrayList<>();
		for (int i = 0; i < aantal; i++)
		{
			tempList.add(this.spel.getGebouwFactory().trekKaart());
		}
		return tempList;
	}

	// Selecteren van een kaart aan de hand van de getrokken kaarten
	public void selecterenKaart(ArrayList<GebouwKaartRemote> lijst, int index) throws RemoteException {
		this.getHand().addGebouw(lijst.get(index));
		lijst.remove(index);
		this.getSpel().getGebouwFactory().addGebouw(lijst.get(0));
        notifyObservers();
	}

	public Karakter getKarakter() throws RemoteException {
		return this.karakter;
	}
	
	public void setKarakter(Karakter karakter) {
		try
		{
			this.karakter = karakter;
	        notifyObservers();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public SpelRemote getSpel() throws RemoteException {
		return this.spel;
	}

	public PortemonneeRemote getPortemonnee() throws RemoteException {
		return this.portemonnee;
	}

	public HandRemote getHand() throws RemoteException {
		return this.hand;
	}

	public StadRemote getStad() throws RemoteException {
		return this.stad;
	}
	
	public void addObserver(SpelerObserver observer) throws RemoteException {
		observers.add(observer);
	}

	public void notifyObservers() throws RemoteException {
		for (SpelerObserver observer: observers) {
			observer.modelChanged(this);
		}
	}

    public void addSpel(SpelRemote spel) {
        this.spel = spel;
        this.createStad();
    }

    private void createStad() {
		try {
			this.stad = new Stad(this);
			this.createHand();
		} catch (RemoteException re) {
			re.printStackTrace();
		}
	}

    private void createHand() {
        try {
            this.hand = new Hand(this);
		    this.createPortemonnee();
        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }

    private void createPortemonnee() {
        try {
            this.portemonnee = new Portemonnee(this.spel.getBank());
        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }
    
    public int getGebouwdeGebouwen() throws RemoteException {
        return gebouwdeGebouwen;
    }
    
    public boolean EigenschapGebruikt() throws RemoteException {
    	return eigenschapGebruikt;
    }
    
    public void setEigenschapGebruikt() throws RemoteException {
    	this.eigenschapGebruikt = true;
    	
    }
}
