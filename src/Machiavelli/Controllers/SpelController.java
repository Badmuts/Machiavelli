package Machiavelli.Controllers;

import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Machiavelli;
import Machiavelli.Models.Speler;

/**
 * 
 * Deze klasse bestuurt het model van het spel.
 *
 */

public class SpelController {
	private SpelRemote spel = null;

	public SpelController(SpelRemote spel){
        try {
            this.spel = (SpelRemote)Machiavelli.getInstance().getRegistry().lookup("Spel");
            System.out.println("Added spelRemote to spelcontroller");
        } catch (Exception re) {
            re.printStackTrace();
        }
	}

	public void cmdAddSpeler(Speler speler) {
        try {
            this.spel.addSpeler(speler);
            speler.addSpel(this.spel);
            new SpeelveldController(this.spel, speler);
        } catch (Exception re) {
            re.printStackTrace();
        }
    }
}

