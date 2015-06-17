package Machiavelli.Interfaces.Observers;

import Machiavelli.Models.Bank;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by badmuts on 10-6-15.
 */
public interface BankObserver extends Remote {

    void modelChanged(Bank bank) throws RemoteException;

}
