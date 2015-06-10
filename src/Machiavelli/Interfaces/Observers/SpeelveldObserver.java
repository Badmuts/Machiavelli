package Machiavelli.Interfaces.Observers;

import Machiavelli.Interfaces.Remotes.SpeelveldRemote;
import Machiavelli.Models.Speelveld;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SpeelveldObserver extends Remote {

	/**
	 *
	 * @param speelveld model is changed.
	 * @throws RemoteException
	 */
	public void modelChanged(SpeelveldRemote speelveld) throws RemoteException;
}

