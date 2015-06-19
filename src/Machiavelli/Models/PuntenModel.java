package Machiavelli.Models;

import Machiavelli.Interfaces.Observers.PuntenObserver;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Hier wordt bepaald wie de winnaar is van het spel.
 *
 * @author Bernd Oostrum
 * @version 0.1
 *
 */
public class PuntenModel implements Serializable {

	private Speler winnaar;
	private ArrayList<PuntenObserver> observers = new ArrayList<>();
	private Spel spel;

	public PuntenModel(Spel spel)
	{
		this.spel = spel;
	}

	public void berekenWinnaar() throws RemoteException {
		;
		for (int i = 0;  i < this.spel.getSpelers().size(); i++) {
			if (winnaar.getStad().getWaardeStad())
			winnaar = this.spel.getSpelers().get(i);

		}
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