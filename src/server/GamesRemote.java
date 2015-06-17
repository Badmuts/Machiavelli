package server;

import Machiavelli.Interfaces.Observers.GameObserver;
import Machiavelli.Interfaces.Remotes.SpelRemote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface GamesRemote extends Remote {

    ArrayList<SpelRemote> getGames() throws RemoteException;
    void addSpelToGames(SpelRemote spel) throws RemoteException;
    void addObserver(GameObserver gameObserver) throws RemoteException;
    void notifyObservers() throws RemoteException;
    SpelRemote getSpel(SpelRemote spel) throws RemoteException;

}
