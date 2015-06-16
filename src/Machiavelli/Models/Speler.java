package Machiavelli.Models;

import Machiavelli.Controllers.SpeelveldController;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Observers.SpelerObserver;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;

import java.io.Serializable;
import java.rmi.RemoteException;
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
public class Speler implements SpelerRemote, Serializable {
	// Variables
	private Portemonnee portemonnee;
	private Karakter karakter;
	private Hand hand;
	private SpelRemote spel;
	private Stad stad;
	private ArrayList<SpelerObserver> observers = new ArrayList<>();
	private SpeelveldController speelveldController;

	// Speler toewijzen aan spel en een nieuwe portemonnee, hand en stad maken.
	public Speler() {

	}

	// Haalt goud van de bank en zet het in de portemonnee
	public void getGoudVanBank(Bank bank, int aantal) throws RemoteException {
		this.portemonnee.ontvangenGoud(aantal);
        notifyObservers();
	}

	// Haalt goud uit de portemonnee en geeft dit aan de bank
	public void setGoudOpBank(Portemonnee portemonnee, int aantal) throws RemoteException {
		this.portemonnee.bestedenGoud(this.spel.getBank(), aantal);
        notifyObservers();
	}

	// Plaats een gebouwkaart in de stad van de speler
	public void bouwenGebouw(GebouwKaart gebouw) throws RemoteException {
		this.stad.addGebouw(gebouw);
		this.hand.removeGebouw(gebouw);
        notifyObservers();
	}

	// Trekken van twee kaarten uit de stapel
	public ArrayList<GebouwKaart> trekkenKaart() throws RemoteException {
		ArrayList<GebouwKaart> tempList = new ArrayList<GebouwKaart>();
		for (int i = 0; i < 2; i++)
		{
			tempList.add(this.spel.getGebouwFactory().trekKaart());
		}
		return tempList;
	}

	// Trekken van een x aantal kaarten van de stapel
	public ArrayList<GebouwKaart> trekkenKaart(int aantal) throws RemoteException {
		ArrayList<GebouwKaart>tempList = new ArrayList<GebouwKaart>();
		for (int i = 0; i < aantal; i++)
		{
			tempList.add(this.spel.getGebouwFactory().trekKaart());
		}
		return tempList;
	}

	// Selecteren van een kaart aan de hand van de getrokken kaarten
	public void selecterenKaart(ArrayList<GebouwKaart> lijst, int index) throws RemoteException {
		this.getHand().addGebouw(lijst.get(index));
		lijst.remove(index);
		this.getSpel().getGebouwFactory().addGebouw(lijst.get(0));
        notifyObservers();
	}

	public Karakter getKarakter() throws RemoteException {
		return this.karakter;
	}
	
	public void setKarakter(Karakter karakter) throws RemoteException {
		this.karakter = karakter;
        notifyObservers();
	}

	public SpelRemote getSpel() throws RemoteException {
		return this.spel;
	}

	public Portemonnee getPortemonnee() throws RemoteException {
		return this.portemonnee;
	}

	public Hand getHand() throws RemoteException {
		return this.hand;
	}

	public Stad getStad() throws RemoteException {
		return this.stad;
	}

	@Override
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
		this.stad = new Stad(this);
		this.createHand();
    }

    private void createHand() {
		this.hand = new Hand(this);
		this.createPortemonnee();
    }

    private void createPortemonnee() {
        try {
            this.portemonnee = new Portemonnee(this.spel.getBank());
            this.speelveldController = new SpeelveldController(this.spel, this);
        } catch (RemoteException re) {
            System.out.print(re);
        }
    }
}