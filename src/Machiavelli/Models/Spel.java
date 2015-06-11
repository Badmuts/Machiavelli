package Machiavelli.Models;

import Machiavelli.Factories.GebouwFactory;
import Machiavelli.Interfaces.Observers.SpelObserver;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Views.SpeelveldView;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Spel implements SpelRemote {
	private Speelveld speelveld;
	private SpeelveldView speelveldview;
	private ArrayList<Speler> spelers = new ArrayList<>();
	private Bank bank;
	private GebouwFactory gebouwFactory;
	private ArrayList<SpelObserver> observers = new ArrayList<>();
	
	public Spel(){
		bank = new Bank();
		gebouwFactory = new GebouwFactory();
	}

	public void nieuwSpel() throws RemoteException {
		//Minimaal aantal spelers kiezen
		//Speelveld laden
		//Spelers koppeln aan speelveld
		//Speelveld laten zien
		//Start spelers is koning
		//Starten karakterkiezenlijst speler 1
		//Doorgeven karakterlijst aan andere spelers
		this.spelers.add(new Speler(this));

		/*spelers.add(new Speler(this));
		spelers.add(new Speler(this));
		spelers.add(new Speler(this));
		spelers.add(new Speler(this));*/
		this.speelveld = new Speelveld(this.spelers, this.spel);
        notifyObservers();
	}

    @Override
    public void removeObserver(SpelObserver observer) throws RemoteException {
        this.observers.remove(observer);
    }

    public Bank getBank() throws RemoteException {
		return this.bank;
	}
	
	public GebouwFactory getGebouwFactory() throws RemoteException {
		return this.gebouwFactory;
	}

	public int getAantalSpelers() throws RemoteException {
		return spelers.size();
	}

	@Override
	public void addObserver(SpelObserver observer) throws RemoteException {
		observers.add(observer);
	}

	public void notifyObservers() throws RemoteException {
		for (SpelObserver observer: observers) {
			observer.modelChanged(this);
		}
	}
}

