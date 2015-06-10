package Machiavelli.Observers;

import Machiavelli.Models.Speelveld;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SpeelVeldObserver extends Remote {

    void modelChanged(Speelveld speelveld) throws RemoteException;

}
