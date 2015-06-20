package Machiavelli.Interfaces.Remotes;

import Machiavelli.Interfaces.Observers.SpeelveldObserver;
import Machiavelli.Models.Speler;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by badmuts on 18-6-15.
 */
public interface SpeelveldRemote extends Remote {

    public void setKoning(SpelerRemote spelers) throws RemoteException;
    public void toonKarakterLijst() throws RemoteException;
    public void addObserver(SpeelveldObserver observer) throws RemoteException;
    public void notifyObservers() throws RemoteException;
    public SpelerRemote getSpeler() throws RemoteException;
    public void addSpeler(SpelerRemote speler) throws RemoteException;

}
