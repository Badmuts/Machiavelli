package Views;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Controllers.SpeelveldController;
import Models.Speelveld;

public class SpeelveldView {
	private Button gebruikEigenschap;
	private Button exitbutton;
	private Button spelregels;
	private Button opslaanKnop;
	private Button placeholderbutton1;
	private Button placeholderbutton2;
	private SpeelveldController speelveldcontroller;
	private Speelveld speelveld;
	private Scene speelveldscene;
	
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
		opslaanKnop = new Button();
		placeholderbutton1 = new Button();
		placeholderbutton2 = new Button();
		
		initButton(gebruikEigenschap,"Eigenschap","gamekiezen",1300,700, 200f,75f);
		
		Pane speelveldpane = new Pane();
		speelveldpane.getChildren().addAll(gebruikEigenschap);
		speelveldscene = new Scene(speelveldpane, 1600, 900);
		speelveldpane.getStylesheets().add("Resources/stylecss.css");
			
	}
	public void show(Stage stage){
		stage.setTitle("Machiavelli");
		stage.setResizable(false);
		stage.setScene(speelveldscene);
		stage.show();
	}
}
