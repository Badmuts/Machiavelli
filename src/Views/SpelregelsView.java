package Views;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Models.Spelregels;

public class SpelregelsView extends Application {
	
	private Spelregels spelregels;
	private Stage stage;
	private Button closeButton;

	public SpelregelsView() throws IOException {
		Stage stage = new Stage();
		this.spelregels = new Spelregels();
		stage.setTitle("Spelregels Machiavelli");
		
		Text title = new Text("Spelregels");
		title.setId("title");
		title.setFill(Color.WHITE);
		title.setLayoutX(420);
		title.setLayoutY(50);
		
		Text text = new Text(this.spelregels.getSpelregels());
		text.setId("regels");
		text.setLayoutX(50);
		text.setLayoutY(100);
		
		this.closeButton = new Button("X");
		this.closeButton.setId("closeButton");
		this.closeButton.setLayoutX(984);
		this.closeButton.setLayoutY(15);

//		text.setContentDisplay
//		text.setFont(Font.font ("Roboto", 20));
		Pane stPane = new Pane();
		stPane.setId("ROOTNODE");
		stPane.getChildren().addAll(title, text, closeButton);
		Rectangle rect = new Rectangle(1024, 768);
		rect.setArcHeight(60.0);
		rect.setArcWidth(60.0);
		stPane.setClip(rect);
		
		Scene scene = new Scene(stPane, 1024, 768);
		stage.initStyle(StageStyle.TRANSPARENT);
		scene.setFill(Color.TRANSPARENT);
		stPane.getStylesheets().add("Resources/SpelregelsView.css");
		
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
	
	public Button getCloseButton() {
		return this.closeButton;
	}
}
