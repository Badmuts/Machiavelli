package Machiavelli.Interfaces.Observers;

import Machiavelli.Interfaces.Remotes.SpelregelsRemote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by badmuts on 10-6-15.
 */
public interface SpelregelsObserver extends Remote {

    public void modelChanged(SpelregelsRemote spelregels) throws RemoteException;

}
