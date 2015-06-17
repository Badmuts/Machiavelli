package Machiavelli.Interfaces.Remotes;

import Machiavelli.Factories.GebouwFactory;
import Machiavelli.Interfaces.Observers.SpelObserver;
import Machiavelli.Models.Bank;
import Machiavelli.Models.Speler;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by badmuts on 10-6-15.
 */
public interface SpelRemote extends Remote {

//    public void nieuwSpel() throws RemoteException;
Bank getBank() throws RemoteException;
    GebouwFactory getGebouwFactory() throws RemoteException;
    int getAantalSpelers() throws RemoteException;
    void addObserver(SpelObserver spelObserver) throws RemoteException;
    void removeObserver(SpelObserver observer) throws RemoteException;
    void notifyObservers() throws RemoteException;
    void addSpeler(Speler speler) throws RemoteException;
    void createNewSpel(int maxAantalSpelers) throws RemoteException;
}
