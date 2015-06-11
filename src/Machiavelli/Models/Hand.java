package Machiavelli.Models;

import Machiavelli.Interfaces.Observers.BankObserver;
import Machiavelli.Interfaces.Observers.HandObserver;
import Machiavelli.Interfaces.Remotes.HandRemote;

import java.rmi.RemoteException;
import java.util.*;

/**
 * De hand van de speler heeft controle over de kaarten die de speler
 * op dat moment bezit. Er kunnen kaarten in de hand worden toegevoegd
 * en verwijderd.
 *
 * @author Sander
 * @version 0.1
 *
 */
public class Hand implements HandRemote {
	// Variables
	private ArrayList<GebouwKaart> kaartenLijst;
	private Speler speler;
    private ArrayList<HandObserver> observers = new ArrayList<>();

	// Een speler start met 4 gebouwkaarten in zijn hand.
	public Hand(Speler speler) {
		this.speler = speler;
		kaartenLijst = new ArrayList<GebouwKaart>();
		for(int i = 0; i < 4; i ++) {
			// Trek 4 kaarten van de stapel (gebouwFactory)
            try {
                kaartenLijst.add(this.speler.getSpel().getGebouwFactory().trekKaart());
            } catch (RemoteException re) {
                System.out.print(re);
            }
		}
	}

	// Een gebouw toevoegen aan de hand van de speler
	public void addGebouw(GebouwKaart kaart) throws RemoteException {
		kaartenLijst.add(kaart);
        notifyObservers();
	}

	// Kaart verwijderen uit de hand van de speler
	public void removeGebouw(GebouwKaart gebouw) throws RemoteException {
		this.kaartenLijst.remove(gebouw);
        notifyObservers();
	}

	public ArrayList<GebouwKaart> getKaartenLijst() throws RemoteException
	{
		return this.kaartenLijst;
	}

	public void setKaartenLijst(ArrayList<GebouwKaart> lijst) throws RemoteException {
		this.kaartenLijst = lijst;
        notifyObservers();
	}

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

}