package Machiavelli.Controllers;

import Machiavelli.Models.Spel;
import Machiavelli.Models.Speler;

/**
 * 
 * Deze klasse bestuurt het model van het spel.
 *
 */

public class SpelController{
    private SpeelveldController speelveldController;
	private Spel spel;

	public SpelController(int maxAantalSpelers){
		this.spel = new Spel(maxAantalSpelers);
        this.speelveldController = new SpeelveldController(spel);
	}

	public void cmdAddSpeler(Speler speler) {
        speler.addSpel(this.spel);
        this.spel.addSpeler(speler);
    }

    public Spel getSpel() {
        return this.spel;
    }
}

