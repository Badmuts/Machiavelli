package Machiavelli.Controllers;

import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Models.Speler;

/**
 * 
 * Deze klasse bestuurt het model van het spel.
 *
 */

public class SpelController {
    private SpeelveldController speelveldController;
	private SpelRemote spel = null;

	public SpelController(SpelRemote spel){
        try {
            this.spel = spel;
            System.out.println("Added spelRemote to spelcontroller");
        } catch (Exception re) {
            re.printStackTrace();
        }
	}

	public void cmdAddSpeler(Speler speler) {
        try {
            this.spel.addSpeler(speler);
            speler.addSpel(this.spel);
        } catch (Exception re) {
            re.printStackTrace();
        }
    }

    public SpelRemote getSpel() {
        return this.spel;
    }
}

