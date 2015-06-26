package Machiavelli.Models;

import Machiavelli.Factories.GebouwFactory;
import Machiavelli.Factories.KarakterFactory;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Observers.SpelObserver;
import Machiavelli.Interfaces.Remotes.BankRemote;
import Machiavelli.Interfaces.Remotes.BeurtRemote;
import Machiavelli.Interfaces.Remotes.GebouwFactoryRemote;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Models.Karakters.Condotierre;
import Machiavelli.Models.Karakters.Dief;
import Machiavelli.Models.Karakters.Magier;
import Machiavelli.Models.Karakters.Moordenaar;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Random;

public class Spel implements SpelRemote, Serializable {
	private int maxAantalSpelers;
	private BankRemote bank;
	private GebouwFactoryRemote gebouwFactory;
	private BeurtRemote beurt;
	private KarakterFactory karakterFactory;
	private ArrayList<SpelObserver> observers;
	private ArrayList<SpelerRemote> spelers = new ArrayList<>();

	public Spel(){

    }

    public void createNewSpel(int maxAantalSpelers) throws RemoteException {
        this.maxAantalSpelers = maxAantalSpelers;
        this.bank = new Bank();
        this.gebouwFactory = new GebouwFactory();
		this.spelers = new ArrayList<>();
        this.observers = new ArrayList<SpelObserver>();
        this.karakterFactory = new KarakterFactory();
        this.beurt = new Beurt(this, this.getSpelers());
        
    }
    
    public BeurtRemote getBeurt() throws RemoteException {
      return this.beurt;
    }

    public BankRemote getBank() throws RemoteException {
		return this.bank;
	}
	
	public GebouwFactoryRemote getGebouwFactory() throws RemoteException {
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
    public void addSpeler(SpelerRemote speler) throws RemoteException {
		this.spelers.add(speler);
		notifyObservers();
	}

    public ArrayList<SpelerRemote> getSpelers() {
        return this.spelers;
    }

    public int getMaxAantalSpelers() {
        return this.maxAantalSpelers;
    }

    public Karakter getRandomKarakterFor(SpelerRemote speler) throws RemoteException
    {
    	Random rand = new Random();
    	int randomNum = rand.nextInt((4 - 1) + 1) + 1;
    	Karakter tmpKarakter = null;
    	
    	for(Karakter karakter : speler.getSpel().getKarakterFactory().getKarakters())
    	{
    		tmpKarakter = speler.getSpel().getKarakterFactory().getKarakters().get(randomNum);
    	}
    	
    	return tmpKarakter;
    }
    
	public void createNewSpeler() throws RemoteException{
		SpelerRemote speler = new Speler();
        speler.addSpel(this);

//        speler.setKarakter(new Dief()); // TESTING ONLY
        speler.setKarakter(getRandomKarakterFor(speler));
        speler.getKarakter().setSpeler(speler); // TESTING ONLY
		this.spelers.add(speler);
		notifyObservers();
	}
}
