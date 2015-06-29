package Machiavelli.Interfaces.Remotes;

import Machiavelli.Interfaces.Observers.BankObserver;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BankRemote extends Remote {

    public void addObserver(BankObserver bankObserver) throws RemoteException;
    public void notifyObservers() throws RemoteException;
    public void ontvangenGoud(int aantal) throws RemoteException;
    public int gevenGoud(int aantal) throws RemoteException;
    public int getGoudMunten() throws RemoteException;

}
