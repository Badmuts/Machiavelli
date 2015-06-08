/*package Views;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Controllers.SpelController;
import Models.Spel;

public class SpelOverzichtView {
	private Button exitbutton;
	private Button deelnemenknop;
	private Button hervattenknop;
	private Button nieuwspelknop;
	private Button spelregels;
	private SpelController sc;
	private Spel sp;
	private Scene spelOverzichtScene;
	
	public void setButton(Button button, int posx, int posy, float sizeX, float sizeY){
		button.setLayoutX(posx);
		button.setLayoutY(posy);
		button.setMinWidth(sizeX);
		button.setMinHeight(sizeY);
	}
	
	public SpelOverzichtView(SpelController sc, Spel sp){
		this.sc = sc;
		
		Pane spelOverzicht = new Pane();
		spelOverzicht.setStyle("-fx-background-color: #48443c");
		
		nieuwspelknop = new Button();
		hervattenknop = new Button();
		deelnemenknop = new Button();
		exitbutton = new Button();
		spelregels = new Button();

		nieuwspelknop.setText("Nieuw spel");
		nieuwspelknop.setId("gamekiezen");
		
		hervattenknop.setText("Hervatten");
		hervattenknop.setId("gamekiezen");
		
		deelnemenknop.setText("Kies spel");
		deelnemenknop.setId("gamekiezen");
		//deelnemen.setOnAction(this);
		
		exitbutton.setText("Afsluiten");
		exitbutton.setId("buttonexit");
		//exitknop.setOnAction(this);
		
		spelregels.setText("Spelregels");
		spelregels.setId("buttonregels");
		
		//Machiavelli tekst layout
		Text mainTx = new Text("Machiavelli");
		mainTx.setFill(Color.WHITE);
		DropShadow ds = new DropShadow();
		ds.setOffsetY(2);
		mainTx.setEffect(ds);
		mainTx.setScaleX(7);
		mainTx.setScaleY(6.5);
		mainTx.setLayoutX(780);
		mainTx.setLayoutY(190);
		
		setButton(nieuwspelknop,700,290,200f,75f);
		setButton(hervattenknop,700,370, 200f,75f);
		setButton(deelnemenknop,700,450,200f,75f);
		setButton(exitbutton,700,530,200f,75f);
		setButton(spelregels,20,30,125f,50f);
		
		//toevoegen van elementen aan het frame
		spelOverzicht.getChildren().addAll(nieuwspelknop,hervattenknop,deelnemenknop,exitbutton,spelregels,mainTx);
		
		//Instellen wat er weergeven moet worden
		spelOverzichtScene = new Scene(spelOverzicht, 1600, 900);
		spelOverzicht.getStylesheets().add("Views/stylecss.css");
	}
	public void show(Stage stage){
		stage.setTitle("Machiavelli");
		stage.setResizable(false);
		stage.setScene(spelOverzichtScene);
		stage.show();
	}
	
	public Button getNieuwSpelKnop(){
		return nieuwspelknop;
	}
	public Button getHervattenKnop(){
		return hervattenknop;
	}
	public Button getDeelnemenKnop(){
		return deelnemenknop;
	}
	public Button getExitButton(){
		return exitbutton;
	}
	
	public Button getSpelRegelsButton(){
		return spelregels;
	}
}
	*/
