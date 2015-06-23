package Machiavelli.Controllers;

import java.rmi.registry.Registry;

import server.GamesRemote;
import Machiavelli.Machiavelli;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Models.Speler;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;

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
            this.gebouwKaartController = new GebouwKaartController(this.spel, this.spel.getSpelers().get(this.spel.getSpelers().size()-1));
        } catch (Exception re) {
            re.printStackTrace();
        }
	}

    public void cmdAddSpeler() {
        try {
            SpelerRemote speler = this.spel.getSpelers().get(this.spel.getSpelers().size() - 1);
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

