package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Machiavelli.Models.Speelveld;

public interface SpeelveldObserver extends Remote {
	
	/**
	 * 
	 * @param speelveld model is changed.
	 * @throws RemoteException
	 */
	public void modelChanged(Speelveld speelveld) throws RemoteException;
}
