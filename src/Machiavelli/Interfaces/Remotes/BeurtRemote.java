package Machiavelli.Interfaces.Remotes;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Machiavelli.Interfaces.Observers.BeurtObserver;
import Machiavelli.Models.Speler;

public interface BeurtRemote extends Remote {
	/**
	 * 
	 * @param speler
	 */
	
	public void geefBeurt(Speler speler) throws RemoteException;
	public Speler getSpeler() throws RemoteException;
	public void setSpeler(Speler speler) throws RemoteException;
	public void addObserver(BeurtObserver beurtObserver) throws RemoteException;
	public void notifyObservers() throws RemoteException;

}
