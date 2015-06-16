package Machiavelli.Views;

/**
 * @author Jamie Kalloe
 */

import java.io.IOException;
import java.rmi.RemoteException;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Machiavelli.Machiavelli;
import Machiavelli.Interfaces.Observers.SpelregelsObserver;
import Machiavelli.Interfaces.Remotes.SpelregelsRemote;
import Machiavelli.Models.Spelregels;

import com.sun.corba.se.impl.orbutil.graph.Node;

public class SpelregelsView implements SpelregelsObserver {
	@Override
	public void modelChanged(SpelregelsRemote spelregels) throws RemoteException {

	}

	private Spelregels spelregels;
	private Stage stage = Machiavelli.getInstance().getStage();
	private Button closeButton;
    private Scene scene;
    private Pane stPane;

	public SpelregelsView() throws IOException {
		this.spelregels = new Spelregels();
		stage.setTitle("Spelregels Machiavelli");
		
		Text title = new Text("Spelregels");
		title.setId("title");
		title.setFill(Color.WHITE);
		title.setLayoutX(720);
		title.setLayoutY(50);
		
		Text text = new Text(this.spelregels.getSpelregels());
		text.setId("regels");
		text.setLayoutX(50);
		text.setLayoutY(100);
		
		this.closeButton = new Button("X");
		this.closeButton.setId("closeButton");
		this.closeButton.setLayoutX(1555);
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
		this.stPane = new Pane();
		stPane.setId("spelregelview");
		stPane.getChildren().addAll(title, text, closeButton);
		Rectangle rect = new Rectangle(1600, 900);
//		rect.setArcHeight(60.0);
//		rect.setArcWidth(60.0);
		stPane.setClip(rect);
        stPane.getStylesheets().add("Machiavelli/Resources/SpelregelsView.css");
	}
	
    public void close() {
        stage.close();
    }

	public Button getCloseButton() {
		return this.closeButton;
	}
	
	public Pane getPane()
	{
		return this.stPane;
	}
}
