package Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import Models.Spel;
import Views.MainMenuView;

public class SpelController implements EventHandler<ActionEvent> {
	private MainMenuView mmv;
	private Spel spel;
	
	public SpelController(Spel sp){
		this.spel = sp;
		this.mmv = new MainMenuView (this,sp);
		
		mmv.getStartButton().setOnAction(this);
		mmv.getExitButton().setOnAction(this);
	}
	public void show(){
		mmv.show(spel.getPrimaryStage());
	}
	
	@Override
	public void handle(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == mmv.getExitButton()){
			System.exit(0);
		}
	}

}
