package Machiavelli.Controllers;

import java.rmi.RemoteException;
import java.util.ArrayList;

import Machiavelli.Interfaces.Remotes.BankRemote;
import Machiavelli.Interfaces.Remotes.GebouwKaartRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import Machiavelli.Machiavelli;
import Machiavelli.Models.Bank;
import Machiavelli.Models.GebouwKaart;
import Machiavelli.Models.Spel;
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
	private KiesInkomstenView inkomstenView;
	
	public InkomstenController(Speler speler) throws RemoteException
	{
		this.speler = speler;
		this.inkomstenView = new KiesInkomstenView();
		this.initializeInkomstenViewButtons();
		
		//TODO: Dit is alleen bedoeld voor testing, krijg de speler vanuit beurt.
		Spel spel = new Spel();
		spel.createNewSpel(2);
		this.speler.addSpel(spel);
	}
	
	public void cmdTrekkenKaart() throws RemoteException
	{
		this.trekkenKaartView = new TrekkenKaartView(this);
		ArrayList<GebouwKaartRemote> getrokkenKaarten = speler.trekkenKaart();
		
		System.out.println("Getrokken kaarten: "+ getrokkenKaarten.size());
		
		//Maak de buttons voor elk getrokken gebouwkaart.
		for (GebouwKaartRemote gebouw: getrokkenKaarten) {
			trekkenKaartView.createGebouwView(gebouw);
		}
		
		//voeg de buttons toe aan het scherm.
		this.trekkenKaartView.addButtonsToView();
		
		
		System.out.println("Buttons: " + trekkenKaartView.getButtonList().size());
		
		for (Button button: trekkenKaartView.getButtonList()) {
															//neem de index van de arraylist per object / button
			button.setOnAction((event) -> 
			{
				this.cmdKiezenKaart(this.trekkenKaartView.getButtonList().indexOf(button));
				trekkenKaartView.cmdSluitTrekkenKaartView();
				
				try
				{
					ArrayList<GebouwKaartRemote> spelersKaarten = this.speler.getHand().getKaartenLijst();
					String getrokkenKaart = spelersKaarten.get(spelersKaarten.size() -1).getNaam();
					new MeldingController().build("De kaart '" + getrokkenKaart + "' is in je hand geplaatst").cmdWeergeefMeldingView();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			});
		}
	}
	
	public void cmdKiezenKaart(int gekozenKaart)
	{
		try
		{
			System.out.println("Gekozenkaart Index: (vanuit ActionEvent" + gekozenKaart);
			this.speler.selecterenKaart(this.trekkenKaartView.getGebouwen(), gekozenKaart);
			showHand(this.speler);
		}
		catch(RemoteException e)
		{
			e.printStackTrace();
		}
	}
	
	public void cmdKiezenGoud()
	{
		try 
		{
			BankRemote bank = this.speler.getSpel().getBank();
			this.speler.getGoudVanBank(bank, 0);
			
			System.out.println("Aantal goudmunten in speler portomonee: " + this.speler.getPortemonnee().getGoudMunten());
			System.out.println("Aantal goudmunten in de bank: " + bank.getGoudMunten());
			
			new MeldingController().build("Je portomonee bevat nu " + this.speler.getPortemonnee().getGoudMunten() + " goudstukken").cmdWeergeefMeldingView();
		} 
		catch (RemoteException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void weergeefTrekkenKaartView()
	{
		this.trekkenKaartView.cmdWeergeefTrekkenKaartView();
	}
	
	public void weergeefKiesInkomstenView()
	{
		try
		{
			this.inkomstenView.weergeefKiesInkomstenView();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void showHand(SpelerRemote speler) throws RemoteException
    {
        ArrayList<GebouwKaartRemote> lst = speler.getHand().getKaartenLijst();
        System.out.println("Kaarten in hand:");
        for(int i = 0; i < speler.getHand().getKaartenLijst().size(); i++)
        {
            System.out.println(i + 1 + ") " + lst.get(i).getNaam() + " / " + lst.get(i).getType());
        }
        System.out.println();
    }
	
	public void initializeInkomstenViewButtons()
	{
		Button goudButton, kaartenButton;
		
		goudButton = this.inkomstenView.getOntvangGoudButton();
		kaartenButton = this.inkomstenView.getOntvangKaartenButton();
		
		goudButton.setOnAction((event) ->
		{
			this.inkomstenView.cmdSluitKiesInkomstenView();
			cmdKiezenGoud();
		});
		
		kaartenButton.setOnAction((event) ->
		{
			try
			{
				this.inkomstenView.cmdSluitKiesInkomstenView();
				cmdTrekkenKaart();
				weergeefTrekkenKaartView();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		});
	}

}
