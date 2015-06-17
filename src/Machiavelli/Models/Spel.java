package Machiavelli.Models;

import Machiavelli.Factories.GebouwFactory;
import Machiavelli.Interfaces.Observers.SpelObserver;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Views.SpeelveldView;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

public class Spel implements SpelRemote, Serializable {
	private int maxAantalSpelers;
	private Speelveld speelveld;
	private SpeelveldView speelveldview;
//	private ArrayList<SpelerRemote> spelers = new ArrayList<>();
	private Bank bank;
	private GebouwFactory gebouwFactory = null;
	private ArrayList<SpelObserver> observers;
	private ArrayList<Speler> spelers = new ArrayList<>();
	private int aantalspelers;

	public Spel(){

    }

    public void createNewSpel(int maxAantalSpelers) throws RemoteException {
        this.maxAantalSpelers = maxAantalSpelers;
        this.bank = new Bank();
        this.gebouwFactory = new GebouwFactory().createFactory();
        this.observers = new ArrayList<>();
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
    
    
    public void saveGame(Spel spel) throws FileNotFoundException {
		XStream xs = new XStream(new DomDriver());

		// Tags hernoemen
		xs.alias("spel", Spel.class);
		xs.alias("speler", Speler.class);
		xs.alias("gebouwkaart", GebouwKaart.class);

		// Skippen problematische velden
		xs.omitField(Image.class, "progress");
		xs.omitField(Image.class, "platformImage");
		xs.omitField(Speelveld.class, "speelveldcontroller");

		// XML bestand wegschrijven
		FileOutputStream fos = new FileOutputStream(createSaveLocation() + "/" + generateFileName());
		xs.toXML(spel, fos);
	}

	public void loadGame()
	{
		//        InputStream in = new FileInputStream("testXML.xml");
		//        spel = null;
		//        spel = (Spel) xs.fromXML(in);
	}

	private String generateFileName()
	{
		String name;
		name = "saveGame_" + new Date().getTime() + ".xml";
		return name;
	}

	private File createSaveLocation()
	{
		File file = new File(System.getProperty("user.home") + "/machiavelli/");
		if (!file.exists()){
			file.mkdir();
		}

		return file;
	}
}