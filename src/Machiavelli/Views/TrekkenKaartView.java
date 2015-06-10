package Machiavelli.Views;

import java.util.ArrayList;

import Machiavelli.Machiavelli;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Machiavelli.Models.GebouwKaart;
import Machiavelli.Models.Speler;

/**
 * @author Jamie Kalloe
 */

public class TrekkenKaartView extends Application
{
	private Speler speler;
	private Stage stage;
	private ArrayList<Button> kaartenButton;
	private ArrayList<GebouwKaart> kaartenLijst;
	private Button Kaart1, Kaart2;

	public TrekkenKaartView() {
		Stage stage = new Stage();
		stage.setTitle("Trekken Kaart");
//		this.speler = new Speler();
		
		this.kaartenButton = new ArrayList<Button>();
		this.Kaart1 = new Button("Kaart 1");
		this.Kaart2 = new Button("Kaart 2");
		
//		this.Kaart1.setGraphic(new ImageView(gebouwkaart.getImage));
		
		Text title = new Text("Maak je keuze:");
		title.setId("title");
		title.setFill(Color.WHITE);
		title.setLayoutX(420);
		title.setLayoutY(50);
		
		Pane stPane = new Pane();
		stPane.setId("ROOTNODE");
		stPane.getChildren().add(title);
		Rectangle rect = new Rectangle(1024, 768);
		rect.setArcHeight(60.0);
		rect.setArcWidth(60.0);
		stPane.setClip(rect);
		
		Scene scene = new Scene(stPane, 1024, 768);
		stage.initStyle(StageStyle.TRANSPARENT);
		scene.setFill(Color.TRANSPARENT);
		stPane.getStylesheets().add("Machiavelli/Resources/TrekkenKaartView.css");
		
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
