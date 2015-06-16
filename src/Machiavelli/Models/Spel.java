package Machiavelli.Models;

import Machiavelli.Factories.GebouwFactory;
import Machiavelli.Interfaces.Observers.SpelObserver;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Views.SpeelveldView;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Spel implements SpelRemote, Serializable {
	private int maxAantalSpelers;
	private Speelveld speelveld;
	private SpeelveldView speelveldview;
	private ArrayList<SpelerRemote> spelers = new ArrayList<>();
	private Bank bank;
	private GebouwFactory gebouwFactory;
	private ArrayList<SpelObserver> observers = new ArrayList<>();

	public Spel(int aantalSpelers){
		this.maxAantalSpelers = aantalSpelers;
		this.bank = new Bank();
		this.gebouwFactory = new GebouwFactory();
	}

//	public void nieuwSpel() throws RemoteException {
//		//Minimaal aantal spelers kiezen
//		//Speelveld laden
//		//Spelers koppeln aan speelveld
//		//Speelveld laten zien
//		//Start spelers is koning
//		//Starten karakterkiezenlijst speler 1
//		//Doorgeven karakterlijst aan andere spelers
//		this.spelers.add(new Speler(this));
//
//		/*spelers.add(new Speler(this));
//		spelers.add(new Speler(this));
//		spelers.add(new Speler(this));
//		spelers.add(new Speler(this));*/
//		this.speelveld = new Speelveld(this.spelers, this);
//        notifyObservers();
//	}

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
		System.out.println("Spel model changed!");
		for (SpelObserver observer: observers) {
			observer.modelChanged(this);
		}
	}

	@Override
	public void addSpeler(SpelerRemote speler) throws RemoteException {
		this.spelers.add(speler);
	}

	public void addSpeler(Speler speler) {
        this.spelers.add(speler);
        try {
            notifyObservers();
        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }

    public ArrayList<SpelerRemote> getSpelers() {
        return this.spelers;
    }

    public int getMaxAantalSpelers() {
        return this.maxAantalSpelers;
    }
}

