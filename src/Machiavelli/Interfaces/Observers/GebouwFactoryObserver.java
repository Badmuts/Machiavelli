package Machiavelli.Interfaces.Observers;

import Machiavelli.Interfaces.Remotes.GebouwFactoryRemote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by badmuts on 10-6-15.
 */
public interface GebouwFactoryObserver extends Remote {

    public void modelChanged(GebouwFactoryRemote gebouwFactory) throws RemoteException;

}
