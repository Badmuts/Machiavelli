package Machiavelli.Views;

import Machiavelli.Controllers.SpeelveldController;
import Machiavelli.Interfaces.Observers.SpeelveldObserver;
import Machiavelli.Interfaces.Remotes.SpeelveldRemote;
import Machiavelli.Machiavelli;
import Machiavelli.Models.Speelveld;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.rmi.RemoteException;

public class SpeelveldView implements SpeelveldObserver {
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
	private Stage stage = Machiavelli.getInstance().getStage();
	private Rectangle buttonholder;
	private Rectangle karakterholder;
	private Rectangle kaartholder;
	private Image portretmagier;

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
		
		initButton(gebruikEigenschap,"Button","gamebutton",1400,770, 180f,60f);
		initButton(exitbutton,"Afsluiten","buttonexit", 1400, 835, 180f,60f);
		initButton(spelregels,"Spelregels", "buttonregels", 15,10,125f,50f);
		initButton(opslaanknop,"Opslaan","buttonsave",1200, 835, 180f, 60f);
		initButton(placeholderbutton1, "Inkomsten", "gamebutton", 1200, 770, 180f, 60f);
		initButton(placeholderbutton2, "Bouwen", "gamebutton", 1400, 705, 180f, 60f);
		initButton(placeholderbutton3, "Eigenschap", "gamebutton", 1200, 705, 180f, 60f);
		
		buttonholder = new Rectangle(1175,680,425,250);
		buttonholder.setFill(Color.DIMGRAY);
		
		karakterholder = new Rectangle(0, 680, 250, 250);
		karakterholder.setFill(Color.DIMGRAY);
		
		kaartholder = new Rectangle(250, 680,925,250);
		kaartholder.setFill(Color.GRAY);
		
		Pane speelveldpane = new Pane();
		
		Image spelregelsbg = new Image("Machiavelli/Resources/SpelregelsBorder.png");
		ImageView iv = new ImageView(spelregelsbg);
		iv.setCache(true);
		iv.setFitWidth(200);
        
		//De portretten moeten nog in andere klassen worden opgeslagen, dit is een test.
		portretmagier = new Image("Machiavelli/Resources/Portrait-Magier.png");
		ImageView portretview = new ImageView(portretmagier);
		portretview.setCache(true);
		portretview.setScaleX(0.45);
		portretview.setScaleY(0.45);
		portretview.setLayoutX(-60);
		portretview.setLayoutY(615);
		
		speelveldpane.getChildren().addAll(iv,buttonholder,karakterholder,kaartholder, gebruikEigenschap, exitbutton,spelregels, opslaanknop,placeholderbutton1,placeholderbutton2,placeholderbutton3,portretview);
		speelveldscene = new Scene(speelveldpane, 1600, 900);
		speelveldpane.getStylesheets().add("Machiavelli/Resources/Speelveld.css");
						
		this.show();
	}

	public void initButton(Button button,String tekst,String id, int posx, int posy, float sizeX, float sizeY){
		button.setText(tekst);
		button.setId(id);
		button.setLayoutX(posx);
		button.setLayoutY(posy);
		button.setMinWidth(sizeX);
		button.setMinHeight(sizeY);
	}

	public void show(){
		stage.setScene(speelveldscene);
        stage.centerOnScreen();
		stage.show();
	}

	public Button getSpelregels() {
		return spelregels;
	}
	
	public Button getKiesInkomstenButton()
	{
		return this.placeholderbutton1;
	}

	public Button getExitButton(){
		return exitbutton;
	}

	@Override
	public void modelChanged(SpeelveldRemote speelveld) throws RemoteException {
		// Doe iets?
	}
}
