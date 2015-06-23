package Machiavelli.Interfaces.Remotes;

import Machiavelli.Interfaces.Observers.GebouwFactoryObserver;
import Machiavelli.Models.GebouwKaart;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by badmuts on 18-6-15.
 */
public interface GebouwFactoryRemote extends Remote {

    public void addGebouw(GebouwKaart gebouw) throws RemoteException;
    public GebouwKaartRemote trekKaart() throws RemoteException;
    public ArrayList<GebouwKaart> getGebouwen() throws RemoteException;
    public void addObserver(GebouwFactoryObserver gebouwFactoryObserver) throws RemoteException;
    public void notifyObservers() throws RemoteException;

}
