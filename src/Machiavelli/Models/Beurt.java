package Machiavelli.Models;

import Machiavelli.Interfaces.Observers.BankObserver;
import Machiavelli.Interfaces.Observers.BeurtObserver;
import Machiavelli.Interfaces.Remotes.BeurtRemote;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Sander de Jong
 *
 */
public class Beurt implements BeurtRemote {
    // Variables
    private Spel spel;
    private ArrayList<Speler> spelerLijst;
    private Speler speler;
    private ArrayList<BeurtObserver> observers = new ArrayList<>();

    public Beurt(Spel spel, ArrayList<Speler> spelerLijst)
    {
        this.spel = spel;
        this.spelerLijst = spelerLijst;
    }

    // Best method naam
    public void geefBeurt(Speler speler) throws RemoteException
    {
        // TODO: een speler object een beurt geven.
        notifyObservers();
    }
    
    public Speler getSpeler() throws RemoteException
    {
    	return this.speler;
    }
    
    public ArrayList<Speler> getSpelerLijst()
    {
    	return this.spelerLijst;
    }
    
    public void setSpeler(Speler speler) throws RemoteException {
    	this.speler = speler;
        notifyObservers();
    }

    @Override
    public void addObserver(BeurtObserver beurtObserver) throws RemoteException {
        observers.add(beurtObserver);
    }

    public void notifyObservers() throws RemoteException {
        for (BeurtObserver observer: observers) {
            observer.modelChanged(this);
        }
    }

}
