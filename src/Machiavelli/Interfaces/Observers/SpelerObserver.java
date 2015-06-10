package Machiavelli.Interfaces.Observers;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Machiavelli.Models.Speler;

public interface SpelerObserver extends Remote{
	
	/**
	 * 
	 * @param speler model changed.
	 * @throws RemoteException
	 */
	public void modelChanged(Speler speler) throws RemoteException;

}