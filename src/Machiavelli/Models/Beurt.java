package Machiavelli.Models;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Machiavelli.Interfaces.Observers.BeurtObserver;
import Machiavelli.Interfaces.Remotes.BeurtRemote;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;

/**
 *
 * Dit model geeft de spelers een beurt.
 *
 */
public class Beurt extends UnicastRemoteObject implements BeurtRemote, Serializable {
    private SpelRemote spel;
    private SpelerRemote speler;
    private ArrayList<SpelerRemote> spelerLijst;
    private ArrayList<BeurtObserver> observers = new ArrayList<>();
   //private KarakterController karaktercontroller;
    private int observerIndex;
    //private int karakterIndex;
    //private int spelerNummer = 0;

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
    /*public void BeginRondeBeurt(SpelerRemote speler) throws RemoteException {
      //TODO: Speler op plek 0 ophalen in de arraylist.
   
        this.speler = speler;
        speler.getSpel().getSpelers().get(spelerNummer);
        for(SpelerRemote spelerLijst: spel.getSpelers()) {
          
    	  spelerNummer++;
    	  setSpeler(this.spelerLijst.set(spelerNummer, spelerLijst));
    	}
        this.karakterIndex = 1;
    	geefBeurt(); 
    } */
    
    /**
     * De speler krijgt de beurt gebaseerd op de karakter nummer om zijn functies uit te voeren.
     */
    
    public void geefBeurt() throws RemoteException
    {
      // TODO: Karakter Toewijzen aan begin 
      //Die speler de beurt geven en wachten tot hij op einde beurt knop drukt
      //Als einde beurt knop wordt gedrukt, wordt de observerindex verhoogt.
      nextBeurtObserver();
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

    public void addObserver(BeurtObserver observer) throws RemoteException {
      nextBeurtObserver();  
      observers.add(observer);
      System.out.println("Beurt Observer ADDED!: " + this.observers.size());
    }

    public void notifyObservers() throws RemoteException {
        System.out.println("Beurt changed");
        for (BeurtObserver observer: observers) {
            observer.modelChanged(this);
        }
    }
    
  private void nextBeurtObserver() throws RemoteException {
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
