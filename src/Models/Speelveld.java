package Models;

import java.util.ArrayList;

import javafx.stage.Stage;
import Interfaces.Karakter;

public class Speelveld {
	private Stage secondStage;
	private ArrayList<Speler> spelers;
	private Speler koning;
	private Karakter karakter;
	
	public Speelveld(Stage secondStage){
	 this.secondStage = secondStage;	
	}
	public Stage getSecondStage(){
		return secondStage;
	}
	
	public Speelveld(ArrayList<Speler> spelers){
		//Spelers koppeln aan speelveld
		//Start spelers is koning
		//Starten karakterkiezenlijst speler 1
		//Doorgeven karakterlijst aan andere spelers
		this.spelers = spelers;
		this.setKoning(spelers.get(1));
		
	}
	public void setKoning(Speler spelers){
		this.koning = spelers;
	}
	public void toonKarakterLijst(){
		
	}
	
}

