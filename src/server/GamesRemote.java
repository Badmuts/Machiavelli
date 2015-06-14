package server;

import Machiavelli.Interfaces.Observers.GameObserver;
import Machiavelli.Interfaces.Remotes.SpelRemote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface GamesRemote extends Remote {

    public ArrayList<SpelRemote> getGames() throws RemoteException;
    public void addSpelToGames(SpelRemote spel) throws RemoteException;
    public void addObserver(GameObserver gameObserver) throws RemoteException;
    public void notifyObservers() throws RemoteException;

}
