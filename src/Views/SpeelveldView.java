package Views;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
	private Button placeholderbutton3;
	private SpeelveldController speelveldcontroller;
	private Speelveld speelveld;
	private Scene speelveldscene;
	private Stage stage;
	private Rectangle buttonholder;
	private Rectangle karakterholder;
	
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
		placeholderbutton3 = new Button();
		
		initButton(gebruikEigenschap,"Sander","gamebutton",1400,770, 180f,60f);
		initButton(exitbutton,"Afsluiten","buttonexit", 1400, 835, 180f,60f);
		initButton(spelregels,"Spelregels", "buttonregels", 15,10,125f,50f);
		initButton(opslaanknop,"Opslaan","buttonsave",1200, 835, 180f, 60f);
		initButton(placeholderbutton1, "Jimmy", "gamebutton", 1200, 770, 180f, 60f);
		initButton(placeholderbutton2, "Jaimy", "gamebutton", 1400, 705, 180f, 60f);
		initButton(placeholderbutton3, "Bernd", "gamebutton", 1200, 705, 180f, 60f);
		
		buttonholder = new Rectangle(1175,680,425,250);
		buttonholder.setFill(Color.GRAY);
		
		karakterholder = new Rectangle(0, 680, 250, 250);
		karakterholder.setFill(Color.GRAY);
		
		Pane speelveldpane = new Pane();
		
		Image spelregelsbg = new Image("Resources/SpelregelsBorder.png");
		ImageView iv = new ImageView(spelregelsbg);
		iv.setCache(true);
		iv.setFitWidth(200);
        
		speelveldpane.getChildren().addAll(iv,buttonholder,karakterholder, gebruikEigenschap, exitbutton,spelregels, opslaanknop,placeholderbutton1,placeholderbutton2,placeholderbutton3);
		speelveldscene = new Scene(speelveldpane, 1600, 900);
		speelveldpane.getStylesheets().add("Resources/Speelveld.css");
						
		
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
	public Button getExitButton(){
		return exitbutton;
	}
}
