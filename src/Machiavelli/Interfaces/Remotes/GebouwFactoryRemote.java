package Machiavelli.Interfaces.Remotes;

import Machiavelli.Models.GebouwKaart;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by badmuts on 10-6-15.
 */
public interface GebouwFactoryRemote extends Remote {

    public void addGebouw(GebouwKaart gebouw) throws RemoteException;
    public GebouwKaart trekKaart() throws RemoteException;
    public ArrayList<GebouwKaart> getGebouwen() throws RemoteException;

}
