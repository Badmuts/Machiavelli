package Machiavelli.Interfaces.Remotes;

import Machiavelli.Interfaces.Observers.HandObserver;
import Machiavelli.Models.Speler;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by badmuts on 18-6-15.
 */
public interface HandRemote extends Remote {

    /**
     * Voeg GebouwKaart toe aan Hand.
     *
     * @param kaart
     * @throws RemoteException
     */
    public void addGebouw(GebouwKaartRemote kaart) throws RemoteException;

    /**
     * Verwijder GebouwKaart uit Hand.
     *
     * @param gebouw
     * @throws RemoteException
     */
    public void removeGebouw(GebouwKaartRemote gebouw) throws RemoteException;

    /**
     * Voeg meerderen gebouwen toe aan Hand. (Redundant?)
     *
     * @param gebouwKaarten
     * @throws RemoteException
     */
    public void addGebouwen(List<GebouwKaartRemote> gebouwKaarten) throws RemoteException;

    /**
     * Haal kaarten op uit Hand.
     * @return
     * @throws RemoteException
     */
    public ArrayList<GebouwKaartRemote> getKaartenLijst() throws RemoteException;

    /**
     * Zet kaarten in Hand (Redundant?)
     * @param lijst
     * @throws RemoteException
     */
    public void setKaartenLijst(ArrayList<GebouwKaartRemote> lijst) throws RemoteException;

    /**
     * Haal eigenaar van Hand op.
     *
     * @return
     * @throws RemoteException
     */
    public Speler getSpeler() throws RemoteException;

    public void addObserver(HandObserver observer) throws RemoteException;

    public void notifyObservers() throws RemoteException;
}
