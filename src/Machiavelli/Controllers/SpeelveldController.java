package Machiavelli.Controllers;

import java.rmi.RemoteException;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import Machiavelli.Machiavelli;
import Machiavelli.Models.Speelveld;
import Machiavelli.Views.KiesInkomstenView;
import Machiavelli.Views.SpeelveldView;

public class SpeelveldController {
	private Speelveld speelveld;
	private SpeelveldView speelveldview;
	private KiesInkomstenView inkomstenView;
	private Scene scene;
	
	public SpeelveldController(Speelveld speelveld) throws RemoteException {
		this.speelveld = speelveld;
		this.speelveldview = new SpeelveldView(this, this.speelveld);
		
		speelveldview.getExitButton().setOnAction(event -> System.exit(0));
		
		this.inkomstenView = new KiesInkomstenView();
		
//		this.speelveldview.getKiesInkomstenButton().setOnAction(event -> inkomstenView.show());
		this.speelveldview.getKiesInkomstenButton().setOnAction((event) -> {
			System.out.println("inkomsten event");
			StackPane pane = new StackPane();
			Pane speeldveldPane = new Pane();
			//GET PANE FROM EXISTING SCENE.
			speeldveldPane.getChildren().add(Machiavelli.getInstance().getStage().getScene().getRoot());
			pane.getChildren().addAll(speeldveldPane, inkomstenView.getPane());
			//setpane into inkomstenview... 
			this.scene = new Scene(pane, 1600, 900);
			Machiavelli.getInstance().getStage().setScene(this.scene);
		});
		
		this.speelveldview.show();
	}
}
