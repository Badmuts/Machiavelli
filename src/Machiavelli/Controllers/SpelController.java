package Machiavelli.Controllers;

import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Machiavelli;
import Machiavelli.Models.Speler;
import server.GamesRemote;

import java.rmi.registry.Registry;

/**
 * 
 * Deze klasse bestuurt het model van het spel.
 *
 */

public class SpelController {
    private SpeelveldController speelveldController;
	private SpelRemote spel;
    private Registry registry = Machiavelli.getInstance().getRegistry();

	public SpelController(SpelRemote spel){
        try {
        	
            this.spel = (SpelRemote)registry.lookup("Spel");
            //GamesRemote gamesRemote = (GamesRemote)registry.lookup("Games");
            //gamesRemote.addSpelToGames(this.spel);
        } catch (Exception re) {
            re.printStackTrace();
        }
	}

	public void cmdAddSpeler(Speler speler) {
        try {
//            registry.bind("Speler", speler);
//            SpelerRemote sp = (SpelerRemote)registry.lookup("Speler");
            speler.addSpel(this.spel);
            this.spel.addSpeler(speler);
        } catch (Exception re) {
            re.printStackTrace();
        }
    }

    public SpelRemote getSpel() {
        return this.spel;
    }
}

