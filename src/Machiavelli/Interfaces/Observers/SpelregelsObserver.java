package Machiavelli.Interfaces.Observers;

import Machiavelli.Models.Spelregels;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by badmuts on 10-6-15.
 */
public interface SpelregelsObserver extends Remote {

    void modelChanged(Spelregels spelregels) throws RemoteException;

}
