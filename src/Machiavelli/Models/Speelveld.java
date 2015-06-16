package Machiavelli.Models;

import Machiavelli.Controllers.SpeelveldController;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Observers.SpeelveldObserver;
import Machiavelli.Interfaces.Remotes.SpeelveldRemote;
import Machiavelli.Interfaces.Remotes.SpelRemote;
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
	private Speler koning;
	private Karakter karakter;
	private SpeelveldController speelveldcontroller;
	private Speler speler;
	private ArrayList<SpeelveldObserver> observers = new ArrayList<>();

	public Speelveld(SpelRemote spel) {
        this.spel = spel;
	}

	public void setKoning(Speler spelers) throws RemoteException {
		this.koning = spelers;
        notifyObservers();
	}

	public void toonKarakterLijst() throws RemoteException {
		// TODO
	}

	@Override
	public void addObserver(SpeelveldObserver observer) throws RemoteException {
		observers.add(observer);
	}

	public void notifyObservers() throws RemoteException {
		for (SpeelveldObserver observer: observers) {
			observer.modelChanged(this);
		}
	}

	public void registratieView(SpeelveldView speelveldview) {
		this.speelveldView = speelveldview;
	}

    public Speler getSpeler() {
        return this.speler;
    }

    public void addSpeler(Speler speler) {
        this.speler = speler;
    }

	public String toString() {
        return "Hoi ik ben een SpeelveldModel";
	}
}