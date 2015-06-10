package Machiavelli.Models;

import Machiavelli.Factories.GebouwFactory;
import Machiavelli.Views.SpeelveldView;

import java.util.ArrayList;

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
		ArrayList<Speler> spelers = new ArrayList<Speler>();
		for(int i = 0; i < aantalspelers; i++){
			spelers.add(new Speler(this));
		}
		/*spelers.add(new Speler(this));
		spelers.add(new Speler(this));
		spelers.add(new Speler(this));
		spelers.add(new Speler(this));*/
		this.speelveld = new Speelveld(spelers);
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
}

