package Machiavelli.Controllers;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import Machiavelli.Machiavelli;
import Machiavelli.Models.Melding;
import Machiavelli.Views.MeldingView;

public class MeldingController {

	private Melding melding;
	private MeldingView meldingView;
	
	public MeldingController()
	{
		this.melding = new Melding();
		this.meldingView = new MeldingView();
		this.meldingView.getSluitButton().setOnAction((event) ->
		{
			cmdSluitMeldingView();
		});
	}
	
	public void cmdSetMelding(String message)
	{
		this.melding.setMelding(message);
		this.meldingView.getText().setText(cmdGetMelding());
	}
	
	public MeldingController build(String message)
	{
		this.melding.setMelding(message);
		this.meldingView.getText().setText(cmdGetMelding());
		
		return this;
	}
	
	public String cmdGetMelding()
	{
		return this.melding.getMeldig();
	}
	
	public void cmdWeergeefMeldingView()
	{
		StackPane pane = new StackPane();
    	
    	Pane old = new Pane();
    	old.getChildren().add(Machiavelli.getInstance().getStage().getScene().getRoot());
    	pane.getChildren().addAll(old, cmdGetPane());
    	
    	Scene scene = new Scene(pane, 1440, 900);
		Machiavelli.getInstance().getStage().setScene(scene);
	}
	
	public void cmdSluitMeldingView()
	{
		Pane newPane = new Pane();
    	Scene currentScene = Machiavelli.getInstance().getStage().getScene();

    	System.out.println("\nThe current scene contains the following nodes (panes): ");
    	for(Node node : currentScene.getRoot().getChildrenUnmodifiable())
    	{
    		System.out.println(node.idProperty());
    		if(currentScene.lookup("#meldingview").equals(node))
    		{
    			//deletes the spelregelview pane, from the nodelist of the scene...
    			newPane.getChildren().add(node);
    			
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
	
	public Pane cmdGetPane()
	{
		return this.meldingView.getPane();
	}
	
	public Button getSluitButton()
	{
		return this.meldingView.getSluitButton();
	}
}
