package Machiavelli.Views;

import Machiavelli.Machiavelli;
import Machiavelli.Models.Spelregels;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SpelregelsView {
	
	private Spelregels spelregels;
	private Stage stage = Machiavelli.getInstance().getStage();
	private Button closeButton;
    private Scene scene;

	public SpelregelsView() throws IOException {
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
        /**
         * Om twee Pane's over elkaar heen te plaatsen zou dat via een
         * StackPane moeten voor zover ik heb kunnen lezen:
         *
         * https://docs.oracle.com/javafx/2/layout/builtin_layouts.htm
         * https://docs.oracle.com/javafx/2/api/javafx/scene/layout/StackPane.html
         *
         * Hier moet nog even naar gekeken worden.
         */
		Pane stPane = new Pane();
		stPane.setId("ROOTNODE");
		stPane.getChildren().addAll(title, text, closeButton);
		Rectangle rect = new Rectangle(1024, 768);
		rect.setArcHeight(60.0);
		rect.setArcWidth(60.0);
		stPane.setClip(rect);
        stPane.getStylesheets().add("Machiavelli/Resources/SpelregelsView.css");

        this.scene = new Scene(stPane, 1024, 768);
        this.scene.setFill(Color.TRANSPARENT);
        this.stage.initStyle(StageStyle.TRANSPARENT);
	}
	
	public void show() {
        stage.setScene(scene);
        stage.centerOnScreen();
		stage.show();
	}

    public void close() {
        stage.close();
    }

	public Button getCloseButton() {
		return this.closeButton;
	}
}
