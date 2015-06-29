package Machiavelli.Models;

import Machiavelli.Interfaces.Observers.PuntenObserver;
import Machiavelli.Interfaces.Remotes.PuntenRemote;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * @author Bernd Oostrum
 * 
 * Hier wordt bepaald wie de winnaar is van het spel.
 *
 * 
 * 
 *
 */
public class PuntenModel extends UnicastRemoteObject implements PuntenRemote, Serializable {

	private Speler winnaar;
	private ArrayList<PuntenObserver> observers = new ArrayList<>();

	protected PuntenModel() throws RemoteException {
		super(1099);
	}

	public Speler berekenWinnaar() throws RemoteException {
		return this.winnaar;
	}

	public void addObserver(PuntenObserver observer) throws RemoteException {
		observers.add(observer);
	}

	public void notifyObservers() throws RemoteException {
		for (PuntenObserver observer: observers) {
			observer.modelChanged(this);
		}
	}
}