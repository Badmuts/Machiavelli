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
import Machiavelli.Controllers.RaadplegenSpelregelsController;
import Machiavelli.Interfaces.Observers.SpelregelsObserver;
import Machiavelli.Interfaces.Remotes.SpelregelsRemote;
import Machiavelli.Models.Spelregels;

import com.sun.corba.se.impl.orbutil.graph.Node;

public class SpelregelsView implements SpelregelsObserver {
	@Override
	public void modelChanged(SpelregelsRemote spelregels) throws RemoteException {

	}

	private Spelregels spelregels;
	private Button closeButton;
    private Pane stPane;

	public SpelregelsView() throws IOException {
		this.spelregels = new Spelregels();
		
		Text title = new Text("Spelregels");
		title.setId("title");
		title.setFill(Color.WHITE);
		title.setLayoutX(720);
		title.setLayoutY(120);
		
		Text text = new Text(this.spelregels.getSpelregels());
		text.setId("regels");
		text.setLayoutX(350);
		text.setLayoutY(180);
		
		this.closeButton = new Button("X");
		this.closeButton.setId("closeButton");
		this.closeButton.setLayoutX(1280);
		this.closeButton.setLayoutY(90);

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
		stPane.setPrefSize(1440, 900);
		
		Rectangle rect = new Rectangle(1024, 768);
		rect.setId("spelregelwindow");
		rect.setLayoutX(300);
		rect.setLayoutY(70);
		rect.setArcHeight(60.0);
		rect.setArcWidth(60.0);
		stPane.setClip(rect);
        stPane.getStylesheets().add("Machiavelli/Resources/SpelregelsView.css");
        
        
	}
	
	public Button getCloseButton() {
		return this.closeButton;
	}
	
	public Pane getPane()
	{
		return this.stPane;
	}
}
