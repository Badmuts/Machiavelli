package Machiavelli.Interfaces.Observers;

import server.GamesRemote;

import java.rmi.RemoteException;

public interface GameObserver {

    public void modelChanged(GamesRemote games) throws RemoteException;

}
