package Machiavelli.Models;

import Machiavelli.Interfaces.Karakter;

import java.util.ArrayList;

/**
 * De speler is onderdeel van een spel. De speler beheerd zijn portemonnee en
 * heeft een hand met kaarten. Bij het aanmaken van een speler krijgt deze
 * een nieuwe hand, portemonnee en stad toegewezen. De speler kan vervolgens
 * verschillende acties uitvoeren.
 *
 * @author Sander de Jong
 * @version 0.1
 *
 */
public class Speler
{
	// Variables
	private boolean isKoning;
	private Portemonnee portemonnee;
	private Karakter karakter;
	private Hand hand;
	private Spel spel;
	private Stad stad;

	// Speler toewijzen aan spel en een nieuwe portemonnee, hand en stad maken.
	public Speler(Spel spel) {
		this.spel = spel;
		this.stad = new Stad(spel);
		this.portemonnee = new Portemonnee(this.spel.getBank());
		this.hand = new Hand(this);
	}

	// Haalt goud van de bank en zet het in de portemonnee
	public void getGoudVanBank(Bank bank, int aantal) {
		this.portemonnee.ontvangenGoud(aantal);
	}

	// Haalt goud uit de portemonnee en geeft dit aan de bank
	public void setGoudOpBank(Portemonnee portemonnee, int aantal) {
		this.portemonnee.bestedenGoud(this.spel.getBank(), aantal);
	}

	// Plaats een gebouwkaart in de stad van de speler
	public void setKaartInStad(GebouwKaart gebouw) {
		this.stad.addGebouw(gebouw);
		this.hand.removeGebouw(gebouw);
	}

	// Trekken van twee kaarten uit de stapel
	public ArrayList<GebouwKaart> trekkenKaart() {
		ArrayList<GebouwKaart> tempList = new ArrayList<GebouwKaart>();
		for (int i = 0; i < 2; i++)
		{
			tempList.add(this.spel.getGebouwFactory().trekKaart());
		}
		return tempList;
	}

	// Trekken van een x aantal kaarten van de stapel
	public ArrayList<GebouwKaart> trekkenKaart(int aantal) {
		ArrayList<GebouwKaart>tempList = new ArrayList<GebouwKaart>();
		for (int i = 0; i < aantal; i++)
		{
			tempList.add(this.spel.getGebouwFactory().trekKaart());
		}
		return tempList;
	}

	// Selecteren van een kaart aan de hand van de getrokken kaarten
	public void selecterenKaart(ArrayList<GebouwKaart> lijst, int index) {
		this.getHand().addGebouw(lijst.get(index));
		lijst.remove(index);
		this.getSpel().getGebouwFactory().addGebouw(lijst.get(0));
	}

	public Karakter getKarakter() {
		return this.karakter;
	}
	
	public void setKarakter(Karakter karakter) {
		this.karakter = karakter;
	}

	public Spel getSpel() {
		return this.spel;
	}

	public Portemonnee getPortemonnee() {
		return this.portemonnee;
	}

	public Hand getHand() {
		return this.hand;
	}

	public Stad getStad() {
		return this.stad;
	}
}
