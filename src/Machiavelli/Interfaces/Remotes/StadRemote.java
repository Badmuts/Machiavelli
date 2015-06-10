package Machiavelli.Interfaces.Remotes;

import Machiavelli.Models.GebouwKaart;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by badmuts on 10-6-15.
 */
public interface StadRemote extends Remote {

    public ArrayList<GebouwKaart> getGebouwen() throws RemoteException;
    public void addGebouw(GebouwKaart gebouw) throws RemoteException;
    public void removeGebouw(GebouwKaart gebouw) throws RemoteException;
    public int getWaardeStad() throws RemoteException;

}
