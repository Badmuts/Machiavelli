package Machiavelli.Controllers;

import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Models.Speler;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 
 * Deze klasse bestuurt het model van het spel.
 *
 */

public class SpelController extends UnicastRemoteObject {
	private SpelRemote spel = null;
    private GebouwKaartController gebouwKaartController;

	public SpelController(SpelRemote spel) throws RemoteException {
        try {
            this.spel = spel;
            this.gebouwKaartController = new GebouwKaartController(spel.getGebouwFactory());
        } catch (Exception re) {
            re.printStackTrace();
        }
	}

    public void cmdAddSpeler(Speler speler) {
        try {
            // Voeg speler toe aan spel
            this.spel.addSpeler(speler);
            // Voeg spel toe aan speler
            speler.addSpel(this.spel);
            // Start nieuwe SpeelveldController
            new SpeelveldController(this.spel, speler, this.gebouwKaartController);
        } catch (Exception re) {
            re.printStackTrace();
        }
    }

    public GebouwKaartController getGebouwKaartController() {
        return gebouwKaartController;
    }
}

