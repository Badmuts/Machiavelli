package Machiavelli.Interfaces.Remotes;

import Machiavelli.Interfaces.Observers.StadObserver;
import Machiavelli.Models.GebouwKaart;
import Machiavelli.Models.Speler;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by badmuts on 18-6-15.
 */
public interface StadRemote extends Remote {

    public void addGebouw(GebouwKaart gebouw) throws RemoteException;
    public void removeGebouw(GebouwKaart gebouw) throws RemoteException;
    public void addObserver(StadObserver observer) throws RemoteException;
    public void notifyObservers() throws RemoteException;
    public Speler getSpeler() throws RemoteException;
    public ArrayList<GebouwKaart> getGebouwen() throws RemoteException;
    public int getWaardeStad() throws RemoteException;

}
