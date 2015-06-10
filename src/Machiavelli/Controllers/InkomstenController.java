package Machiavelli.Controllers;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import Machiavelli.Models.GebouwKaart;
import Machiavelli.Models.Speler;
import Machiavelli.Views.TrekkenKaartView;

/**
 * @author Jamie Kalloe
 *
 */
public class InkomstenController implements EventHandler<ActionEvent>
{
	// Variables
	private Speler speler; // ?
//	Beurt beurt;
	private TrekkenKaartView trekkenKaartView;
	
	public InkomstenController(Speler speler)
	{
		this.speler = speler;
		this.cmdTrekkenKaart();
		
//		this.beurt = new Beurt(beurt.getSpeler().getSpel(), beurt.getSpelerLijst());
		
		
//		this.trekkenKaartView.getButtonList().get(0).setOnAction(event -> System.out.println("Kaart 1"));
//		this.trekkenKaartView.getButtonList().get(1).setOnAction(event -> System.out.println("Kaart 2"));
	}
	private static int gekozenKaartIndex = 0;
	public void cmdTrekkenKaart()
	{
		this.trekkenKaartView = new TrekkenKaartView(this);
		ArrayList<GebouwKaart> getrokkenKaarten = speler.trekkenKaart();
		
		System.out.println("Getrokken kaarten: "+ getrokkenKaarten.size());
		
		for (GebouwKaart gebouw: getrokkenKaarten) {
			trekkenKaartView.createGebouwView(gebouw);
		}
		
		System.out.println("Buttons: " + trekkenKaartView.getButtonList().size());
		
//		for (Button button: trekkenKaartView.getButtonList()) {
//			button.setOnAction(event -> this.cmdKiezenKaart(gekozenKaartIndex));
//			System.out.println("gekozenKaartIndex: " + gekozenKaartIndex);
//			gekozenKaartIndex++;
//		}
//		System.out.println(gekozenKaartIndex);
		
		trekkenKaartView.getButtonList().get(0).setOnAction(event -> this.cmdKiezenKaart(0));
		trekkenKaartView.getButtonList().get(1).setOnAction(event -> this.cmdKiezenKaart(1));

		/*werkt alleen hardcoded.
		de static int gekozenKaart, blijft na de loop altijd op 2 staan (2).
		en vraagt zelfs na het toewijzen van het event (met lambda) altijd naar de index 2.
		die niet bestaat in de list..*/
		
//		for(int i = 0; gekozenKaartIndex < trekkenKaartView.getButtonList().size(); this.gekozenKaartIndex++)
//		{
//			trekkenKaartView.getButtonList().get(this.gekozenKaartIndex).setOnAction(event -> this.cmdKiezenKaart(this.gekozenKaartIndex));
//			System.out.println("gekozenkaartindex: " + gekozenKaartIndex);
//		}
		
	}
	
	public void cmdKiezenKaart(int gekozenKaart)
	{
		System.out.println("Gekozenkaart Index: (vanuit ActionEvent" + gekozenKaart);
		this.speler.selecterenKaart(this.trekkenKaartView.getGebouwen(), gekozenKaart);
		//gekozenKaart -1, omdat 2 leeg is in de gekozenKaart lijst.
		//Je moet altijd maar 1 kaart trekken,
		showHand(this.speler);
		trekkenKaartView.getStage().close();
		//sluit de view na het trekken van de kaart.
	}
	
	public void cmdKiezenGoud()
	{
		
	}
	
	public void weergeefTrekkenKaartView()
	{
		this.trekkenKaartView.getStage().show();
	}
	
	public void showHand(Speler speler)
    {
        ArrayList<GebouwKaart> lst = speler.getHand().getKaartenLijst();
        System.out.println("Kaarten in hand:");
        for(int i = 0; i < speler.getHand().getKaartenLijst().size(); i++)
        {
            System.out.println(i + 1 + ") " + lst.get(i).getNaam() + " / " + lst.get(i).getType());
        }
        System.out.println();
    }

	@Override
	public void handle(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Handle event");
		int i = 0;
		Button sourcebutton = (Button)e.getSource();
		for (Button button: trekkenKaartView.getButtonList()) {
			if(button.equals(sourcebutton)){
				this.cmdKiezenKaart(i);
				i++;
			}
		}
	}
}
