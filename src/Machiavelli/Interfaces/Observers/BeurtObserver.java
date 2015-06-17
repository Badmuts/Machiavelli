package Machiavelli.Interfaces.Observers;

import Machiavelli.Models.Beurt;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by badmuts on 10-6-15.
 */
public interface BeurtObserver extends Remote {

    void modelChanged(Beurt beurt) throws RemoteException;

}
