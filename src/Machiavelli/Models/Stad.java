package Machiavelli.Models;

import Machiavelli.Interfaces.Observers.StadObserver;
import Machiavelli.Interfaces.Remotes.GebouwKaartRemote;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Interfaces.Remotes.StadRemote;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by daanrosbergen on 03/06/15.
 * 
 * Elke speler heeft een eigen stad. Deze stad bestaat uit een ArrayList van gebouwKaarten.
 * In deze stad kunnen gebouwKaarten geplaatst worden. De condotierre kan een gebouw verwijderen.
 * Het spel stopt als een speler 8 of meer kaarten in zijn stad heeft. Dan wordt aan de hand
 * van de waarde van de gebouwen in de stad van de speler de score berekend.
 */

public class Stad extends UnicastRemoteObject implements StadRemote, Serializable {
	private SpelerRemote speler;
	private ArrayList<GebouwKaartRemote> gebouwen = new ArrayList<>();
	private SpelRemote spel;
	private int waardeStad;
	private ArrayList<StadObserver> observers = new ArrayList<>();

	public Stad(SpelerRemote speler) throws RemoteException {
		super(1099);
		this.speler = speler;
		this.spel = this.speler.getSpel();
	}

	/**
	 * GebouwKaart in de stad van de speler plaatsen.
	 * 
	 * @param gebouw wordt toegevoegd aan de stad arraylist.
	 * @throws RemoteException
	 */
	public void addGebouw(GebouwKaartRemote gebouw) throws RemoteException {
		this.gebouwen.add(gebouw);
        notifyObservers();
	}
	
	/**
	 * GebouwKaart uit de Arraylist gebouwen verwijderen. De gebouwkaart wordt onderaan
	 * de gebouwkaart factory geplaatst. Alleen als een speler het karakter 
	 * Condotierre is kan deze methode aangeroepen worden door middel van gebruikEigenschap.
	 * 
	 * @param target wordt gebruikt door de Condotierre
	 * @throws RemoteException
	 */
	public void removeGebouw(GebouwKaartRemote target) throws RemoteException {
		this.gebouwen.remove(target);
		this.spel.getGebouwFactory().addGebouw(target);
        notifyObservers();
	}
	
	/**
	 * Aan het einde van het spel wordt de score berekend aan de hand van
	 * de waarde van de gebouwen in de stad van de speler.
	 * 
	 * @param lijst de steden in een stad.
	 * @return waarde
	 * @throws RemoteException
	 */
	private void berekenWaarde(ArrayList<GebouwKaartRemote> lijst) throws RemoteException {
		int waarde = 0;
		for(int i = 0; i < lijst.size(); i++) {
			waarde += lijst.get(i).getKosten();
		}
		this.waardeStad = waarde;
		notifyObservers();
	}
	
	public ArrayList<GebouwKaartRemote> getGebouwen() throws RemoteException {
		return this.gebouwen;
	}
	
	public int getWaardeStad() throws RemoteException {
		berekenWaarde(gebouwen);
		return this.waardeStad;
	}
	
	public SpelerRemote getSpeler() throws RemoteException {
		return speler;
	}
	
	public void addObserver(StadObserver observer) throws RemoteException {
		observers.add(observer);
        System.out.println("Stad observer added! Observers: " + observers.size());
	}

	public void notifyObservers() throws RemoteException {
        System.out.println("Stad model changed! Observers: " + observers.size());
        System.out.println("Gebouwen in stad: " + gebouwen.size());
		for (StadObserver observer: observers) {
			observer.modelChanged(this);
		}
	}
}
