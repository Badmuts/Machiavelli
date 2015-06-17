package Machiavelli.Interfaces.Observers;

import Machiavelli.Models.PuntenModel;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by badmuts on 10-6-15.
 */
public interface PuntenObserver extends Remote {

    void modelChanged(PuntenModel punten) throws RemoteException;

}
