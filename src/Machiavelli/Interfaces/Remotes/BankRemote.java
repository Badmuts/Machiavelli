package Machiavelli.Interfaces.Remotes;

import Machiavelli.Interfaces.Observers.BankObserver;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by badmuts on 10-6-15.
 */
public interface BankRemote extends Remote {

    public void ontvangenGoud(int aantal) throws RemoteException;
    public int gevenGoud(int aantal) throws RemoteException;
    public int getGoudMunten() throws RemoteException;
    public void addObserver(BankObserver bankObserver) throws RemoteException;

}
