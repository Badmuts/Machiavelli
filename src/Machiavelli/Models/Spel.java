package Machiavelli.Models;

import Machiavelli.Factories.GebouwFactory;
import Machiavelli.Factories.KarakterFactory;
import Machiavelli.Interfaces.Observers.SpelObserver;
import Machiavelli.Interfaces.Remotes.BankRemote;
import Machiavelli.Interfaces.Remotes.GebouwFactoryRemote;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Models.Karakters.Prediker;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Spel implements SpelRemote, Serializable {
	private int maxAantalSpelers;
	private BankRemote bank;
	private GebouwFactoryRemote gebouwFactory;
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

		public void opslaanSpel() {
				try
				{
						FileOutputStream saveFile = new FileOutputStream(createSaveLocation() + "/machiavelli.sav");
						ObjectOutputStream save = new ObjectOutputStream(saveFile);
						save.writeObject(this);
						save.close();
				}
				catch (Exception e)
				{
						e.printStackTrace();
				}
				//				XStream x = new XStream(new DomDriver());
//				FileOutputStream fos = null;
//				try {
//						fos = new FileOutputStream(createSaveLocation() + "/machiavelli_save.xml");
//						x.toXML(this, fos);
//				} catch (FileNotFoundException e) {
//						e.printStackTrace();
//				}
		}

		public SpelRemote ladenSpel() {
				try
				{
						FileInputStream saveFile = new FileInputStream(System.getProperty("user.home") + "/machiavelli.sav");
						ObjectInputStream restore = new ObjectInputStream(saveFile);
						Object obj = restore.readObject();
						restore.close();
						return (SpelRemote)obj;
				}
				catch (Exception e)
				{
						e.printStackTrace();
				}
				//				SpelRemote tempSpelRemote;
//				XStream xs = new XStream();
//				InputStream in;
//				try {
//						in = new FileInputStream(System.getProperty("user.home") + "/machiavelli_save.xml");
//						tempSpelRemote = (SpelRemote) xs.fromXML(in);
//						return tempSpelRemote;
//				} catch (FileNotFoundException e) {
//						e.printStackTrace();
//				}
				return null;
		}

		public File createSaveLocation()
		{
				File file = new File(System.getProperty("user.home") + "/machiavelli/");
				if (!file.exists()){
						file.mkdir();
				}

				return file;
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

	public void createNewSpeler() throws RemoteException{
		SpelerRemote speler = new Speler();
        speler.addSpel(this);
        speler.setKarakter(new Prediker()); // TESTING ONLY
        speler.getKarakter().setSpeler(speler); // TESTING ONLY
		this.spelers.add(speler);
		notifyObservers();
	}
}
