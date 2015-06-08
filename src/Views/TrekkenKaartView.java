package Views;

import java.util.ArrayList;

import Models.GebouwKaart;
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

	private Stage stage;
	
	//test var
	private ArrayList<Button> testKaarten;
	
	public TrekkenKaartView()
	{
		Stage stage = new Stage();
		Text title = new Text();
		title.setId("trekkenKaartTitle");
		title.setFill(Color.WHITE);
		title.setLayoutX(420);
		title.setLayoutY(50);
		title.setText("Maak je keuze:");

		Pane stPane = new Pane();
		stPane.setId("ROOTNODE");
		
		for(int i = 0; i < testKaarten.size(); i++)
		{
			stPane.getChildren().addAll(title, testKaarten.get(i));
		}
		
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
	
	public Stage getStage()
	{
		return this.stage;
	}
}
