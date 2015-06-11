package Machiavelli.Interfaces.Remotes;

import Machiavelli.Factories.GebouwFactory;
import Machiavelli.Interfaces.Observers.SpelObserver;
import Machiavelli.Models.Bank;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by badmuts on 10-6-15.
 */
public interface SpelRemote extends Remote {

    public void nieuwSpel() throws RemoteException;
    public Bank getBank() throws RemoteException;
    public GebouwFactory getGebouwFactory() throws RemoteException;
    public int getAantalSpelers() throws RemoteException;
    public void addObserver(SpelObserver spelObserver) throws RemoteException;
    public void removeObserver(SpelObserver observer) throws RemoteException;
    public void notifyObservers() throws RemoteException;

}
