package Machiavelli.Controllers;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import Machiavelli.Machiavelli;
import Machiavelli.Models.Spelregels;
import Machiavelli.Views.SpelregelsView;

/**
 * @author Jamie Kalloe
 */

public class RaadplegenSpelregelsController {
	
	//Variables
	private SpelregelsView spelRegelsView;
	private Spelregels spelRegels;
	
	public RaadplegenSpelregelsController()
	{
		try {
			this.spelRegelsView = new SpelregelsView();
			this.spelRegelsView.getCloseButton().setOnAction(event -> this.spelRegelsView.close());
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void cmdWeergeefSpelregels() {
		this.spelRegelsView.show();
		
//		StackPane pane = new StackPane();
//		Pane p = new Pane();
//		//GET PANE FROM EXISTING SCENE.
//		p.getChildren().add(Machiavelli.getInstance().getStage().getScene().getRoot());
//		pane.getChildren().addAll(p, this.spelRegelsView.getPane());
//		//setpane into inkomstenview... 
////		pane.getStylesheets().add("Machiavelli/Resources/Speelveld.css");
//		Scene scene = new Scene(pane, 1600, 900);
//		Machiavelli.getInstance().getStage().setScene(scene);
		
		//remove the lines under...!
		//de ic controller maakt de view, en weergeeft hem met de weergaafTrekkenKaartView method.
//		InkomstenController ic = new InkomstenController(new Speler(new Spel(new Stage())));
//		ic.weergeefTrekkenKaartView();
	}
	
	public void cmdGetPane()
	{
		this.spelRegelsView.getPane();
	}
}
