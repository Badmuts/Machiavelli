package Machiavelli.Views;

import Machiavelli.Controllers.SpelController;
import Machiavelli.Machiavelli;
import Machiavelli.Models.Spel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenuView{
    private Button startbutton;
    private Button exitbutton;
    private Button spelregels;
    private Button deelnemenknop;
    private Button hervattenknop;
    private Button nieuwspelknop;
    private Button exitbutton2;
    private Button spelregels2;
    private SpelController sc;
    //private Stage primaryStage;
    private Scene mainMenu;
    private Scene mainSelect;
    private Stage stage = Machiavelli.getInstance().getStage();
	
	public MainMenuView(SpelController sc, Spel sp){
		this.sc = sc;
		
		Pane mainMenuPane = new Pane();
		Pane mainSelectPane = new Pane();

		nieuwspelknop = new Button();
		hervattenknop = new Button();
		deelnemenknop = new Button();	
		startbutton = new Button();
		exitbutton = new Button();
		spelregels = new Button();
		exitbutton2 = new Button();
		spelregels2 = new Button();
		
		//Machiavelli tekst layout
		Text mainTx = new Text("Machiavelli");
		mainTx.setFill(Color.WHITE);
		DropShadow ds = new DropShadow();
		ds.setOffsetY(2);
		mainTx.setEffect(ds);
		mainTx.setScaleX(7);
		mainTx.setScaleY(6.5);
		mainTx.setLayoutX(780);
		mainTx.setLayoutY(320);
		
		Text mainTx2 = new Text("Machiavelli");
		mainTx2.setFill(Color.WHITE);
		mainTx2.setEffect(ds);
		mainTx2.setScaleX(7);
		mainTx2.setScaleY(6.5);
		mainTx2.setLayoutX(780);
		mainTx2.setLayoutY(170);
		
		//Knoppen defini�ren
		initButton(startbutton,"Kies spel", "buttonstart", 700,450,200f,75f);
		initButton(exitbutton,"Afsluiten", "buttonexit", 700,530,200f,75f);
		initButton(spelregels,"Spelregels", "buttonregels", 15,10,125f,50f);
		initButton(nieuwspelknop,"Nieuw spel", "gamekiezen", 700,290,200f,75f);
		initButton(hervattenknop,"Hervatten","gamekiezen", 700,370, 200f,75f);
		initButton(deelnemenknop,"Deelnemen", "gamekiezen", 700,450,200f,75f);
		initButton(exitbutton2,"Afsluiten", "buttonexit", 700,530,200f,75f);
		initButton(spelregels2,"Spelregels", "buttonregels", 15,10,125f,50f);
		
		Image spelregelsbg = new Image("Machiavelli/Resources/SpelregelsBorder.png");
		ImageView iv = new ImageView(spelregelsbg);
		ImageView iv2 = new ImageView(spelregelsbg);
		iv.setCache(true);
		iv.setFitWidth(200);
		iv2.setCache(true);
		iv2.setFitWidth(200);
		
		//toevoegen van elementen aan het frame
		mainMenuPane.getChildren().addAll(iv, startbutton,exitbutton,spelregels,mainTx);
		mainSelectPane.getChildren().addAll(iv2, nieuwspelknop,hervattenknop,deelnemenknop,exitbutton2,spelregels2,mainTx2);
		
		//Instellen wat er weergeven moet worden
		mainSelect = new Scene(mainSelectPane, 1440, 900);
		mainMenu = new Scene(mainMenuPane, 1440, 900);
		mainMenuPane.getStylesheets().add("Machiavelli/Resources/Menu.css");
		mainSelectPane.getStylesheets().add("Machiavelli/Resources/Menu.css");
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
		stage.setScene(mainMenu);
		stage.show();
	}

	public void showSelect(){
		stage.setScene(mainSelect);
    	stage.centerOnScreen();
		stage.show();
	}

	public Button getStartButton(){
		return startbutton;
	}

	public Button getExitButton(){
		return exitbutton;
	}

	public Button getSpelregelsButton(){
		return spelregels;
	}

	public Button getExitButton2(){
		return exitbutton2;
	}

	public Button getNieuwSpelKnop(){
		return nieuwspelknop;
	}

	public Button getDeelnemenKnop(){
		return deelnemenknop;
	}

	public Button getHervattenknop(){
		return hervattenknop;
	}

    public void StopStage(){
		this.stage.hide();
	}
}