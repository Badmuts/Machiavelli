package Machiavelli.Controllers;

import Machiavelli.Interfaces.Remotes.BeurtRemote;
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
    private SpelerRemote speler;
    private SpelRemote spel;
    private BeurtRemote beurt;
    private GebouwKaartController gebouwKaartController;

	public SpelController(SpelRemote spel) throws RemoteException {
//        super(1099);
        try {
            this.spel = spel;
            this.speler = this.spel.getSpelers().get(this.spel.getSpelers().size() - 1);
            this.beurt = this.spel.getBeurt();
            this.gebouwKaartController = new GebouwKaartController(this.spel, this.speler);
            // Start nieuwe SpeelveldController
            new SpeelveldController(this.spel, speler, this.gebouwKaartController, this.beurt);
            
        } catch (Exception re) {
            re.printStackTrace();
        }
	}

    public GebouwKaartController getGebouwKaartController() {
        return gebouwKaartController;
    }
}

