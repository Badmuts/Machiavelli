package Machiavelli.Models;

import Machiavelli.Interfaces.Observers.BeurtObserver;
import Machiavelli.Interfaces.Remotes.BeurtRemote;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Machiavelli.Controllers.KarakterController;
import Machiavelli.Interfaces.Observers.BeurtObserver;
import Machiavelli.Interfaces.Remotes.BeurtRemote;

/**
 *
 * Dit model geeft de spelers een beurt.
 *
 */
public class Beurt extends UnicastRemoteObject implements BeurtRemote, Serializable {
    private SpelRemote spel;
    private ArrayList<SpelerRemote> spelerLijst;
    private SpelerRemote speler;
    private ArrayList<BeurtObserver> observers = new ArrayList<>();
    private KarakterController karaktercontroller;
    private int observerIndex;
    private int karakterIndex;

    public Beurt(SpelRemote spel, ArrayList<SpelerRemote> spelerLijst) throws RemoteException
    {
        this.spel = spel;
        this.spelerLijst = spelerLijst;
    }

    /**
     * De speler de beurt geven aan het begin van een ronde om zijn karakter te kiezen.
     */
    public void BeginRondeBeurt(SpelerRemote speler) throws RemoteException {
    	for(SpelerRemote spelerLijst: spel.getSpelers()) {
    	    karaktercontroller.cmdKiesKarakter();
			karaktercontroller.cmdWeergeefKiesKarakterView();
    	}
    }
    
    public void geefBeurt(SpelerRemote speler) throws RemoteException
    {
      // TODO: Als deze methode wordt aangeroepen, wordt er bij de volgende karakter
      // de buttons ge-enabled en de anderen gedisabled. 
    	for(karakterIndex = 1; karakterIndex == 7; karakterIndex++) {
    	  
    	}
    	
    	nextObserver();
        notifyObservers();
    }
    
    public SpelerRemote getSpeler() throws RemoteException
    {
    	return this.speler;
    }
    
    public ArrayList<SpelerRemote> getSpelerLijst()
    {
    	return this.spelerLijst;
    }
    
    public void setSpeler(SpelerRemote speler) throws RemoteException {
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
