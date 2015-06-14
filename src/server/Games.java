package server;

import Machiavelli.Interfaces.Observers.GameObserver;
import Machiavelli.Interfaces.Remotes.SpelRemote;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Deze class heeft alle spellen die op de server draaien.
 */
public class Games implements GamesRemote {

    private ArrayList<SpelRemote> games = new ArrayList<>();
    private ArrayList<GameObserver> observers = new ArrayList<>();

    @Override
    public ArrayList<SpelRemote> getGames() throws RemoteException {
        return this.games;
    }

    @Override
    public void addSpelToGames(SpelRemote spel) throws RemoteException {
        this.games.add(spel);
    }

    @Override
    public void addObserver(GameObserver gameObserver) throws RemoteException {
        observers.add(gameObserver);
    }

    @Override
    public void notifyObservers() throws RemoteException {
        for (GameObserver observer: observers) {
            observer.modelChanged(this);
        }
    }


}
