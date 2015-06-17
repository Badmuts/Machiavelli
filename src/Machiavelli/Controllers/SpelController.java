package Machiavelli.Controllers;

import java.rmi.registry.Registry;

import server.GamesRemote;
import Machiavelli.Machiavelli;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Models.Speler;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Models.Speler;

import java.rmi.RemoteException;

/**
 * 
 * Deze klasse bestuurt het model van het spel.
 *
 */

public class SpelController {
	private SpelRemote spel = null;
    private GebouwKaartController gebouwKaartController;

	public SpelController(SpelRemote spel){
        try {
            this.spel = spel;
            createGebouwKaartController();
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


    public SpelRemote getSpel() {
        return this.spel;
    }
    private void createGebouwKaartController() throws RemoteException {
        this.gebouwKaartController = new GebouwKaartController(spel.getGebouwFactory());
    }

    public GebouwKaartController getGebouwKaartController() {
        return gebouwKaartController;
    }
}

