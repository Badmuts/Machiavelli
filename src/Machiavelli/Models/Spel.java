package Machiavelli.Models;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Random;

import Machiavelli.Factories.GebouwFactory;
import Machiavelli.Factories.KarakterFactory;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Observers.SpelObserver;
import Machiavelli.Interfaces.Remotes.BankRemote;
import Machiavelli.Interfaces.Remotes.BeurtRemote;
import Machiavelli.Interfaces.Remotes.GebouwFactoryRemote;
import Machiavelli.Interfaces.Remotes.KarakterFactoryRemote;
import Machiavelli.Interfaces.Remotes.PuntenRemote;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;


/**
 * @author Bernd
 * 
 * Nadat in het hoofdmenu nieuwspel is geselecteerd, wordt deze klasse aangeroepen.
 * Hier worden alle benodigdheden voor het spel aangemaakt. Zoals bijvoorbeeld de
 * KarakterFactory en GebouwFactory.
 *
 */
public class Spel implements SpelRemote, Serializable {
	private int maxAantalSpelers;
	private BankRemote bank;
	private GebouwFactoryRemote gebouwFactory;
	private BeurtRemote beurt;
	private SpelerRemote speler;
	private KarakterFactoryRemote karakterFactory;
	private PuntenRemote puntenRemote;
	private ArrayList<SpelObserver> observers;
	private ArrayList<SpelerRemote> spelers = new ArrayList<>();
		private ArrayList<SpelerRemote> tempSpelers = new ArrayList();

	public Spel() {
	}

	/**
	 * Aanmaken van een nieuw spel.
	 * 
	 * @param maxAantalSpelers
	 * @throws RemoteException
	 *
	 */
    public void createNewSpel(int maxAantalSpelers) throws RemoteException {
				this.maxAantalSpelers = maxAantalSpelers;
				this.bank = new Bank();
				this.gebouwFactory = new GebouwFactory();
				this.spelers = new ArrayList<>();
				this.observers = new ArrayList<SpelObserver>();
				this.karakterFactory = new KarakterFactory();
				this.beurt = new Beurt(this, this.getSpelers(), speler);
				this.puntenRemote = new PuntenModel(this);
				
    }
    
    public PuntenRemote getPuntenModel() throws RemoteException {
        return this.puntenRemote;
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
	
	public KarakterFactoryRemote getKarakterFactory() {
		return this.karakterFactory;
	}

	public int getAantalSpelers() throws RemoteException {
		return spelers.size();
	}

	@Override
	public void addObserver(SpelObserver observer) throws RemoteException {
        System.out.println("Spel observer added! Observer: " + observers.size());
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

        speler.setKarakter(getRandomKarakterFor(speler));
        speler.getKarakter().setSpeler(speler); // TESTING ONLY
		this.spelers.add(speler);
        notifyObservers();
	}

	public void setMaxAantalSpelers(int maxAantalSpelers) {
		this.maxAantalSpelers = maxAantalSpelers;
	}

	public void setBank(BankRemote bank) {
		this.bank = bank;
	}

	public void setGebouwFactory(GebouwFactoryRemote gebouwFactory) {
		this.gebouwFactory = gebouwFactory;
	}

	public void setKarakterFactory(KarakterFactory karakterFactory) {
		this.karakterFactory = karakterFactory;
	}

    public void setTempSpelers(ArrayList<SpelerRemote> list)
    {
        this.tempSpelers = list;
    }
    public ArrayList<SpelerRemote> getTempSpelers() {
        return tempSpelers;
    }

	public void setSpelers(ArrayList<SpelerRemote> spelers) {
		this.spelers = spelers;
	}

    public ArrayList<SpelObserver> getObservers() throws RemoteException {
        return this.observers;
    }

    @Override
    public void laadSpel(SpelRemote loadSpel) throws RemoteException {
				this.maxAantalSpelers = 0;
        this.maxAantalSpelers = loadSpel.getMaxAantalSpelers();
				this.bank = null;
        this.bank = loadSpel.getBank();
				this.gebouwFactory = null;
        this.gebouwFactory = loadSpel.getGebouwFactory();
				this.spelers = null;
        this.spelers = loadSpel.getSpelers();
				this.observers = null;
        this.observers = loadSpel.getObservers();
				this.karakterFactory = null;
        this.karakterFactory = loadSpel.getKarakterFactory();
    }
}
