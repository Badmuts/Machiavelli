package Machiavelli.Interfaces.Observers;

import Machiavelli.Models.Speelveld;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SpeelveldObserver extends Remote {

	/**
	 *
	 * @param speelveld model is changed.
	 * @throws RemoteException
	 */
	void modelChanged(Speelveld speelveld) throws RemoteException;
}

