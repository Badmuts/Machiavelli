package Machiavelli.Views;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Machiavelli.Machiavelli;
import Machiavelli.Controllers.InkomstenController;
import Machiavelli.Models.GebouwKaart;

/**
 * @author Jamie Kalloe
 */

public class TrekkenKaartView
{
	private ArrayList<Button> kaartenButtons;
	private ArrayList<GebouwKaart> gebouwen = new ArrayList<GebouwKaart>();
	private Pane pane;

	public TrekkenKaartView(InkomstenController inkomstenController) {
		
		this.kaartenButtons = new ArrayList<Button>();
		
		Text title = new Text("Maak je keuze:");
		title.setId("title");
		title.setFill(Color.WHITE);
		title.setLayoutX(660);
		title.setLayoutY(50);
		
		pane = new Pane();
		pane.setId("ROOTNODE");
		pane.getChildren().add(title);
		System.out.println("Voor loop");

		System.out.println("Na loop");
		Rectangle rect = new Rectangle(1440, 900);
		pane.setClip(rect);
		pane.getStylesheets().add("Machiavelli/Resources/TrekkenKaartView.css");
	}

	public ArrayList<Button> getButtonList()
	{
		System.out.println("Button list");
		return this.kaartenButtons;
	}
	
	public ArrayList<GebouwKaart> getGebouwen() {
		return this.gebouwen;
	}
	
	public void createGebouwView(GebouwKaart gebouw) throws RemoteException {
		Button newButton = new Button();
		newButton.setGraphic(new ImageView(gebouw.getImage()));
		newButton.setLayoutX(Math.random() * 111);
		newButton.setLayoutY(50);

		//for css styling.
		newButton.setId("Kaart");
		
		this.gebouwen.add(gebouw);
		this.kaartenButtons.add(newButton);
	}
	
	public void addButtonsToView()
	{
		HBox hBox = new HBox();
		hBox.setSpacing(30.0);
		hBox.setPadding(new Insets(15, 15, 15, 15));
        hBox.setSpacing(90.0);
        
        for(Button button : this.kaartenButtons)
        {
        	hBox.getChildren().add(button);
        }
        
        hBox.setLayoutY(120);
        hBox.setLayoutX(300);
        this.pane.getChildren().add(hBox);
	}
	
	public void cmdWeergeefTrekkenKaartView() {
    	StackPane pane = new StackPane();
    	
    	Pane old = new Pane();
    	old.getChildren().add(Machiavelli.getInstance().getStage().getScene().getRoot());
    	pane.getChildren().addAll(old, this.getPane());

    	Scene scene = new Scene(pane, 1440, 900);
		Machiavelli.getInstance().getStage().setScene(scene);
	}
	
	public void cmdSluitTrekkenKaartView()
	{
		Pane newPane = new Pane();
    	Scene currentScene = Machiavelli.getInstance().getStage().getScene();

    	System.out.println("\nThe current scene contains the following nodes (panes): ");
    	for(Node node : currentScene.getRoot().getChildrenUnmodifiable())
    	{
    		System.out.println(node.idProperty());
    		if(currentScene.lookup("#ROOTNODE").equals(node))
    		{
    			newPane.getChildren().add(node);
    			
    			System.out.println("\nVerwijderd: " + node.getId());
    			break;
    		}
    	}
    	
    	newPane = null;
    	
    	//show the nodes in the current list.
    	System.out.println("\nThe current scene contains the following  nodes (panes): ");
    	for(Node node : currentScene.getRoot().getChildrenUnmodifiable())
    	{
    		System.out.println(node.idProperty());
    	}
	}
	
	public Pane getPane()
	{
		return this.pane;
	}
}
