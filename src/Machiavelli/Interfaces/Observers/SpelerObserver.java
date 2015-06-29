package Machiavelli.Interfaces.Observers;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Models.Speler;

public interface SpelerObserver extends Remote{
	
	/**
	 * 
	 * @param speler model changed.
	 * @throws RemoteException
	 */
	void modelChanged(SpelerRemote speler) throws RemoteException;

}
