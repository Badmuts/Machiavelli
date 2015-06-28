package Machiavelli.Models;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import com.sun.webkit.ContextMenu.ShowContext;

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
    //private int spelerNummer = 0;

    public Beurt(SpelRemote spel, ArrayList<SpelerRemote> spelerLijst, SpelerRemote speler) throws RemoteException
    {
        this.spel = spel;
        this.spelerLijst = spelerLijst;
        this.speler = speler;
    } 
    
    /**
     * De speler krijgt de beurt gebaseerd op de karakter nummer om zijn functies uit te voeren.
     * 
     * @throw RemoteException
     */
    
    public void geefBeurt() throws RemoteException
    {
      // TODO: Karakter Toewijzen aan begin 
      /**Die speler de beurt geven en wachten tot hij op einde beurt knop drukt
      *Als einde beurt knop wordt gedrukt, wordt de observerindex verhoogt.
      */

      nextBeurtObserver();
      notifyObservers();
      
      this.speler = this.getSpelerLijst().get(observerIndex);
      this.speler.setGebouwdeGebouwen(0); // Dit proberen te vervangen door nieuwe karakterFactory
      this.speler.setEigenschapGebruikt(false); // Dit proberen te vervangen door nieuwe karakterFactory
    }
    
    public void getInkomstenView() throws RemoteException {
      this.observers.get(observerIndex).showInkomsten();
    }
    
    public void getKarakterView() throws RemoteException {
      this.observers.get(observerIndex).showKarakterMenu();
    }
    
    public SpelerRemote getSpeler() throws RemoteException
    {
      return this.speler;
    }
    
    public ArrayList<BeurtObserver> getObserverList() {
      return this.observers;
    }
    
    public void setSpeler(SpelerRemote speler) throws RemoteException {
    	this.speler = speler;
        notifyObservers();
    }

    public void addObserver(BeurtObserver observer) throws RemoteException {
      nextBeurtObserver();
      observers.add(observer);
      System.out.println("Beurt Observer ADDED!: " + this.observers.size());
      notifyObservers();
      
    }

    public void notifyObservers() throws RemoteException {
        System.out.println("Beurt changed");
        for (BeurtObserver observer: observers) {
            observer.modelChanged(this);
        }
    }
    
    public void setObserverIndex(int observerIndex) {
      this.observerIndex = observerIndex;
    }
    
    public int getObserverIndex() {
      return this.observerIndex;
    }
    
    public ArrayList<SpelerRemote> getSpelerLijst()
    {
      return this.spelerLijst;
    }
    
    /**
     * Deze method kijkt of het aantal observers groter is dan 0, als het zo is
     * roept die de beurtobserver aan met de method setDisable,
     * deze method krijgt een boolean mee als parameter.
     * De observer krijgt als eerst een true mee(dus de buttons bij de eerste
     * observer worden uitgeschakeld), daarna verhoogt hij de observerindex.
     * Als de observerIndex niet zo groot is als de arraylist, laat hij de
     * volgende observer de buttons enablen door setDisable(false)
     * de buttons krijgen in buttonholderActionView -> Modelchanged
     * deze boolean waardes mee.
     * Met dank aan Alex van Manen.
     * 
     * @throw RemoteException
     */
  private void nextBeurtObserver() throws RemoteException {
    	if (observers.size() > 0) {
    	    observers.get(observerIndex).setDisable(true);
			observerIndex++;
			if (observerIndex >= observers.size() - 1) {
				observerIndex = 0;
			}
			observers.get(observerIndex).setDisable(false);
		} 

    }

}
