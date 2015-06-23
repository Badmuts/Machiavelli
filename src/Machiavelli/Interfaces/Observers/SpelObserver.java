package Machiavelli.Interfaces.Observers;

import Machiavelli.Interfaces.Remotes.SpelRemote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by badmuts on 10-6-15.
 */
public interface SpelObserver extends Remote {

    void modelChanged(SpelRemote spel) throws RemoteException;

}
