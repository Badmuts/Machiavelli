package Machiavelli.Controllers;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import Machiavelli.Machiavelli;
import Machiavelli.Models.GebouwKaart;
import Machiavelli.Models.Speler;
import Machiavelli.Views.KiesInkomstenView;
import Machiavelli.Views.TrekkenKaartView;

/**
 * @author Jamie Kalloe
 *
 */
public class InkomstenController
{
	// Variables
	private Speler speler; // ?
//	Beurt beurt;
	private TrekkenKaartView trekkenKaartView;
	
	public InkomstenController(Speler speler) throws RemoteException
	{
		this.speler = speler;
		this.cmdTrekkenKaart();
		
//		this.beurt = new Beurt(beurt.getSpeler().getSpel(), beurt.getSpelerLijst());
		
		
//		this.trekkenKaartView.getButtonList().get(0).setOnAction(event -> System.out.println("Kaart 1"));
//		this.trekkenKaartView.getButtonList().get(1).setOnAction(event -> System.out.println("Kaart 2"));
	}
	public void cmdTrekkenKaart() throws RemoteException
	{
		this.trekkenKaartView = new TrekkenKaartView(this);
		ArrayList<GebouwKaart> getrokkenKaarten = speler.trekkenKaart();
		
		System.out.println("Getrokken kaarten: "+ getrokkenKaarten.size());
		
		for (GebouwKaart gebouw: getrokkenKaarten) {
			trekkenKaartView.createGebouwView(gebouw);
		}
		
		System.out.println("Buttons: " + trekkenKaartView.getButtonList().size());
		
		for (Button button: trekkenKaartView.getButtonList()) {
															//neem de index van de arraylist per object / button
			button.setOnAction(event -> this.cmdKiezenKaart(this.trekkenKaartView.getButtonList().indexOf(button)));
		}
	}
	
	public void cmdKiezenKaart(int gekozenKaart)
	{
		try
		{
			System.out.println("Gekozenkaart Index: (vanuit ActionEvent" + gekozenKaart);
			this.speler.selecterenKaart(this.trekkenKaartView.getGebouwen(), gekozenKaart);
			showHand(this.speler);
			
			trekkenKaartView.getStage().close();
			//sluit de view na het trekken van de kaart.
		}
		catch(RemoteException e)
		{
			e.printStackTrace();
		}
	}
	
	public void cmdKiezenGoud()
	{
		
	}
	
	public void weergeefTrekkenKaartView()
	{
		//zet het venster boven alle andere venster.
		this.trekkenKaartView.getStage().setAlwaysOnTop(true);
		this.trekkenKaartView.getStage().show();
		
//		StackPane pane = new StackPane();
//		Pane p = new Pane();
//		//GET PANE FROM EXISTING SCENE.
//		p.getChildren().add(Machiavelli.getInstance().getStage().getScene().getRoot());
//		pane.getChildren().addAll(p, trekkenKaartView.getPane());
//		//setpane into inkomstenview... 
////		pane.getStylesheets().add("Machiavelli/Resources/Speelveld.css");
//		Scene scene = new Scene(pane, 1600, 900);
//		Machiavelli.getInstance().getStage().setScene(scene);
		
		//TODO: laat de main stage zien, met een trekkenkaart pane (in de nieuwe scene) en een speelveldviewpane.
	}
	
	public void showHand(Speler speler) throws RemoteException
    {
        ArrayList<GebouwKaart> lst = speler.getHand().getKaartenLijst();
        System.out.println("Kaarten in hand:");
        for(int i = 0; i < speler.getHand().getKaartenLijst().size(); i++)
        {
            System.out.println(i + 1 + ") " + lst.get(i).getNaam() + " / " + lst.get(i).getType());
        }
        System.out.println();
    }

}
