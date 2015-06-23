package Machiavelli.Interfaces.Remotes;

import Machiavelli.Interfaces.Observers.PuntenObserver;
import Machiavelli.Models.Speler;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by badmuts on 18-6-15.
 */
public interface PuntenRemote extends Remote {

    public Speler berekenWinnaar() throws RemoteException;
    public void addObserver(PuntenObserver observer) throws RemoteException;
    public void notifyObservers() throws RemoteException;

}
