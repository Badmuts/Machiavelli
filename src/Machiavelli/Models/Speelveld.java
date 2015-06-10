package Machiavelli.Models;

import Machiavelli.Controllers.SpeelveldController;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Remotes.SpeelveldRemote;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Speelveld implements SpeelveldRemote {
	private ArrayList<Speler> spelers;
	private Speler koning;
	private Karakter karakter;
	private SpeelveldController speelveldcontroller;

	public Speelveld(ArrayList<Speler> spelers){
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
	}

	public void toonKarakterLijst() throws RemoteException {
		
	}
	
	
}

