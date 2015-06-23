package Machiavelli.Models;

import Machiavelli.Factories.GebouwFactory;
import Machiavelli.Interfaces.Observers.SpelObserver;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Models.Karakters.Prediker;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.scene.image.Image;
import Machiavelli.Factories.GebouwFactory;
import Machiavelli.Factories.KarakterFactory;
import Machiavelli.Interfaces.Observers.SpelObserver;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Views.SpeelveldView;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Spel implements SpelRemote, Serializable {
	private int maxAantalSpelers;
	private Bank bank;
	private GebouwFactory gebouwFactory;
	private KarakterFactory karakterFactory;
	private ArrayList<SpelObserver> observers;
	private ArrayList<Speler> spelers = new ArrayList<>();

	public Spel(){

    }

    public void createNewSpel(int maxAantalSpelers) throws RemoteException {
        this.maxAantalSpelers = maxAantalSpelers;
        this.bank = new Bank();
        this.gebouwFactory = new GebouwFactory();
		this.spelers = new ArrayList<Speler>();
        this.observers = new ArrayList<SpelObserver>();
        this.karakterFactory = new KarakterFactory();
    }

    public Bank getBank() throws RemoteException {
		return this.bank;
	}
	
	public GebouwFactory getGebouwFactory() throws RemoteException {
		return this.gebouwFactory;
	}
	
	public KarakterFactory getKarakterFactory()
	{
		return this.karakterFactory;
	}

	public int getAantalSpelers() throws RemoteException {
		return spelers.size();
	}

	@Override
	public void addObserver(SpelObserver observer) throws RemoteException {
		observers.add(observer);
	}
    
    @Override
    public void removeObserver(SpelObserver observer) throws RemoteException {
        this.observers.remove(observer);
    }

    @Override
	public void notifyObservers() throws RemoteException {
		System.out.println("Spel model changed!");
		System.out.println("Aantal spelers in spel: " + this.getAantalSpelers());
		for (SpelObserver observer: observers) {
			observer.modelChanged(this);
		}
	}
    
    @Override
    public void addSpeler(Speler speler) throws RemoteException {
		this.spelers.add(speler);
		notifyObservers();
	}

    public ArrayList<Speler> getSpelers() {
        return this.spelers;
    }

    public int getMaxAantalSpelers() {
        return this.maxAantalSpelers;
    }

	public void createNewSpeler() throws RemoteException{
		Speler speler = new Speler();
        speler.addSpel(this);
        speler.setKarakter(new Prediker()); // TESTING ONLY
        speler.getKarakter().setSpeler(speler); // TESTING ONLY
		this.spelers.add(speler);
		notifyObservers();
	}
}