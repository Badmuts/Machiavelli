package Machiavelli.Controllers;

import Machiavelli.Interfaces.Observers.SpelObserver;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Models.Speelveld;
import Machiavelli.Models.Spel;
import Machiavelli.Views.SpeelveldView;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
/**
 * 
 * Het speelveld controller maakt het speelveld view aan en kijkt of het speelveld model is veranderd
 * doormiddel van de modelChanged method.
 *
 */

public class SpeelveldController extends UnicastRemoteObject implements SpelObserver {
	private Speelveld speelveld;
	private SpeelveldView speelveldview;
	private SpelRemote spel;
	
	public SpeelveldController(Speelveld speelveld, SpelRemote spel) throws RemoteException{
        this.speelveld = speelveld;
        this.spel = spel;
        this.spel.addObserver(this);
        this.speelveldview = new SpeelveldView(this, this.speelveld);
        this.speelveld.registratieView(this.speelveldview);

		speelveldview.getExitButton().setOnAction(event -> System.exit(0));

		this.speelveldview.show();
	}

	public SpelRemote getSpel() {
		return this.spel;
	}

    @Override
    public void modelChanged(SpelRemote spel) throws RemoteException {
        // tmp casting
        System.out.println("SpeelveldController: Spel model changed!");
        this.spel = (Spel)spel;
        System.out.println("Aantal spelers: " + this.spel.getAantalSpelers());
    }
}
