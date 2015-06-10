package Machiavelli.Interfaces.Observers;

import Machiavelli.Interfaces.Remotes.GebouwKaartRemote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by badmuts on 10-6-15.
 */
public interface GebouwKaartObserver extends Remote {

    public void modelChanged(GebouwKaartRemote gebouwKaart) throws RemoteException;

}
