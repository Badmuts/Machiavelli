package Machiavelli.Models;

import Machiavelli.Interfaces.Observers.PuntenObserver;
import Machiavelli.Interfaces.Remotes.PuntenRemote;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Hier wordt bepaald wie de winnaar is van het spel.
 *
 * @author Bernd Oostrum
 * @version 0.1
 *
 */
public class PuntenModel implements PuntenRemote {

	private Speler winnaar;
	private ArrayList<PuntenObserver> observers = new ArrayList<>();
	
	public Speler berekenWinnaar() throws RemoteException {
		return this.winnaar;
	}

	@Override
	public void addObserver(PuntenObserver observer) throws RemoteException {
		observers.add(observer);
	}

	public void notifyObservers() throws RemoteException {
		for (PuntenObserver observer: observers) {
			observer.modelChanged(this);
		}
	}
}