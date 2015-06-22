package Machiavelli.Controllers;

import Machiavelli.Interfaces.Bonusable;
import Machiavelli.Interfaces.Observers.SpelObserver;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Models.Speelveld;
import Machiavelli.Views.SpeelveldView;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 
 * Het speelveld controller maakt het speelveld view aan en kijkt of het speelveld model is veranderd
 * doormiddel van de modelChanged method.
 *
 */

public class SpeelveldController extends UnicastRemoteObject implements SpelObserver, Serializable {
    private SpelerRemote speler;
    private GebouwKaartController gebouwKaartController;
    private Speelveld speelveld;
	private SpeelveldView speelveldview;
	private SpelRemote spel;

    public SpeelveldController(SpelRemote spel, SpelerRemote speler, GebouwKaartController gebouwKaartController) throws RemoteException {
        this.spel = spel;
        this.speler = speler;
        this.speelveld = new Speelveld(this.spel);
        this.speelveld.addSpeler(speler);
        this.gebouwKaartController = gebouwKaartController;

        this.speelveldview = new SpeelveldView(this, this.speelveld, this.gebouwKaartController);
        this.speelveld.addObserver(this.speelveldview);

		speelveldview.getExitButton().setOnAction(event -> System.exit(0));

        this.spel.addObserver(this);
		this.speelveldview.show();
	}

	public SpelRemote getSpel() {
		return this.spel;
	}

    @Override
    public void modelChanged(SpelRemote spel) throws RemoteException {
        System.out.println("SpeelveldController: Spel model changed!");
        System.out.println("Aantal spelers: " + this.spel.getAantalSpelers());
    }

    public void cmdBonusGoud() {
        try {
            Bonusable karakter = (Bonusable)this.speler.getKarakter();
            karakter.ontvangenBonusGoud();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SpelerRemote getSpeler() {
        return this.speler;
    }

    public void cmdBouwGebouw() {
        // TODO: Implement gebouwbouwen method
    }

    public void cmdEindeBeurt() {
        // TODO: Implement eindeBeurt method
    }
}
