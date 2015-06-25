package Machiavelli.Controllers;

import Machiavelli.Interfaces.Bonusable;
import Machiavelli.Interfaces.Observers.SpelObserver;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Models.Speelveld;
import Machiavelli.Views.SpeelveldView;
import javafx.application.Platform;

import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 
 * Het speelveld controller maakt het speelveld view aan en kijkt of het speelveld model is veranderd
 * doormiddel van de modelChanged method.
 *
 */

public class SpeelveldController extends UnicastRemoteObject implements SpelObserver {
    private MeldingController meldingController;
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
        this.meldingController = new MeldingController();

        this.spel.addObserver(this);
        this.speelveldview.show();

        if (this.spel.getAantalSpelers() < this.spel.getMaxAantalSpelers()) {
            this.meldingController.build("Wachten op spelers: " + this.spel.getAantalSpelers() + "/" + this.spel.getMaxAantalSpelers());
            this.meldingController.getSluitButton().setDisable(true);
            this.meldingController.getSluitButton().setText("Wachten...");
            this.meldingController.cmdWeergeefMeldingView();
        }
    }

	public SpelRemote getSpel() {
		return this.spel;
	}

    public void cmdOpslaan(){
        try
        {
            this.spel.opslaanSpel();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void cmdLaden()
    {
        try {
            this.speelveld.ladenSpel();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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

    @Override
    public void modelChanged(SpelRemote spel) throws RemoteException {
        this.spel = spel;
        if (this.spel.getMaxAantalSpelers() == this.spel.getAantalSpelers()) {
            Platform.runLater(() -> this.meldingController.cmdSluitMeldingView());
        } else {
            this.meldingController.build("Wachten op spelers: " + this.spel.getAantalSpelers() + "/" + this.spel.getMaxAantalSpelers());
        }
        System.out.println("SpeelveldController: Spel model changed!");
        System.out.println("Aantal spelers: " + this.spel.getAantalSpelers());
    }
}
