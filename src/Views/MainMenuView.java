package Views;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Controllers.SpelController;
import Models.Spel;

public class MainMenuView{
		private Button startbutton;
		private Button exitbutton;
		private Button spelregels;
		private SpelController sc;
		private Stage primaryStage;
		private Scene mainMenu; 
		
		
	public void setButton(Button button, int posx, int posy, float sizeX, float sizeY){
		button.setLayoutX(posx);
		button.setLayoutY(posy);
		button.setScaleX(sizeX);
		button.setScaleY(sizeY);
	}
	
		
	public MainMenuView(SpelController sc, Spel sp){
		this.sc = sc;
		
		Pane mainMenuPane = new Pane();
		mainMenuPane.setStyle("-fx-background-color: #48443c");
			
		startbutton = new Button();
		exitbutton = new Button();
		spelregels = new Button();

		startbutton.setText("Kies spel");
		startbutton.setId("buttonstart");
		//startbutton.setOnAction(this);
		
		exitbutton.setText("Afsluiten");
		exitbutton.setId("buttonexit");
		//exitbutton.setOnAction(this);
		
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
		mainTx.setLayoutY(320);
		
		setButton(startbutton,780,450,3.5f,3f);
		setButton(exitbutton,780,530,3.5f,2.7f);
		setButton(spelregels,50,30,2f,2f);
		
		//toevoegen van elementen aan het frame
		mainMenuPane.getChildren().addAll(startbutton,exitbutton,spelregels,mainTx);
		
		//Instellen wat er weergeven moet worden
		mainMenu = new Scene(mainMenuPane, 1600, 900);
		mainMenuPane.getStylesheets().add("Views/stylecss.css");
	}
	public void show(Stage stage){
		stage.setTitle("Machiavelli");
		stage.setScene(mainMenu);
		stage.show();
	}
	public Button getStartButton(){
		return startbutton;
	}
	public Button getExitButton(){
		return exitbutton;
	}
	public Button getSpelregelsbutton(){
		return spelregels;
	}

	/*	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Machiavelli");
		primaryStage.setResizable(false);
		primaryStage.show();
		}
		@Override
	public void handle(ActionEvent event) {
		if(event.getSource() == startbutton){
			System.out.println("Nog geen werkelijke implementatie");
		}
		if(event.getSource() == exitbutton){
			Platform.exit();
		}
		if(event.getSource() == spelregels){
			
		}
	}*/
}
