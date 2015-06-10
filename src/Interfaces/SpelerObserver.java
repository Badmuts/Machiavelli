package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Models.Speler;

public interface SpelerObserver extends Remote{
	
	/**
	 * 
	 * @param speler model changed.
	 * @throws RemoteException
	 */
	public void modelChanged(Speler speler) throws RemoteException;

}
