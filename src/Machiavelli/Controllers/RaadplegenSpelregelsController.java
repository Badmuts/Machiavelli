package Machiavelli.Controllers;

import java.rmi.RemoteException;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import Machiavelli.Machiavelli;
import Machiavelli.Models.Spelregels;
import Machiavelli.Views.KiesInkomstenView;
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
			this.spelRegelsView.getCloseButton().setOnAction(event -> cmdSluitSpelregelView());
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void cmdWeergeefSpelregels() {
    	StackPane pane = new StackPane();
//    	StackPane old = (StackPane) Machiavelli.getInstance().getStage().getScene().getRoot();
    	
    	Pane old = new Pane();
    	old.getChildren().add(Machiavelli.getInstance().getStage().getScene().getRoot());
    	pane.getChildren().addAll(old, cmdGetPane());

    	
    	Scene scene = new Scene(pane, 1600, 900);
		Machiavelli.getInstance().getStage().setScene(scene);
	}
	
	public Pane cmdGetPane()
	{
		return this.spelRegelsView.getPane();
	}
	
	public void cmdSluitSpelregelView()
	{
		Pane newPane = new Pane();
    	Scene oldScene = Machiavelli.getInstance().getStage().getScene();
//    	for(Node node : old.getChildren())
//    	{
//    		if(node.getId() != null && new String(node.getId()).equals("spelregelview"))
//    		{
//    			System.out.println("Speelveld pane gevonden");
//    			break;
//    		}
//    		newPane.getChildren().addAll(node);
//    	}
    	
//    	for(Node node : old.getChildren())
//    	{
//    		if(node.getId() != null && new String(node.getId()).equals("spelregelview"))
//    		{
//    			System.out.println("Speelveld pane gevonden");
//    			old.getChildren().remove(old.getChildren().indexOf(node));
//    			System.out.println("removed old pane from the scene");
//    			break;
//    		}
////    		newPane.getChildren().addAll(node);
//    		System.out.println(node.getId());
//    	}

//    	System.out.println(sc.lookup("#spelregelview").idProperty());
//    	System.out.println(sc.lookup("#spelregelview").getId());
    	
    	for(Node node : Machiavelli.getInstance().getStage().getScene().getRoot().getChildrenUnmodifiable())
    	{
    		System.out.println(node.idProperty());
    		if(oldScene.lookup("#spelregelview").equals(node))
    		{
    			newPane.getChildren().add(node);
    			System.out.println(node.idProperty());
    			break;
    		}
    	}
    	newPane = null;
    	
//    	Scene scene = new Scene(newPane, 1600, 900);
//		Machiavelli.getInstance().getStage().setScene(scene);
	}
}
