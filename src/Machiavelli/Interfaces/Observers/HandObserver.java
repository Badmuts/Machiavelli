package Machiavelli.Interfaces.Observers;

import Machiavelli.Models.Hand;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by badmuts on 10-6-15.
 */
public interface HandObserver extends Remote {

    void modelChanged(Hand hand) throws RemoteException;

}
