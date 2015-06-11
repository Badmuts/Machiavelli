package Machiavelli.Models;

import Machiavelli.Controllers.SpeelveldController;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Observers.SpeelveldObserver;
import Machiavelli.Interfaces.Remotes.SpeelveldRemote;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Speelveld implements SpeelveldRemote {
	private ArrayList<Speler> spelers;
	private Speler koning;
	private Karakter karakter;
	private SpeelveldController speelveldcontroller;
	private ArrayList<SpeelveldObserver> observers = new ArrayList<>();

	public Speelveld(ArrayList<Speler> spelers) throws RemoteException{
		//Spelers koppeln aan speelveld
		//Start spelers is koning
		//Starten karakterkiezenlijst speler 1
		//Doorgeven karakterlijst aan andere spelers
		this.spelers = spelers;
		try {
			this.setKoning(spelers.get(0));
		} catch (RemoteException re) {
			System.out.print(re);
		}
		speelveldcontroller = new SpeelveldController(this);

	}

	public void setKoning(Speler spelers) throws RemoteException {
		this.koning = spelers;
        notifyObservers();
	}

	public void toonKarakterLijst() throws RemoteException {
		
	}

	@Override
	public void addObserver(SpeelveldObserver observer) throws RemoteException {
		observers.add(observer);
	}

	public void notifyObservers() throws RemoteException {
		for (SpeelveldObserver observer: observers) {
			observer.modelChanged(this);
		}
	}
	
	
}

