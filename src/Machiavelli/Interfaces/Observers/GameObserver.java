package Machiavelli.Interfaces.Observers;

import server.GamesRemote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameObserver extends Remote {

    void modelChanged(GamesRemote games) throws RemoteException;

}
