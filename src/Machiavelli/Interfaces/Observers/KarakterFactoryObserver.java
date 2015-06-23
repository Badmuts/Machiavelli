package Machiavelli.Interfaces.Observers;

import Machiavelli.Factories.KarakterFactory;
import Machiavelli.Interfaces.Remotes.KarakterFactoryRemote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by badmuts on 10-6-15.
 */
public interface KarakterFactoryObserver extends Remote {

    void modelChanged(KarakterFactoryRemote karakterFactory) throws RemoteException;

}
