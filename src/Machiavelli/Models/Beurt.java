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
    private int resetgebouwaantal;
    private int karakterIndex;
    //private int spelerNummer = 0;

    public Beurt(SpelRemote spel, ArrayList<SpelerRemote> spelerLijst, SpelerRemote speler) throws RemoteException
    {
        this.spel = spel;
        this.spelerLijst = spelerLijst;
        this.speler = speler;
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
     * 
     * @throw RemoteException
     */
    
    public void geefBeurt() throws RemoteException
    {
      // TODO: Karakter Toewijzen aan begin 
      //Die speler de beurt geven en wachten tot hij op einde beurt knop drukt
      //Als einde beurt knop wordt gedrukt, wordt de observerindex verhoogt.
      this.resetgebouwaantal = 0;
      this.resetgebouwaantal = speler.getGebouwdeGebouwen();
      nextBeurtObserver();
      this.getSpelerLijst().set(observerIndex, speler);
      
      
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
      notifyObservers();
    }

    public void notifyObservers() throws RemoteException {
        System.out.println("Beurt changed");
        for (BeurtObserver observer: observers) {
            observer.modelChanged(this);
        }
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
     * 
     * @throw RemoteException
     */
  private void nextBeurtObserver() throws RemoteException {
    	if (observers.size() > 0) {
    	    observers.get(observerIndex).setDisable(true);
			observerIndex++;
			if (observerIndex >= observers.size()) {
				observerIndex = 0;
    	    }
    	  
			observers.get(observerIndex).setDisable(false);
		} 

    }

}
