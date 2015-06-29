
package Machiavelli.Models;

import Machiavelli.Interfaces.Observers.BankObserver;
import Machiavelli.Interfaces.Remotes.BankRemote;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Dit is de bank van het spel. Per spel is er 1 bank aanwezig die het geld beheerd.
 * Spelers kunnen via de portemonnee geld van de bank halen en geld aan de bank geven.
 *
 * @author Sander de Jong
 * @version 0.1
 *
 */
public class Bank extends UnicastRemoteObject implements BankRemote, Serializable {
	// Variables
	private int goudMunten;
	private ArrayList<BankObserver> observers = new ArrayList<>();

	// De bank begint met 30 goudmunten
	public Bank() throws RemoteException {
//		super(1099);
		this.goudMunten = 30;
	}

	public void addObserver(BankObserver bankObserver) throws RemoteException {
		observers.add(bankObserver);
	}

	public void notifyObservers() throws RemoteException {
		for (BankObserver observer: observers) {
			observer.modelChanged(this);
		}
	}

	// De bank ontvangt een x aantal goud
	public void ontvangenGoud(int aantal) throws RemoteException{
		this.goudMunten += aantal;
		notifyObservers();
	}

	// De bank geeft een x aantal goud en haalt het van het totaal af
	public int gevenGoud(int aantal) throws RemoteException {
		this.goudMunten -= aantal;
		notifyObservers();
		return aantal;
	}

	public int getGoudMunten() throws RemoteException {
		return this.goudMunten;
	}
}
