package Machiavelli.Models;

import Machiavelli.Interfaces.Observers.PortemonneeOberserver;
import Machiavelli.Interfaces.Remotes.PortemonneeRemote;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * De portemonnee beheerd het geld van de speler. Via de portemonnee
 * kan de speler aan andere spelers of de bank betalen. Ook ontvangt
 * de speler via de portemonnee goud.
 *
 * @author Sander de Jong
 * @version 0.1
 *
 */
public class Portemonnee implements PortemonneeRemote, Serializable {
	// Variables
	private int goudMunten;
	private Bank bank;
	private ArrayList<PortemonneeOberserver> observers = new ArrayList<>();

	// Een portemonnee start met 2 goudmunten. Deze worden uit de bank gehaald
	public Portemonnee(Bank bank) {
		this.bank = bank;
		try {
			goudMunten += this.bank.gevenGoud(2);
		} catch (RemoteException re) {
			re.printStackTrace();
		}
	}

	// Goud aan de bank betalen
	public void bestedenGoud(Bank bank, int aantal) throws RemoteException {
		bank.ontvangenGoud(aantal);
		this.goudMunten -= aantal;
		notifyObservers();
	}

	// Ontvangen van een x aantal goud
	public void ontvangenGoud(int aantal) throws RemoteException {
		goudMunten += this.bank.gevenGoud(aantal);
        notifyObservers();
	}

	public int getGoudMunten() throws RemoteException {
		return this.goudMunten;
	}

	@Override
	public void addObserver(PortemonneeOberserver observer) throws RemoteException {
		observers.add(observer);
	}

	public void notifyObservers() throws RemoteException {
		for (PortemonneeOberserver observer: observers) {
			observer.modelChanged(this);
		}
	}

}