package Machiavelli.Models;

import Machiavelli.Factories.GebouwFactory;
import Machiavelli.Interfaces.Observers.HandObserver;
import Machiavelli.Interfaces.Remotes.HandRemote;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * De hand van de speler heeft controle over de kaarten die de speler
 * op dat moment bezit. Er kunnen kaarten in de hand worden toegevoegd
 * en verwijderd.
 *
 * @author Sander
 * @version 0.1
 *
 */
public class Hand implements HandRemote, Serializable {
	private GebouwFactory gebouwFactory;
	// Variables
	private ArrayList<GebouwKaart> kaartenLijst = new ArrayList<GebouwKaart>();
	private Speler speler;
    private ArrayList<HandObserver> observers = new ArrayList<>();

	// Een speler start met 4 gebouwkaarten in zijn hand.
	public Hand(Speler speler) {
		this.speler = speler;
        trekKaarten();
    }

    /**
     * Trek vier kaarten van de stapel als start tarief.
     * Vult kaartenLijst[] met kaarten die hij ontvangt.
     */
    private void trekKaarten() {
        try {
            // Haal gebouwFactory op vanuit het spel.
            GebouwFactory factory = this.speler.getSpel().getGebouwFactory();
            for(int i = 0; i < 4; i ++) { // Trek 4 kaarten.
                addGebouw(factory.trekKaart()); // Voeg kaart toe aan hand
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
	public void addGebouw(GebouwKaart kaart) throws RemoteException {
		kaartenLijst.add(kaart);
        notifyObservers();
	}

    /**
     * Verwijder GebouwKaart uit Hand.
     *
     * @param gebouw
     * @throws RemoteException
     */
	public void removeGebouw(GebouwKaart gebouw) throws RemoteException {
		this.kaartenLijst.remove(gebouw);
        notifyObservers();
	}

    /**
     * Voeg meerderen gebouwen toe aan Hand. (Redundant?)
     *
     * @param gebouwKaarten
     * @throws RemoteException
     */
	public void addGebouwen(List<GebouwKaart> gebouwKaarten) throws RemoteException {
		this.kaartenLijst.addAll(gebouwKaarten);
        notifyObservers();
	}

    /**
     * Haal kaarten op uit Hand.
     * @return
     * @throws RemoteException
     */
	public ArrayList<GebouwKaart> getKaartenLijst() throws RemoteException {
		return this.kaartenLijst;
	}

    /**
     * Zet kaarten in Hand (Redundant?)
     * @param lijst
     * @throws RemoteException
     */
	public void setKaartenLijst(ArrayList<GebouwKaart> lijst) throws RemoteException {
		this.kaartenLijst = lijst;
        notifyObservers();
	}

    /**
     * Haal eigenaar van Hand op.
     *
     * @return
     * @throws RemoteException
     */
	public Speler getSpeler() throws RemoteException {
		return this.speler;
	}

	@Override
	public void addObserver(HandObserver observer) throws RemoteException {
		observers.add(observer);
	}

	public void notifyObservers() throws RemoteException {
		for (HandObserver observer: observers) {
			observer.modelChanged(this);
		}
	}

    public String toString() {
        String str = "KAARTEN IN HAND: ";
        for(GebouwKaart gebouwKaart: kaartenLijst) {
            str += gebouwKaart + " ";
        }
        return str;
    }

}