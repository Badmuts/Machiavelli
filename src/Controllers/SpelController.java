package Controllers;

import java.io.IOException;

import Models.Spel;
import Models.Spelregels;
import Views.MainMenuView;

public class SpelController{
	private MainMenuView mmv;
	private Spel spel;
	private RaadplegenSpelregelsController spelregelsController;
	//private SpelOverzichtView sov;
	
	public SpelController(Spel sp){
		this.spel = sp;
		this.spelregelsController = new RaadplegenSpelregelsController();
		Spelregels r = new Spelregels();
		this.mmv = new MainMenuView (this,sp);
		
		
		//this.sov = new SpelOverzichtView(this, sp);
		mmv.getStartButton().setOnAction(event -> mmv.show2(spel.getPrimaryStage()));
		mmv.getExitButton().setOnAction(event -> System.exit(0));
		mmv.getExitButton2().setOnAction(event -> System.exit(0));
		mmv.getNieuwSpelKnop().setOnAction(event -> cmdNieuwSpel());
		mmv.getHervattenknop().setOnAction(event -> System.out.println("spel hervatten"));
		mmv.getDeelnemenKnop().setOnAction(event -> System.out.println("Deelnemen"));
		mmv.getSpelregelsButton().setOnAction(event -> this.spelregelsController.cmdWeergeefSpelregels());
		//sov.getNieuwSpelKnop().setOnAction(this);
		//sov.getExitButton().setOnAction(this);
		
	}
	public void show(){
		mmv.show(spel.getPrimaryStage());
	}
	public void show2(){
		mmv.show(spel.getPrimaryStage());
	}
	
	public void cmdNieuwSpel(){
		//Create nieuw spel
		//Speelveld laden
		//Spelers koppeln aan speelveld
		//Start spelers is koning
		//Starten karakterkiezenlijst speler 1
		//Doorgeven karakterlijst aan andere spelers
		this.spel.NieuwSpel();
		mmv.StopStage(spel.getPrimaryStage());
		
	}
}

