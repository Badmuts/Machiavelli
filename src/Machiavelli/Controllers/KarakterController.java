package Machiavelli.Controllers;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import Machiavelli.Machiavelli;
import Machiavelli.Factories.KarakterFactory;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Models.Spel;
import Machiavelli.Models.Speler;
import Machiavelli.Views.KiesKarakterView;

/**
 * Created by daanrosbergen on 03/06/15.
 */
public class KarakterController {

	//TODO: krijg speler van beurt..
    private Speler speler = new Speler();
    private KiesKarakterView karakterView = new KiesKarakterView();
    
    
    public KarakterController() throws RemoteException
    {
    	//delete the new spel, this is for testing only.
    	this.speler.addSpel(new Spel(1));
    	cmdTrekkenKaart();
    }
//
//    public KarakterController(Speler speler) {
//        this.speler = speler;
//    }
//
//    public void cmdGebruikKarakterEigenschap() {
//        Karakter karakter = this.speler.getKarakter();
//        karakter.gebruikEigenschap();
//    }
    
    public void cmdTrekkenKaart() throws RemoteException
    {
//    	ArrayList<Karakter> karakterLijst = this.speler.getSpel().getKarakterFactory().getKarakters();
    	KarakterFactory karakterFactory = this.speler.getSpel().getKarakterFactory();
    	
    	for (Karakter karakter : karakterFactory.getKarakters()) {
			karakterView.createKarakterView(karakter);
		}
    	
    	this.karakterView.addButtonsToView();
    	
    	for (Button button: karakterView.getButtonList()) 
    	{
    		try 
    		{
    			int buttonNumber = karakterView.getButtonList().indexOf(button);
    			
    			button.setOnAction((event) -> 
    			{
    				try
    				{
	    				this.speler.setKarakter(karakterFactory.getKarakterByNumber(buttonNumber));
	    				
	    				cmdSluitKiesKarakterView();
    				}
    				catch(Exception e)
    				{
    					e.printStackTrace();
    				}
    			});
			} 
    		catch (Exception e) {
				e.printStackTrace();
			}
    	}
    }
    
    public void cmdWeergeefKiesKarakterView()
    {
    	StackPane pane = new StackPane();
    	Pane old = new Pane();
    	
    	old.getChildren().add(Machiavelli.getInstance().getStage().getScene().getRoot());
    	pane.getChildren().addAll(old, karakterView.getPane());
    	
    	Scene scene = new Scene(pane, 1440, 900);
		Machiavelli.getInstance().getStage().setScene(scene);
    }
    
    public void cmdSluitKiesKarakterView()
    {
    	Pane newPane = new Pane();
    	Scene currentScene = Machiavelli.getInstance().getStage().getScene();

    	System.out.println("\nThe current scene contains the following nodes (panes): ");
    	for(Node node : currentScene.getRoot().getChildrenUnmodifiable())
    	{
    		System.out.println(node.idProperty());
    		if(currentScene.lookup("#kieskarakterview").equals(node))
    		{
    			//deletes the spelregelview pane, from the nodelist of the scene...
    			newPane.getChildren().add(node);
    			
    			System.out.println("\nVerwijderd: " + node.getId());
    			break;
    		}
    	}
    	
    	newPane = null;
//    	
    	//show the nodes in the current list.
    	System.out.println("\nThe current scene contains the following nodes (panes): ");
    	for(Node node : currentScene.getRoot().getChildrenUnmodifiable())
    	{
    		System.out.println(node.idProperty());
    	}
    }
}
