package Machiavelli.Controllers;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.scene.control.Button;
import Machiavelli.Interfaces.Remotes.BankRemote;
import Machiavelli.Interfaces.Remotes.GebouwKaartRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Models.Spel;
import Machiavelli.Models.Speler;
import Machiavelli.Views.KiesInkomstenView;
import Machiavelli.Views.TrekkenKaartView;

/**
 * @author Jamie Kalloe
 * 
 * Aan het begin van een beurt krijgt een speler de keuze om 
 * goud of kaarten te kiezen. In deze klasse wordt de request uit de view
 * afgehandeld en doorgeven aan de model klasses. Daar op volgend worden
 * ook views getriggerd door deze controller.
 *
 */
public class InkomstenController
{
	// Variables
	private SpelerRemote speler;
	
	//	Beurt beurt;
	private TrekkenKaartView trekkenKaartView;
	private KiesInkomstenView inkomstenView;
	
	public InkomstenController(SpelerRemote speler) throws RemoteException {
		this.speler = speler;
		this.inkomstenView = new KiesInkomstenView();
		this.initializeInkomstenViewButtons();
	}
	
	/**
	 * Hier wordt de request om twee kaarten te trekken afgehandeld.
	 * Vervolgens wordt een nieuwe view gegenereerd die de speler
	 * de keuze tussen 2 kaarten geeft.
	 */
	public void cmdTrekkenKaart() throws RemoteException {
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
			button.setOnAction((event) -> {
				this.cmdKiezenKaart(this.trekkenKaartView.getButtonList().indexOf(button));
				trekkenKaartView.cmdSluitTrekkenKaartView();
				
				try {
					ArrayList<GebouwKaartRemote> spelersKaarten = this.speler.getHand().getKaartenLijst();
					String getrokkenKaart = spelersKaarten.get(spelersKaarten.size() -1).getNaam();
					new MeldingController().build("De kaart '" + getrokkenKaart + "' is in je hand geplaatst").cmdWeergeefMeldingView();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			});
		}
	}
	
	/**
	 * De speler kiest een van de twee kaarten. Vervolgens wordt de kaart weergegeven
	 * in de hand van de speler.
	 */
	public void cmdKiezenKaart(int gekozenKaart) {
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
	
	/**
	 * In de KiesInkomstenView wordt goud gekozen. dit wordt doorgeven aan de bank en speler models.
	 * Vervolgens wordt er een melding weergegeven. de portemonnee view wordt geupdatet.
	 */
	public void cmdKiezenGoud() {
		try {
			BankRemote bank = this.speler.getSpel().getBank();
			this.speler.getGoudVanBank(bank, 2);
			
			System.out.println("Aantal goudmunten in speler portomonee: " + this.speler.getPortemonnee().getGoudMunten());
			System.out.println("Aantal goudmunten in de bank: " + bank.getGoudMunten());
			
			new MeldingController().build("Je portomonee bevat nu " + this.speler.getPortemonnee().getGoudMunten() + " goudstukken").cmdWeergeefMeldingView();
		} 
		catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void weergeefTrekkenKaartView() {
		this.trekkenKaartView.cmdWeergeefTrekkenKaartView();
	}
	
	public void show() {
		try {
			this.inkomstenView.weergeefKiesInkomstenView();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showHand(SpelerRemote speler) throws RemoteException {
        ArrayList<GebouwKaartRemote> lst = speler.getHand().getKaartenLijst();
        System.out.println("Kaarten in hand:");
        for(int i = 0; i < speler.getHand().getKaartenLijst().size(); i++) {
            System.out.println(i + 1 + ") " + lst.get(i).getNaam() + " / " + lst.get(i).getType());
        }
        System.out.println();
    }
	
	public void initializeInkomstenViewButtons() {
		Button goudButton, kaartenButton;
		
		goudButton = this.inkomstenView.getOntvangGoudButton();
		kaartenButton = this.inkomstenView.getOntvangKaartenButton();
		
		goudButton.setOnAction((event) -> {
			this.inkomstenView.cmdSluitKiesInkomstenView();
			cmdKiezenGoud();
		});
		
		kaartenButton.setOnAction((event) -> {
			try {
				this.inkomstenView.cmdSluitKiesInkomstenView();
				cmdTrekkenKaart();
				weergeefTrekkenKaartView();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		});
	}

}
