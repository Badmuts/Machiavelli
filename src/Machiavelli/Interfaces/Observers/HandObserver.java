package Machiavelli.Interfaces.Observers;

import Machiavelli.Interfaces.Remotes.HandRemote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by badmuts on 10-6-15.
 */
public interface HandObserver extends Remote {

    public void modelChanged(HandRemote hand) throws RemoteException;

}
