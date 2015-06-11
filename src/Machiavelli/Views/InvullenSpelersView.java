package Machiavelli.Views;

import Machiavelli.Controllers.SpelController;
import Machiavelli.Machiavelli;
import Machiavelli.Models.Spel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InvullenSpelersView extends TextField {
	private SpelController spelcontroller;
	private Spel spel;
	private Button okbutton;
	private Button terugbutton;
	private TextField textfield;
	private Scene invulscene;
	private Text invoertekst;
	private Stage stage = Machiavelli.getInstance().getStage();

	public InvullenSpelersView(SpelController spelcontroller, Spel spel){
		this.spelcontroller = spelcontroller;
		this.spel = spel;
		
		StackPane stackpane = new StackPane();
		
		invoertekst = new Text("Vul hier minimaal 2 en maximaal 7 spelers in");
		okbutton = new Button("Ok");
		terugbutton = new Button("Terug");
		textfield = new TextField();
		textfield.setMaxSize(50, 10);
		
		stackpane.getChildren().addAll(textfield, okbutton, terugbutton,invoertekst);
		stackpane.setAlignment(invoertekst,Pos.TOP_CENTER);
		stackpane.setAlignment(okbutton, Pos.BOTTOM_LEFT);
		stackpane.setAlignment(terugbutton, Pos.BOTTOM_RIGHT);
		
		invulscene = new Scene(stackpane, 400, 200);
	}
 
    public void show(){
		stage.setScene(invulscene);
		stage.centerOnScreen();
		stage.show();
	}

    public Button getOkButton(){
    	return okbutton;
    }

    public Button getTerugButton(){
    	return terugbutton;
    }

    public String getTextField(){
    	return textfield.getText();
    }
}

