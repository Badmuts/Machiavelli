package Machiavelli.Models;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Machiavelli.Controllers.KarakterController;
import Machiavelli.Interfaces.Observers.BeurtObserver;
import Machiavelli.Interfaces.Remotes.BeurtRemote;

/**
 *
 * Dit model geeft de spelers een beurt.
 *
 */
public class Beurt implements BeurtRemote, Serializable {
    private Spel spel;
    private ArrayList<Speler> spelerLijst;
    private Speler speler;
    private ArrayList<BeurtObserver> observers = new ArrayList<>();
    private KarakterController karaktercontroller;
    private int observerIndex;

    public Beurt(Spel spel, ArrayList<Speler> spelerLijst)
    {
        this.spel = spel;
        this.spelerLijst = spelerLijst;
    }

    /**
     * De speler de beurt geven aan het begin van een ronde om zijn karakter te kiezen.
     */
    
    public void BeginRondeBeurt(Speler speler) throws RemoteException {
    	for(Speler spelerLijst: spel.getSpelers()) {
			karaktercontroller.cmdKiesKarakter();
    	}
    }
    
    public void geefBeurt(Speler speler) throws RemoteException
    {
        // TODO: een speler object een beurt geven. De eerste beurt is de koning met karakternummer 4
    	// Zet na de beurt de karakternummer op 1 en sla dan 4 over.
    	this.speler = speler;
    	
    	nextObserver();
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

    public void addObserver(BeurtObserver beurtObserver) throws RemoteException {
        observers.add(beurtObserver);
    }

    public void notifyObservers() throws RemoteException {
        for (BeurtObserver observer: observers) {
            observer.modelChanged(this);
        }
    }
    
    private void nextObserver() throws RemoteException {
    	if (observers.size() > 0) {
			observers.get(observerIndex).setDisable(false);
			observerIndex++;
			if (observerIndex >= observers.size()) {
				observerIndex = 0;
			}
			observers.get(observerIndex).setDisable(true);
		}

    }

}
