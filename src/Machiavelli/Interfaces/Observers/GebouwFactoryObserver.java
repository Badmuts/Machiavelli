package Machiavelli.Interfaces.Observers;

import Machiavelli.Factories.GebouwFactory;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by badmuts on 10-6-15.
 */
public interface GebouwFactoryObserver extends Remote {

    void modelChanged(GebouwFactory gebouwFactory) throws RemoteException;

}
