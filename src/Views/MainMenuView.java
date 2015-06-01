package Views;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Models.MenuController;

public class MainMenuView extends Application implements EventHandler<ActionEvent>{
		private Button startbutton;
		private Button exitbutton;
		private Button spelregels;
		private MenuController mc;
		
	public void setSizeButton(Button button, int posx, int posy, float sizeX, float sizeY){
		button.setLayoutX(posx);
		button.setLayoutY(posy);
		button.setScaleX(sizeX);
		button.setScaleY(sizeY);
	}
		
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Machiavelli");
		primaryStage.setResizable(false);
		
		Pane root = new Pane();
		root.setStyle("-fx-background-color: #48443c");
		
		startbutton = new Button();
		exitbutton = new Button();
		spelregels = new Button();
		
		startbutton.setText("Kies spel");
		startbutton.setId("buttonstart");
		startbutton.setOnAction(this);
		exitbutton.setText("Afsluiten");
		exitbutton.setId("buttonexit");
		exitbutton.setOnAction(this);
		spelregels.setText("Spelregels");
		spelregels.setId("buttonregels");
		
		
		Text mainTx = new Text("Machiavelli");
		mainTx.setFill(Color.WHITE);
		DropShadow ds = new DropShadow();
		ds.setOffsetY(2);
		mainTx.setEffect(ds);
		mainTx.setScaleX(7);
		mainTx.setScaleY(6.5);
		mainTx.setLayoutX(780);
		mainTx.setLayoutY(320);
		
		setSizeButton(startbutton,780,450,3.5f,3f);
		setSizeButton(exitbutton,780,530,3.5f,2.7f);
		setSizeButton(spelregels,50,30,2f,2f);
		
		//toevoegen van elementen aan het frame
		root.getChildren().addAll(startbutton,exitbutton,spelregels,mainTx);
		
		//Instellen wat er weergeven moet worden
		Scene scene = new Scene(root, 1600, 900);
		primaryStage.setScene(scene);
		scene.getStylesheets().add("Views/stylecss.css");
		primaryStage.show();
	}
	@Override
	public void handle(ActionEvent event) {
		if(event.getSource() == startbutton){
			System.out.println("Nog geen werkelijke implementatie");
		}
		if(event.getSource() == exitbutton){
			System.exit(0);
		}
		if(event.getSource() == spelregels){
			
		}
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
