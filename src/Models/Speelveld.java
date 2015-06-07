package Models;

import java.util.ArrayList;

import javafx.stage.Stage;
import Controllers.SpeelveldController;
import Interfaces.Karakter;
import Views.SpeelveldView;

public class Speelveld {
	private Stage secondaryStage;
	private ArrayList<Speler> spelers;
	private Speler koning;
	private Karakter karakter;
	private SpeelveldView speelveldview;
	
	public Speelveld(Stage secondaryStage){
	 this.secondaryStage = secondaryStage;	
	}
	public Stage getSecondaryStage(){
		return secondaryStage;
	}
	
	public Speelveld(ArrayList<Speler> spelers){
		//Spelers koppeln aan speelveld
		//Start spelers is koning
		//Starten karakterkiezenlijst speler 1
		//Doorgeven karakterlijst aan andere spelers
		this.spelers = spelers;
		this.setKoning(spelers.get(0));		
		speelveldview = new SpeelveldView(new SpeelveldController(this),this);
	}
	public void setKoning(Speler spelers){
		this.koning = spelers;
	}
	public void toonKarakterLijst(){
		
	}
	
}

