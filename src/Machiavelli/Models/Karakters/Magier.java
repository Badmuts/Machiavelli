package Machiavelli.Models.Karakters;

import Machiavelli.Enumerations.Type;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Observers.KarakterObserver;
import Machiavelli.Interfaces.Remotes.GebouwFactoryRemote;
import Machiavelli.Interfaces.Remotes.GebouwKaartRemote;
import Machiavelli.Interfaces.Remotes.HandRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by daanrosbergen on 03/06/15.
 * Edited by Sander de Jong on 05/06/15.
 * Edit by Bernd Oostrum
 * 
 * De speler heeft het karakter Magier gekozen. 
 * De eigenschappen van dit karakter worden gebruikt
 * door de speler tijdens de duur van een ronde.
 * 
 * De magier heeft als eigenschap dat hij al zijn 
 * handkaarten (ook als dit 0 is) mag ruilen met een
 * andere speler of naar keuze een aantal kaarten in de 
 * factory terugstoppen en dezelfde hoeveelheid weer trekken
 */


public class Magier extends UnicastRemoteObject implements Karakter, Serializable {

    // Variables
    private final String naam = "Magier";
    private final int nummer = 3;
    private final int bouwLimiet = 1;
    private final Type type = Type.NORMAAL;

    private SpelerRemote speler  = null;
    private Object target = null;
    private final String image = "Machiavelli/Resources/Karakterkaarten/Portrait-Magier.png";
    private ArrayList<KarakterObserver> observers = new ArrayList<>();

    public Magier() throws RemoteException {}

    /**
	 * Overriden van de methode uit de interface Karakter,
	 * de Magier wordt aan de speler gekoppeld.
	 */
	@Override
	public void setSpeler(SpelerRemote speler) throws RemoteException {
        this.speler = speler;
    }

    @Override
    public SpelerRemote getSpeler() throws RemoteException {
        return this.speler;
    }

    /**
     *Ruil kaarten met de stapel of met een andere speler.
     *
     * @return Is het gelukt de eigenschap uit te voeren.
     */
    @Override
    public boolean gebruikEigenschap() throws RemoteException {
        // TODO: ruilen bouwkaarten
        if (this.target == null)
            return false;

        try {
            ruilMetStapel();
            this.speler.setEigenschapGebruikt(true);
            target = null;
        } catch (Exception e) {
            try {
                ruilMetHand();
                this.speler.setEigenschapGebruikt(true);
                target = null;
            } catch (Exception cce) {
                cce.printStackTrace();
                return false;
            }
        }
        return true;
    }

    /**
     * Ruilt complete hand met een andere speler
     * @throws Exception
     */
    private void ruilMetHand() throws Exception {
        // Cast target naar hand van target
        SpelerRemote spel = (SpelerRemote) this.target;
        HandRemote hand = spel.getHand();
        // Add activeCard to target
        ArrayList<GebouwKaartRemote> targetKaarten = hand.getKaartenLijst();
        ArrayList<GebouwKaartRemote> spelerKaarten = this.speler.getHand().getKaartenLijst();
        hand.setKaartenLijst(spelerKaarten);
        this.speler.getHand().setKaartenLijst(targetKaarten);
    }

    /**
     * Ruilt geslecteerde kaarten met nieuwe kaarten van stapel
     * @throws Exception
     */
    private void ruilMetStapel() throws Exception {
        // Cast target to stapel
        GebouwFactoryRemote factory = (GebouwFactoryRemote) this.target;

        // Loop door geselecteerde kaarten heen
        for (GebouwKaartRemote kaart: this.speler.getHand().getActiveCards()) {
            // Add activeCard to target
            factory.addGebouw(kaart);
            // Get new cards from target
            GebouwKaartRemote newGebouwKaart = factory.trekKaart();
            // Add new cards to hand
            this.speler.getHand().addGebouw(newGebouwKaart);
            // Remove old card from hand
            this.speler.getHand().removeGebouw(kaart);
        }
        this.speler.getHand().resetActiveCards();
    }

    @Override
    public String getNaam() throws RemoteException {
    	return this.naam;
    }
   
    @Override
    public int getNummer() throws RemoteException {
    	return this.nummer;
    }

    @Override
    public int getBouwLimiet() throws RemoteException {
        return this.bouwLimiet;
    }

    public Type getType() throws RemoteException {
		return this.type;
	}

    @Override
    public void setTarget(Object target) throws RemoteException {
        this.target = target;
    }

    @Override
    public String getImage() throws RemoteException {
        return this.image;
    }

    @Override
    public void addObserver(KarakterObserver observer) throws RemoteException {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() throws RemoteException {
        for (KarakterObserver observer: observers) {
            observer.modelChanged(this);
        }
    }

    public void beurtOverslaan() throws RemoteException {}

    public Object getTarget() throws RemoteException {
        return target;
    }
}
