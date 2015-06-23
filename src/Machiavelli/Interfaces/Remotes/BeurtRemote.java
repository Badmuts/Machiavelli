package Machiavelli.Interfaces.Remotes;

import Machiavelli.Interfaces.Observers.BeurtObserver;
import Machiavelli.Models.Speler;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by badmuts on 18-6-15.
 */
public interface BeurtRemote extends Remote {

    public void geefBeurt(Speler speler) throws RemoteException;
    public Speler getSpeler() throws RemoteException;
    public void setSpeler(Speler speler) throws RemoteException;
    public void addObserver(BeurtObserver beurtObserver) throws RemoteException;
    public void notifyObservers() throws RemoteException;

}
