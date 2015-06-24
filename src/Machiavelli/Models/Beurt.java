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
    private int karakterIndex = 1;
    private int spelerNummer = 0;

    public Beurt(SpelRemote spel, ArrayList<SpelerRemote> spelerLijst) throws RemoteException
    {
        this.spel = spel;
        this.spelerLijst = spelerLijst;
    }

    /**
     * De speler de beurt geven aan het begin van een ronde om zijn karakter te kiezen.
     * Nadat een speler een karakter heeft gekozen, moet de method de volgende speler laten kiezen.
     * Nadat alle spelers gekozen zijn moet de geefBeurt method aangeroepen worden.
     */
    public void BeginRondeBeurt() throws RemoteException {
      //TODO: Speler op plek 0 ophalen in de arraylist.
      //
        setSpeler(this.spelerLijst.get(spelerNummer));
        for(SpelerRemote spelerLijst: spel.getSpelers()) {
    	  karaktercontroller.cmdWeergeefKiesKarakterView();    	    
    	  karaktercontroller.cmdKiesKarakter();
    	  spelerNummer++;
    	  setSpeler(this.spelerLijst.set(spelerNummer, spelerLijst));
    	}
    	geefBeurt(karakterIndex);
    }
    
    /**
     * De speler krijgt de beurt gebaseerd op de karakter om zijn functies uit te voeren.
     */
    
    public void geefBeurt(int karakterIndex) throws RemoteException
    {
      // TODO: Karakter Toewijzen.
     this.karakterIndex = karakterIndex;
     if (karakterIndex == speler.getKarakter().getNummer()) {
       observers.get(observerIndex).setDisable(false);
     }
      
    }
    
    public void EindeBeurt() throws RemoteException {
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
