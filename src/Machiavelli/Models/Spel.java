package Machiavelli.Models;

import Machiavelli.Factories.GebouwFactory;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Views.SpeelveldView;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Spel implements SpelRemote {
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

	public void NieuwSpel() throws RemoteException {
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

	public void EindeBeurt() throws RemoteException {
		
	}
	
	public Bank getBank() throws RemoteException {
		return this.bank;
	}
	
	public GebouwFactory getGebouwFactory() throws RemoteException {
		return this.gebouwFactory;
	}

	public void setAantalSpelers(int aantalspelers) throws RemoteException {
		this.aantalspelers = aantalspelers;
	}

	public int getAantalSpelers() throws RemoteException {
		return aantalspelers;
	}
}

