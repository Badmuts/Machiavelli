package Machiavelli.Interfaces.Remotes;

import Machiavelli.Interfaces.Observers.GebouwFactoryObserver;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by badmuts on 18-6-15.
 */
public interface GebouwFactoryRemote extends Remote {

    public void addGebouw(GebouwKaartRemote gebouw) throws RemoteException;
    public GebouwKaartRemote trekKaart() throws RemoteException;
    public ArrayList<GebouwKaartRemote> getGebouwen() throws RemoteException;
    public void addObserver(GebouwFactoryObserver gebouwFactoryObserver) throws RemoteException;
    public void notifyObservers() throws RemoteException;

}
