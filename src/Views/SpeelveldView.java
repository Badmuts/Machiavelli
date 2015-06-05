package Views;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Controllers.SpeelveldController;
import Models.Speelveld;

public class SpeelveldView {
	private Button gebruikEigenschap;
	private Button exitbutton;
	private Button spelregels;
	private Button opslaanknop;
	private Button placeholderbutton1;
	private Button placeholderbutton2;
	private SpeelveldController speelveldcontroller;
	private Speelveld speelveld;
	private Scene speelveldscene;
	private Stage stage;
	
	public void initButton(Button button,String tekst,String id, int posx, int posy, float sizeX, float sizeY){
		button.setText(tekst);
		button.setId(id);
		button.setLayoutX(posx);
		button.setLayoutY(posy);
		button.setMinWidth(sizeX);
		button.setMinHeight(sizeY);
	}
	
	public SpeelveldView(SpeelveldController speelveldcontroller,Speelveld speelveld){
		this.speelveld = speelveld;
		this.speelveldcontroller = speelveldcontroller;
		
		gebruikEigenschap = new Button();
		exitbutton = new Button();
		spelregels = new Button();
		opslaanknop = new Button();
		placeholderbutton1 = new Button();
		placeholderbutton2 = new Button();
		
		initButton(gebruikEigenschap,"Eigenschap","gamekiezen",1200,700, 200f,75f);
		initButton(exitbutton,"Afsluiten","buttonexit", 1400, 800, 200f,75f);
		initButton(spelregels,"Spelregels", "buttonregels", 20,30,125f,50f);
		initButton(opslaanknop,"Opslaan","buttonstart",1200, 800, 200f, 75f);
		
		Pane speelveldpane = new Pane();
		speelveldpane.getChildren().addAll(gebruikEigenschap, exitbutton,spelregels, opslaanknop);
		speelveldscene = new Scene(speelveldpane, 1600, 900);
		speelveldpane.getStylesheets().add("Resources/stylecss.css");
		this.show(new Stage());
	}
	public void show(Stage stage){
		stage.setTitle("Machiavelli");
		stage.setResizable(false);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setScene(speelveldscene);
		stage.show();
	}
	public Button getSpelregels(){
		return spelregels;
	}
	
}
