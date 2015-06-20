package Machiavelli.Models;

import Machiavelli.Controllers.SpeelveldController;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Observers.SpeelveldObserver;
import Machiavelli.Interfaces.Remotes.SpeelveldRemote;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Machiavelli;
import Machiavelli.Views.SpeelveldView;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * 
 * Model van speelveld.
 *
 */

public class Speelveld implements SpeelveldRemote, Serializable {
    private SpeelveldView speelveldView;
	private ArrayList<Speler> spelers;
	private SpelRemote spel;
	private SpelerRemote koning;
	private Karakter karakter;
	private SpeelveldController speelveldcontroller;
	private SpelerRemote speler;
	private ArrayList<SpeelveldObserver> observers = new ArrayList<>();

	public Speelveld(SpelRemote spel) {
        try {
            this.spel = (SpelRemote) Machiavelli.getInstance().getRegistry().lookup("Spel");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public void setKoning(SpelerRemote spelers) throws RemoteException {
		this.koning = spelers;
        notifyObservers();
	}

	public void toonKarakterLijst() throws RemoteException {
		// TODO
	}

	public void addObserver(SpeelveldObserver observer) throws RemoteException {
		observers.add(observer);
	}

	public void notifyObservers() throws RemoteException {
		for (SpeelveldObserver observer: observers) {
			observer.modelChanged(this);
		}
	}

    public SpelerRemote getSpeler() {
        return this.speler;
    }

    public void addSpeler(SpelerRemote speler) {
        this.speler = speler;
    }

	public String toString() {
        return "Hoi ik ben een SpeelveldModel";
	}
}