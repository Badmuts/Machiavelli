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

    	
    	Scene scene = new Scene(pane, 1440, 900);
		Machiavelli.getInstance().getStage().setScene(scene);
	}
	
	public Pane cmdGetPane()
	{
		return this.spelRegelsView.getPane();
	}
	
	public void cmdSluitSpelregelView()
	{
		Pane newPane = new Pane();
    	Scene currentScene = Machiavelli.getInstance().getStage().getScene();

    	System.out.println("\nThe current scene contains the following nodes (panes): ");
    	for(Node node : currentScene.getRoot().getChildrenUnmodifiable())
    	{
    		System.out.println(node.idProperty());
    		if(currentScene.lookup("#spelregelview").equals(node))
    		{
    			//deletes the spelregelview pane, from the nodelist of the scene...
    			newPane.getChildren().add(node);
    			
    			//java.lang.UnsupportedOperationException
//    			currentScene.getRoot().getChildrenUnmodifiable().remove(currentScene.getRoot().getChildrenUnmodifiable().indexOf(node));
    			System.out.println("\nVerwijderd: " + node.getId());
    			break;
    		}
    	}
    	
    	newPane = null;
    	
    	//show the nodes in the current list.
    	System.out.println("\nThe current scene contains the following nodes (panes): ");
    	for(Node node : currentScene.getRoot().getChildrenUnmodifiable())
    	{
    		System.out.println(node.idProperty());
    	}
	}
}
