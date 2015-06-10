package Machiavelli.Interfaces.Observers;

import Machiavelli.Interfaces.Remotes.BankRemote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by badmuts on 10-6-15.
 */
public interface BankObserver extends Remote {

    public void modelChanged(BankRemote bank) throws RemoteException;

}
