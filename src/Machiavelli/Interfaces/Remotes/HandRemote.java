package Machiavelli.Interfaces.Remotes;

import Machiavelli.Interfaces.Observers.HandObserver;
import Machiavelli.Models.GebouwKaart;
import Machiavelli.Models.Speler;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by badmuts on 10-6-15.
 */
public interface HandRemote extends Remote {

    public void addGebouw(GebouwKaart kaart) throws RemoteException;
    public void removeGebouw(GebouwKaart gebouw) throws RemoteException;
    public ArrayList<GebouwKaart> getKaartenLijst() throws RemoteException;
    public void setKaartenLijst(ArrayList<GebouwKaart> lijst) throws RemoteException;
    public Speler getSpeler() throws RemoteException;
    public void addObserver(HandObserver handObserver) throws RemoteException;

}
