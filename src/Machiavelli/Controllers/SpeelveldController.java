package Machiavelli.Controllers;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Machiavelli.Interfaces.Bonusable;
import Machiavelli.Interfaces.Observers.SpelObserver;
import Machiavelli.Interfaces.Remotes.BeurtRemote;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Models.Beurt;
import Machiavelli.Models.Speelveld;
import Machiavelli.Views.SpeelveldView;

/**
 * 
 * Het speelveld controller maakt het speelveld view aan en kijkt of het speelveld model is veranderd
 * doormiddel van de modelChanged method.
 *
 */

public class SpeelveldController extends UnicastRemoteObject implements SpelObserver {
    private SpelerRemote speler;
    private BeurtRemote beurtRemote;
    
    private BeurtController beurtController;
    private GebouwKaartController gebouwKaartController;
    private Speelveld speelveld;
	private SpeelveldView speelveldview;

	private SpelRemote spel;

    public SpeelveldController(SpelRemote spel, SpelerRemote speler, GebouwKaartController gebouwKaartController) throws RemoteException {
        this.spel = spel;
        this.speler = speler;
        this.speelveld = new Speelveld(this.spel);
        this.speelveld.addSpeler(speler);
        this.beurtRemote = new Beurt(this.spel,this.spel.getSpelers());
        this.gebouwKaartController = gebouwKaartController;
        this.beurtController = new BeurtController(this.beurtRemote,this.spel);
        this.speelveldview = new SpeelveldView(this, this.speelveld, this.gebouwKaartController, this.speler,this.beurtRemote, this.beurtController);

		speelveldview.getExitButton().setOnAction(event -> System.exit(0));
        
//		speelveldview.getSpelregels().setOnAction((event) ->
//		{
//			RaadplegenSpelregelsController spelregelscontroller = new RaadplegenSpelregelsController();
//			spelregelscontroller.cmdSluitSpelregelView();
//		});
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
        this.gebouwKaartController.cmdBouwGebouw();
    }

    public void cmdEindeBeurt() {
        // TODO: Implement eindeBeurt method
        
    }
}
