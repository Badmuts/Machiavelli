package Machiavelli.Models;

import Machiavelli.Controllers.SpeelveldController;
import Machiavelli.Interfaces.Karakter;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Speelveld {
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
		this.setKoning(spelers.get(0));		
		speelveldcontroller = new SpeelveldController(this);

	}

	public void setKoning(Speler spelers) {
		this.koning = spelers;
	}

	public void toonKarakterLijst() {
		
	}
	
	
}

