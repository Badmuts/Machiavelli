package Machiavelli.Models;

import Machiavelli.Interfaces.Observers.HandObserver;
import Machiavelli.Interfaces.Remotes.GebouwKaartRemote;
import Machiavelli.Interfaces.Remotes.HandRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Sander
 * 
 * De hand van de speler heeft controle over de kaarten die de speler
 * op dat moment bezit. Er kunnen kaarten in de hand worden toegevoegd
 * en verwijderd.
 *
 * 
 */

public class Hand extends UnicastRemoteObject implements HandRemote, Serializable {
    private ArrayList<GebouwKaartRemote> kaartenLijst = new ArrayList<>();
    private SpelerRemote speler;
    private ArrayList<HandObserver> observers = new ArrayList<>();
    private ArrayList<GebouwKaartRemote> activeCards = new ArrayList<>();
   
    /**
     * Als het spel gestart wordt en de hand van de speler wordt aangemaakt, 
     * begint een speler met 4 gebouwkaarten in zijn hand.
     *
     * @param speler
     * @throws RemoteException
     */
    public Hand(SpelerRemote speler) throws RemoteException {
//        super(1099);
        this.speler = speler;
        trekKaarten();
    }

    /**
     * Trek vier kaarten van de stapel en plaatst deze in de hand van de speler.
     * Vult kaartenLijst[] met kaarten die hij ontvangt.
     * 
     * @throws RemoteException
     */
    private void trekKaarten() {
        try {
           
            for (int i = 0; i < 4; i++) { // Trek 4 kaarten.
                addGebouw(this.speler.getSpel().getGebouwFactory().trekKaart()); // Voeg kaart toe aan hand
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Voeg GebouwKaart toe aan Hand.
     *
     * @param kaart
     * @throws RemoteException
     */
    public void addGebouw(GebouwKaartRemote kaart) throws RemoteException {
        kaartenLijst.add(kaart);
        notifyObservers();
    }

    @Override
    public void addActiveCard(GebouwKaartRemote gebouwKaartRemote) throws RemoteException {
        this.activeCards.add(gebouwKaartRemote);
    }

    @Override
    public ArrayList<GebouwKaartRemote> getActiveCards() throws RemoteException {
        return this.activeCards;
    }

    @Override
    public void removeActiveCard(GebouwKaartRemote gebouwKaartRemote) throws RemoteException {
        this.activeCards.remove(gebouwKaartRemote);
    }

    @Override
    public void resetActiveCards() throws RemoteException {
        this.activeCards.clear();
    }

    @Override
    public GebouwKaartRemote trekKaart() throws RemoteException {
        GebouwKaartRemote kaart = this.kaartenLijst.get(0);
        this.kaartenLijst.remove(kaart);
        return kaart;
    }

    /**
     * Verwijder GebouwKaart uit de hand van de speler.
     *
     * @param gebouw
     * @throws RemoteException
     */
    public void removeGebouw(GebouwKaartRemote gebouw) throws RemoteException {
        Iterator<GebouwKaartRemote> iterator = this.kaartenLijst.iterator();
        while (iterator.hasNext()) {
            GebouwKaartRemote kaart = iterator.next();
            if (kaart.getNaam().equals(String.valueOf(gebouw.getNaam()))) {
                iterator.remove();
                notifyObservers();
                break;
            }
        }
    }

    /**
     * Voeg meerderen gebouwen toe aan de hand van de speler.
     *
     * @param gebouwKaarten
     * @throws RemoteException
     */
    public void addGebouwen(List<GebouwKaartRemote> gebouwKaarten) throws RemoteException {
        this.kaartenLijst.addAll(gebouwKaarten);
        notifyObservers();
    }

    /**
     * Haal kaarten op uit Hand.
     *
     * @return
     * @throws RemoteException
     */
    public ArrayList<GebouwKaartRemote> getKaartenLijst() throws RemoteException {
        return this.kaartenLijst;
    }

    /**
     * Zet kaarten in Hand (Redundant?)
     *
     * @param lijst
     * @throws RemoteException
     */
    public void setKaartenLijst(ArrayList<GebouwKaartRemote> lijst) throws RemoteException {
        this.kaartenLijst = lijst;
        notifyObservers();
    }

  
    public SpelerRemote getSpeler() throws RemoteException {
        return this.speler;
    }

    public void addObserver(HandObserver observer) throws RemoteException {
        observers.add(observer);
        System.out.println("Hand observer added!: Observers: " + this.observers.size());
    }

    public void notifyObservers() throws RemoteException {
        System.out.println("Hand changed!: Observers: " + this.observers.size());
        for (HandObserver observer : observers) {
            observer.modelChanged(this);
        }
    }

    public String toString() {
        String str = "KAARTEN IN HAND: ";
        for (GebouwKaartRemote gebouwKaart : kaartenLijst) {
            str += gebouwKaart + " ";
        }
        return str;
    }

}
