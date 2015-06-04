package Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import Models.Spel;
import Views.MainMenuView;

public class SpelController implements EventHandler<ActionEvent> {
	private MainMenuView mmv;
	private Spel spel;
	//private SpelOverzichtView sov;
	
	public SpelController(Spel sp){
		this.spel = sp;
		this.mmv = new MainMenuView (this,sp);
		
		//this.sov = new SpelOverzichtView(this, sp);
		mmv.getStartButton().setOnAction(this);
		mmv.getExitButton().setOnAction(this);
		mmv.getExitButton2().setOnAction(this);
		mmv.getNieuwSpelKnop().setOnAction(this);
		mmv.getHervattenknop().setOnAction(this);
		mmv.getDeelnemenKnop().setOnAction(this);
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
	}
	
	@Override
	public void handle(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == mmv.getExitButton()){
			System.exit(0);
		}
		if(e.getSource() == mmv.getExitButton2()){
			System.exit(0);
		}
		if(e.getSource() == mmv.getStartButton()){
			mmv.show2(spel.getPrimaryStage());
		}
		if(e.getSource() == mmv.getNieuwSpelKnop()){
			this.cmdNieuwSpel();
		}
		if(e.getSource() == mmv.getDeelnemenKnop()){
			
		}
		if(e.getSource() == mmv.getHervattenknop()){
			
		}
		
	}

}
