package Machiavelli.Views;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Machiavelli.Controllers.InkomstenController;
import Machiavelli.Models.GebouwKaart;

/**
 * @author Jamie Kalloe
 */

public class TrekkenKaartView extends Application
{
	private Stage stage;
	private ArrayList<Button> kaartenButtons;
	private ArrayList<GebouwKaart> gebouwen = new ArrayList<GebouwKaart>();
	private Pane pane;
	private InkomstenController inkomstenController;
//	private Button Kaart1, Kaart2;

	public TrekkenKaartView(InkomstenController inkomstenController) {
		Stage stage = new Stage();
		
		stage.setTitle("Trekken Kaart");
//		this.speler = new Speler();
		
		this.kaartenButtons = new ArrayList<Button>();
		
//		this.Kaart2 = new Button("Kaart 2");
//		
//		this.Kaart1.setId("Kaart 1");
//		this.Kaart1.setLayoutX(100);
//		this.Kaart1.setLayoutY(80);
//		
//		this.Kaart2.setId("Kaart 2");
//		this.Kaart2.setLayoutX(700);
//		this.Kaart2.setLayoutY(80);
//		
//		this.kaartenButtons.add(this.Kaart1);
//		this.kaartenButtons.add(this.Kaart2);
		
		//image voor de button moet in de controller worden geregeld..
		
//		this.Kaart1.setGraphic(new ImageView(gebouwkaart.getImage));
		
		Text title = new Text("Maak je keuze:");
		title.setId("title");
		title.setFill(Color.WHITE);
		title.setLayoutX(380);
		title.setLayoutY(50);
		
		pane = new Pane();
		pane.setId("ROOTNODE");
		pane.getChildren().add(title);
		System.out.println("Voor loop");

		System.out.println("Na loop");
		Rectangle rect = new Rectangle(1024, 768);
		rect.setArcHeight(60.0);
		rect.setArcWidth(60.0);
		pane.setClip(rect);
		
		Scene scene = new Scene(pane, 1024, 768);
		stage.initStyle(StageStyle.TRANSPARENT);
		scene.setFill(Color.TRANSPARENT);
		pane.getStylesheets().add("Machiavelli/Resources/TrekkenKaartView.css");
		
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
	
	public ArrayList<Button> getButtonList()
	{
		System.out.println("Button list");
		return this.kaartenButtons;
	}
	
	public ArrayList<GebouwKaart> getGebouwen() {
		return this.gebouwen;
	}
	
	public void createGebouwView(GebouwKaart gebouw) throws RemoteException {
		Button newButton = new Button(gebouw.getNaam());
		newButton.setGraphic(new ImageView(gebouw.getImage()));
		newButton.setLayoutX(Math.random() * 111);
		newButton.setLayoutY(50);
		this.gebouwen.add(gebouw);
		this.kaartenButtons.add(newButton);
		pane.getChildren().add(newButton);
	}
	
	public Pane getPane()
	{
		return this.pane;
	}
}
