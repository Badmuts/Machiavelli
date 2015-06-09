package Views;

import java.io.IOException;
import java.util.ArrayList;

import Models.GebouwKaart;
import Models.Speler;
import Models.Spelregels;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author Jamie Kalloe
 */

public class TrekkenKaartView extends Application
{
	private Speler speler;
	private Stage stage;

	public TrekkenKaartView() {
		Stage stage = new Stage();
		stage.setTitle("Trekken Kaart");
		
		Text title = new Text("Trek een kaart:");
		title.setId("title");
		title.setFill(Color.WHITE);
		title.setLayoutX(420);
		title.setLayoutY(50);
		
		Text title2 = new Text("Trek een kaart:1");
		title.setId("title2");
		title.setFill(Color.WHITE);
		title.setLayoutX(420);
		title.setLayoutY(50);
		
		Text title3 = new Text("Trek een kaart:3");
		title.setId("title3");
		title.setFill(Color.WHITE);
		title.setLayoutX(420);
		title.setLayoutY(50);
		
		Pane stPane = new Pane();
		stPane.setId("ROOTNODE");
		stPane.getChildren().add(title);
		stPane.getChildren().add(title2);
		stPane.getChildren().add(title3);
		Rectangle rect = new Rectangle(1024, 768);
		rect.setArcHeight(60.0);
		rect.setArcWidth(60.0);
		stPane.setClip(rect);
		
		Scene scene = new Scene(stPane, 1024, 768);
		stage.initStyle(StageStyle.TRANSPARENT);
		scene.setFill(Color.TRANSPARENT);
		stPane.getStylesheets().add("Resources/TrekkenKaartView.css");
		
		stage.setScene(scene);
		stage.setResizable(false);
		this.stage = stage;
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
	}
	
	public Stage getStage() {
		return this.stage;
	}
}
