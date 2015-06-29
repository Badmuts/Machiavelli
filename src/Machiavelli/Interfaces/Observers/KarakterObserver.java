package Machiavelli.Interfaces.Observers;

import Machiavelli.Interfaces.Karakter;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by badmuts on 18-6-15.
 */
public interface KarakterObserver extends Remote {

    public void modelChanged(Karakter karakter) throws RemoteException;

}
