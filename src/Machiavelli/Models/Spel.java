package Machiavelli.Models;

import Machiavelli.Factories.GebouwFactory;
import Machiavelli.Views.SpeelveldView;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.scene.image.Image;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

public class Spel {
	private int aantalspelers;
	private Speelveld speelveld;
	private SpeelveldView speelveldview;
	private ArrayList<Speler> speler;
	private Bank bank;
	private GebouwFactory gebouwFactory;
	
	public Spel(){
		bank = new Bank();
		gebouwFactory = new GebouwFactory();
	}

	public void NieuwSpel() {
		//Minimaal aantal spelers kiezen
		//Speelveld laden
		//Spelers koppeln aan speelveld
		//Speelveld laten zien
		//Start spelers is koning
		//Starten karakterkiezenlijst speler 1
		//Doorgeven karakterlijst aan andere spelers
		speler = new ArrayList<Speler>();
		if(aantalspelers >= 2 && aantalspelers < 8){
			for(int i = 0; i < aantalspelers; i++) {
				speler.add(new Speler(this));
			}
		} else {
			return;
		}

		/*spelers.add(new Speler(this));
		spelers.add(new Speler(this));
		spelers.add(new Speler(this));
		spelers.add(new Speler(this));*/
		this.speelveld = new Speelveld(speler);
	}

	public void EindeBeurt() {
		
	}
	
	public Bank getBank() {
		return this.bank;
	}
	
	public GebouwFactory getGebouwFactory() {
		return this.gebouwFactory;
	}

	public void setAantalSpelers(int aantalspelers) {
		this.aantalspelers = aantalspelers;
	}

	public int getAantalSpelers() {
		return aantalspelers;
	}

	public ArrayList<Speler> getSpelerLijst() {
		return this.speler;
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
