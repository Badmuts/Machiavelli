package Machiavelli.Controllers;

import Machiavelli.Interfaces.Bonusable;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Observers.SpelObserver;
import Machiavelli.Interfaces.Remotes.KarakterFactoryRemote;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Models.Speelveld;
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

        this.speelveldview = new SpeelveldView(this, this.speelveld, this.gebouwKaartController, this.speler);

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

    public void cmdGebruikEigenschap() {
        try {
        	//Als de gebruikeigenschap geen target heeft, open de kiesKarakterView.
            if (!this.speler.getKarakter().gebruikEigenschap()) {
            	
            	//Speler = magier, kies speler view.
            	if(this.speler.getKarakter().getNummer() == 3)
            	{
            		System.out.println("De speler is een magier");
            		KarakterController karakterController = new KarakterController(this.speler, "speler");
                    karakterController.show();
            	}
            	
            	//Speler = moordenaar of dief, kies karakter view.
            	if(this.speler.getKarakter().getNummer() == 1 || this.speler.getKarakter().getNummer() == 2)
            	{
            		System.out.println("De speler is een moordenaar'");
            		this.speler.getSpel().getKarakterFactory().getKarakterByNumber(5);
            		KarakterController karakterController = new KarakterController(this.speler, "karakter");
            		karakterController.show();
            	}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
