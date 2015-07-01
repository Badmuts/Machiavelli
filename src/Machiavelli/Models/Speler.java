package Machiavelli.Models;

import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Observers.SpelerObserver;
import Machiavelli.Interfaces.Remotes.*;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * @author Sander de Jong
 * 
 * De speler is onderdeel van een spel. De speler beheerd zijn portemonnee en
 * heeft een hand met kaarten. Bij het aanmaken van een speler wordt 
 * een nieuwe hand, portemonnee en stad aangemaakt. De speler kan vervolgens
 * verschillende acties uitvoeren.
 */
public class Speler extends UnicastRemoteObject implements SpelerRemote, Serializable {
	private PortemonneeRemote portemonnee;
	private Karakter karakter;
	private HandRemote hand;
	private SpelRemote spel;
	private StadRemote stad;
	private ArrayList<SpelerObserver> observers = new ArrayList<>();

	private int gebouwdeGebouwen = 0;
	private boolean eigenschapGebruikt = false;

	public Speler() throws RemoteException {
		super(1099);
	}

	/**
	 * Spel toevoegen aan de speler. Ook wordt de methode createStad aangeroepen
	 * voor het aanmaken van een stad.
	 * 
	 * @param spel
	 */
	public void addSpel(SpelRemote spel) {
		this.spel = spel;
		this.createStad();
	}
	
	/**
	 * Stad van de speler wordt aangemaakt. Ook wordt de methode createHand
	 * aangeroepen voor het aanmaken van de hand van de speler.
	 */
	private void createStad() {
		try {
			this.stad = new Stad(this);
			this.createHand();
		} catch (RemoteException re) {
			re.printStackTrace();
		}
	}
	
	/**
	 * Hand van de speler wordt aangemaakt. Ook wordt de methode createPortemonnee
	 * aangeroepen voor het aanmaken van de portemonnee van de speler.
	 */
	private void createHand() {
		try {
			this.hand = new Hand(this);
			this.createPortemonnee();
		} catch (RemoteException re) {
			re.printStackTrace();
		}
	}
	
	/**
	 * Portemonnee van de speler wordt aangemaakt.
	 */
	private void createPortemonnee() {
		try {
			this.portemonnee = new Portemonnee(this.spel.getBank());
		} catch (RemoteException re) {
			re.printStackTrace();
		}
	}

	/**
	 * Haalt goud van de bank en zet het in de portemonnee.
	 * 
	 * @param bank 
	 * @param aantal hoeveelheid goudstukken
	 * @throws RemoteException
	 */
	public void getGoudVanBank(BankRemote bank, int aantal) throws RemoteException {
		this.portemonnee.ontvangenGoud(aantal);
		notifyObservers();
	}

/** 
 * Haalt goud uit de portemonnee en geeft dit aan de bank.
 * 
 * @param portemonnee 
 * @param aantal hoeveelheid goudstukken
 * @throws RemoteException
 */
	// Haalt goud uit de portemonnee en geeft dit aan de bank
	public boolean setGoudOpBank(PortemonneeRemote portemonnee, int aantal) throws RemoteException {
		boolean kanBesteden = this.portemonnee.bestedenGoud(this.spel.getBank(), aantal);
        notifyObservers();
        return kanBesteden;
	}

	/**
	 * Plaatst een gebouwkaart in de stad van de speler.
	 *
	 * @param gebouw geselecteerde gebouwkaart in de hand van de speler.
	 * @return gebouwdeGebouwen
	 * @throws RemoteException
	 */
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

	/**
	 * Trekken van twee kaarten uit de stapel
	 * 
	 * @return templist
	 * @throws RemoteException
	 */
	public ArrayList<GebouwKaartRemote> trekkenKaart() throws RemoteException {
		ArrayList<GebouwKaartRemote> tempList = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			tempList.add(this.spel.getGebouwFactory().trekKaart());
		}
		return tempList;
	}

	/**
	 * Trekken van een x aantal kaarten uit de GebouwKaartFactory.
	 * 
	 * @param aantal hoeveelheid gebouwkaarten
	 * @throws RemoteException
	 * @return tempList 
	 */
	public ArrayList<GebouwKaartRemote> trekkenKaart(int aantal) throws RemoteException {
		ArrayList<GebouwKaartRemote> tempList = new ArrayList<>();
		for (int i = 0; i < aantal; i++) {
			tempList.add(this.spel.getGebouwFactory().trekKaart());
		}
		return tempList;
	}

	/**
	 * Selecteren van een kaart aan de hand van de getrokken kaarten.
	 * 
	 * @param lijst 
	 * @param index
	 * @throws RemoteException
	 */
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
		try {
			this.karakter = karakter;
			notifyObservers();
		} catch(Exception e) {
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

	public int getGebouwdeGebouwen() throws RemoteException {
		return gebouwdeGebouwen;
	}

	public void setGebouwdeGebouwen(int gebouwdeGebouwen) {
		this.gebouwdeGebouwen = gebouwdeGebouwen;
	}

	public boolean EigenschapGebruikt() throws RemoteException {
		return eigenschapGebruikt;
	}

	public void setEigenschapGebruikt(boolean eigenschapGebruikt) throws RemoteException {
		this.eigenschapGebruikt = eigenschapGebruikt;
		notifyObservers();
	}

	public void addObserver(SpelerObserver observer) throws RemoteException {
		observers.add(observer);
	}

	public void notifyObservers() throws RemoteException {
		for (SpelerObserver observer: observers) {
			observer.modelChanged(this);
		}
	}
}
