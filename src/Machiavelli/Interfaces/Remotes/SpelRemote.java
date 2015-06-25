package Machiavelli.Interfaces.Remotes;

import Machiavelli.Factories.GebouwFactory;
import Machiavelli.Factories.KarakterFactory;
import Machiavelli.Interfaces.Observers.SpelObserver;
import Machiavelli.Models.Bank;
import Machiavelli.Models.Speler;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by badmuts on 10-6-15.
 */
public interface SpelRemote extends Remote {

    int getAantalSpelers() throws RemoteException;
    void addObserver(SpelObserver spelObserver) throws RemoteException;
    void removeObserver(SpelObserver observer) throws RemoteException;
    void notifyObservers() throws RemoteException;
    void addSpeler(SpelerRemote speler) throws RemoteException;
    void createNewSpel(int maxAantalSpelers) throws RemoteException;
    ArrayList<SpelerRemote> getSpelers() throws RemoteException;
    int getMaxAantalSpelers() throws RemoteException;
    BankRemote getBank() throws RemoteException;
    GebouwFactoryRemote getGebouwFactory() throws RemoteException;
    void createNewSpeler() throws RemoteException;
    KarakterFactory getKarakterFactory() throws RemoteException;
    void opslaanSpel() throws RemoteException;
    SpelRemote ladenSpel() throws RemoteException;
    File createSaveLocation() throws RemoteException;
}
