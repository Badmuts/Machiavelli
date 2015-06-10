package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Machiavelli.Models.Speler;

public interface Beurtable extends Remote {
	/**
	 * 
	 * @param speler
	 */
	
	public void geefBeurt(Speler speler) throws RemoteException;
	public Speler getSpeler() throws RemoteException;
	public void setSpeler(Speler speler) throws RemoteException;
}
