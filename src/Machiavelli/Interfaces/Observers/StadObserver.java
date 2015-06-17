package Machiavelli.Interfaces.Observers;

import Machiavelli.Models.Stad;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by badmuts on 10-6-15.
 */
public interface StadObserver extends Remote {

    void modelChanged(Stad stad) throws RemoteException;

}
