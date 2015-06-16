package Machiavelli.Controllers;

import Machiavelli.Interfaces.Observers.SpelObserver;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Machiavelli;
import Machiavelli.Models.Speelveld;
import Machiavelli.Models.Speler;
import Machiavelli.Views.SpeelveldView;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
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
	private SpelRemote spel = null;
    private Registry registry = Machiavelli.getInstance().getRegistry();
	
	public SpeelveldController(SpelRemote spel, Speler speler) throws RemoteException {
        this.spel = spel;
        this.speelveld = new Speelveld(this.spel);
        this.speelveld.addSpeler(speler);

        this.speelveldview = new SpeelveldView(this, this.speelveld);
        this.speelveld.registratieView(this.speelveldview);

		speelveldview.getExitButton().setOnAction(event -> System.exit(0));

        try {
            SpelRemote spelStub = (SpelRemote)registry.lookup("Spel");
            spelStub.addObserver(this);
            System.out.println("Add speelveldController to SpelStub");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("SpeelveldController: Spel: aantal spelers: " + this.spel.getAantalSpelers());
        this.speelveldview.show();
	}

	public SpelRemote getSpel() {
		return this.spel;
	}

    @Override
    public void modelChanged(SpelRemote spel) throws RemoteException {
        // tmp casting
        System.out.println("SpeelveldController: Spel model changed!");
        System.out.println("Aantal spelers: " + this.spel.getAantalSpelers());
    }
}
