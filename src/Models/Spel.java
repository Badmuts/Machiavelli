package Models;

import java.util.ArrayList;

import javafx.stage.Stage;
import Views.SpeelveldView;

public class Spel {
	private Stage primaryStage;
	private Speelveld speelveld;
	private SpeelveldView speelveldview;
	private ArrayList<Speler> speler;
	
	public Spel(Stage primaryStage){
		this.primaryStage = primaryStage;
	}
	public Stage getPrimaryStage(){
		return primaryStage;
	}
	public void NieuwSpel(){
		//Speelveld laden
		//Spelers koppeln aan speelveld
		//Speelveld laten zien
		//Start spelers is koning
		//Starten karakterkiezenlijst speler 1
		//Doorgeven karakterlijst aan andere spelers
		ArrayList<Speler> spelers = new ArrayList<Speler>();
		spelers.add(new Speler());
		spelers.add(new Speler());
		spelers.add(new Speler());
		spelers.add(new Speler());
		this.speelveld = new Speelveld(spelers);
	}
	public void EindeBeurt(){
		
	}
}
