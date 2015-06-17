package Machiavelli.Controllers;

import Machiavelli.Factories.GebouwFactory;
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
	private SpelRemote spel;
    private GebouwKaartController gebouwKaartController;

	public SpelController(SpelRemote spel) throws RemoteException {
        try {
            this.spel = spel;
            GebouwFactory factory = this.spel.getGebouwFactory();
            this.gebouwKaartController = new GebouwKaartController(spel);
            GebouwFactory factory2 = spel.getGebouwFactory();
            GebouwFactory factory3 = spel.getGebouwFactory();
            GebouwFactory factory4 = spel.getGebouwFactory();
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

