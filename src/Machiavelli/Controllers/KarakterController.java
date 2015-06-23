package Machiavelli.Controllers;

import java.rmi.RemoteException;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
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
    private Speler speler;
    private KiesKarakterView karakterView = new KiesKarakterView();
    private Karakter target;
    
    public KarakterController(Speler speler) throws RemoteException
    {
    	this.target = null;
    	this.speler = speler;
    	Spel spel = new Spel();
    	spel.createNewSpel(1);
    	this.speler.addSpel(spel);
    	
//    	cmdTrekkenKaart();
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
    
    public void cmdKiesKarakter() throws RemoteException
    {
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
    					this.target = karakterFactory.getKarakterByNumber(buttonNumber);
	    				this.speler.setKarakter(target);
    				}
    				catch(Exception e)
    				{
    					e.printStackTrace();
    				}
    				
    				//test if the karakter is deleted from the factory and close the view.
    				finally
    				{
    					try 
    					{
							for(Karakter karakter : karakterFactory.getKarakters())
							{
								System.out.println(karakter.getNaam() + " " + karakterFactory.getKarakters().indexOf(karakter));
							}
							
							System.out.println("De speler is een: " + this.speler.getKarakter().getNaam());
							
							cmdSluitKiesKarakterView();
		    				
		    				new MeldingController().build("Je bent deze ronde een " + this.getTarget().getNaam()).cmdWeergeefMeldingView();
						} 
    					catch (Exception e) 
    					{
							e.printStackTrace();
						}
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
    	
    	FadeTransition ft = new FadeTransition(Duration.millis(700), pane);
    	ft.setFromValue(0.7);
    	ft.setToValue(1.0);
    	ft.play();
    	
    	Scene scene = new Scene(pane, 1440, 900);
		Machiavelli.getInstance().getStage().setScene(scene);
    }
    
    public Karakter getTarget()
    {
    	return this.target;
    }
    
    public Speler getSpeler()
    {
    	return this.speler;
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
    			//deletes the kieskarakterview pane, from the nodelist of the scene...
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
