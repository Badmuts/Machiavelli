package Views;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Controllers.SpelController;
import Models.Spel;

public class InvullenSpelersView extends TextField {
		private SpelController spelcontroller;
		private Spel spel;
		private Button okbutton;
		private Button terugbutton;
		private TextField textfield;
		private Scene invulscene;
	
	public void initButton(Button button,String tekst,String id, int posx, int posy, float sizeX, float sizeY){
		button.setText(tekst);
		button.setId(id);
		button.setLayoutX(posx);
		button.setLayoutY(posy);
		button.setMinWidth(sizeX);
		button.setMinHeight(sizeY);
	}
	public InvullenSpelersView(SpelController spelcontroller, Spel spel){
		this.spelcontroller = spelcontroller;
		this.spel = spel;
		
		StackPane stackpane = new StackPane();
		
		okbutton = new Button();
		terugbutton = new Button();
		textfield = new TextField("Aantal spelers");
		
		initButton(okbutton,"Ok", "buttonstart", 300,200,200f,75f);
		initButton(terugbutton,"Terug", "buttonexit", 0,200,200f,75f);
		
		stackpane.getChildren().addAll(textfield, okbutton, terugbutton);
		
		invulscene = new Scene(stackpane, 600, 400);
	}
 
    public void show(Stage stage){
		stage.setResizable(false);
		stage.setScene(invulscene);
		stage.show();
	}
    public Button getOkButton(){
    	return okbutton;
    }
    public Button getTerugButton(){
    	return terugbutton;
    }
}

