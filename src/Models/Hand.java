package Models;

import java.util.*;

/**
 * De hand van de speler heeft controle over de kaarten die de speler
 * op dat moment bezit. Er kunnen kaarten in de hand worden toegevoegd
 * en verwijderd.
 *
 * @author Sander
 * @version 0.1
 *
 */
public class Hand
{
	// Variables
	private ArrayList<GebouwKaart> kaartenLijst;
	private Speler speler;

	// Een speler start met 4 gebouwkaarten in zijn hand.
	public Hand(Speler speler) {
		this.speler = speler;
		kaartenLijst = new ArrayList<GebouwKaart>();
		for(int i = 0; i < 4; i ++)
		{
			// Trek 4 kaarten van de stapel (gebouwFactory)
			kaartenLijst.add(this.speler.getSpel().getGebouwFactory().trekKaart());
		}
	}

	// Een gebouw toevoegen aan de hand van de speler
	public void addGebouw(GebouwKaart kaart) {
		kaartenLijst.add(kaart);
	}

	// Kaart verwijderen uit de hand van de speler
	public void removeGebouw(GebouwKaart gebouw) {
		this.kaartenLijst.remove(gebouw);
	}

	public ArrayList<GebouwKaart> getKaartenLijst()
	{
		return this.kaartenLijst;
	}

	public void setKaartenLijst(ArrayList<GebouwKaart> lijst) {
		this.kaartenLijst = lijst;
	}

	public Speler getSpeler() {
		return this.speler;
	}
}
