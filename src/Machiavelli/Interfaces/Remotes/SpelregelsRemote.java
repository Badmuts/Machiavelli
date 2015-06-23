package Machiavelli.Interfaces.Remotes;

import Machiavelli.Interfaces.Observers.SpelregelsObserver;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by badmuts on 18-6-15.
 */
public interface SpelregelsRemote extends Remote {

    public String getSpelregels() throws IOException, RemoteException;
    public void addObserver(SpelregelsObserver observer) throws RemoteException;
    public void notifyObservers() throws RemoteException;

}
