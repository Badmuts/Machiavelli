package Machiavelli.Models;

import Machiavelli.Interfaces.Observers.PortemonneeOberserver;
import Machiavelli.Interfaces.Remotes.BankRemote;
import Machiavelli.Interfaces.Remotes.PortemonneeRemote;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * @author Sander de Jong
 * @version 0.1
 * 
 * De portemonnee beheerd het geld van de speler. Via de portemonnee
 * kan de speler aan andere spelers of de bank betalen. Ook ontvangt
 * de speler via de portemonnee goud.
 *
 */

public class Portemonnee extends UnicastRemoteObject implements PortemonneeRemote, Serializable {
	private int goudMunten;
	private BankRemote bank;
	private ArrayList<PortemonneeOberserver> observers = new ArrayList<>();


	/**
	 * Een portemonnee start met 2 goudmunten. Deze worden uit de bank gehaald.
	 * 
	 * @param bank
	 * @throws RemoteException
	 */
		public Portemonnee(BankRemote bank) throws RemoteException {
		super(1099);
		this.bank = bank;
		try {
			goudMunten += this.bank.gevenGoud(2);
		} catch (RemoteException re) {
			re.printStackTrace();
		}
	}

	// Goud aan de bank betalen
	public boolean bestedenGoud(BankRemote bank, int aantal) throws RemoteException {
		boolean kanBesteden;
		if(aantal <= this.goudMunten) {
			//genoeg goud in de portomonee.
			bank.ontvangenGoud(aantal);
			this.goudMunten -= aantal;
			kanBesteden = true;
			notifyObservers();
		} else {
			//niet genoeg goud in de portomonee.
			kanBesteden = false;
		}
		
		return kanBesteden;
	}

	/**
	 * Ontvangen van een x aantal goud in de portemonnee van de bank.
	 * 
	 * @param aantal hoeveelheid goudstukken
	 * @throws RemoteException
	 */ 
	public void ontvangenGoud(int aantal) throws RemoteException {
		goudMunten += this.bank.gevenGoud(aantal);
        notifyObservers();
	}

	public int getGoudMunten() throws RemoteException {
		return this.goudMunten;
	}

	public void addObserver(PortemonneeOberserver observer) throws RemoteException {
		observers.add(observer);
	}

	public void notifyObservers() throws RemoteException {
		for (PortemonneeOberserver observer: observers) {
			observer.modelChanged(this);
		}
	}

}
