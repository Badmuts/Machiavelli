package Views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Controllers.SpelController;
import Models.Spel;

public class InvullenSpelersView extends TextField {
		private SpelController spelcontroller;
		private Spel spel;
		private Button okbutton;
		private Button terugbutton;
		private TextField textfield;
		private Scene invulscene;
		private Text invoertekst;
	
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
		
		invoertekst = new Text("Vul hier het aantal spelers in");
		okbutton = new Button("Ok");
		terugbutton = new Button("Terug");
		textfield = new TextField();
		
		stackpane.getChildren().addAll(textfield, okbutton, terugbutton,invoertekst);
		stackpane.setAlignment(invoertekst,Pos.TOP_CENTER);
		stackpane.setAlignment(okbutton, Pos.BOTTOM_LEFT);
		stackpane.setAlignment(terugbutton, Pos.BOTTOM_RIGHT);
		invulscene = new Scene(stackpane, 200, 200);
	}
 
    public void show(Stage stage){
    	stage.setResizable(false);
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

