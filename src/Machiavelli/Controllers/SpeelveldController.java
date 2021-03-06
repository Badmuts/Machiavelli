package Machiavelli.Controllers;

import Machiavelli.Interfaces.Bonusable;
import Machiavelli.Interfaces.Observers.SpelObserver;
import Machiavelli.Interfaces.Remotes.BeurtRemote;
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
 * Het speelveld controller maakt het speelveld view aan en kijkt of het speelveld model is
 * veranderd doormiddel van de modelChanged method.
 *
 */

public class SpeelveldController extends UnicastRemoteObject implements SpelObserver {

    private SpelerRemote speler;
    private BeurtRemote beurt;
    private SpelRemote spel;

    private MeldingController meldingController;
    private BeurtController beurtController;
    private GebouwKaartController gebouwKaartController;
    private KarakterController karakterController;
    private Speelveld speelveld;
    private SpeelveldView speelveldview;

    public SpeelveldController(SpelRemote spel, SpelerRemote speler,
            GebouwKaartController gebouwKaartController, BeurtRemote beurt) throws RemoteException {
        // super(1099);
        this.spel = spel;
        this.speler = speler;
        this.beurt = beurt;
        this.speelveld = new Speelveld(this.spel);
        this.speelveld.addSpeler(speler);
        this.gebouwKaartController = gebouwKaartController;
        this.beurtController = new BeurtController(this.beurt, this.spel, this.speler);
        this.speelveldview =
                new SpeelveldView(this, this.speelveld, this.gebouwKaartController, this.speler,
                        this.beurtController, this.beurt);
        // speelveldview.getSpelregels().setOnAction((event) ->
        // {
        // RaadplegenSpelregelsController spelregelscontroller = new
        // RaadplegenSpelregelsController();
        // spelregelscontroller.cmdSluitSpelregelView();
        // });
        this.meldingController = new MeldingController();

        this.spel.addObserver(this);
        this.speelveldview.show();

        if (this.spel.getAantalSpelers() < this.spel.getMaxAantalSpelers()) {
            this.meldingController.build("Wachten op spelers: " + this.spel.getAantalSpelers()
                    + "/" + this.spel.getMaxAantalSpelers());
            this.meldingController.getSluitButton().setDisable(true);
            this.meldingController.getSluitButton().setText("Wachten...");
            this.meldingController.cmdWeergeefMeldingView();
        } else {
            this.meldingController.build("Spelers aantal bereikt").cmdWeergeefMeldingView();;
            this.meldingController.cmdSluitMeldingView();
            this.karakterController = new KarakterController(this.speler, "ronde");
            this.karakterController.show();
            
            beurtController.cmdGeefBeurt();
            beurtController.cmdGeefBeurt();
        }
    }

    public SpelRemote getSpel() {
        return this.spel;
    }

    public void cmdOpslaan() {
        try {
            this.speelveld.opslaanSpel();
            new MeldingController().build("Spel is opgeslagen!").cmdWeergeefMeldingView();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void cmdBonusGoud() {
        try {
            
            Bonusable karakter = (Bonusable) this.speler.getKarakter();
            karakter.ontvangenBonusGoud();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SpelerRemote getSpeler() {
        return this.speler;
    }

    public BeurtRemote getBeurt() {
        return this.beurt;
    }
    
    

    public void cmdBouwGebouw() {
        this.gebouwKaartController.cmdBouwGebouw();
    }

    public void cmdEindeBeurt() {
        this.beurtController.cmdNextObserver();
    }

    public Speelveld getSpeelveld() {
        return this.speelveld;
    }


    public void cmdGebruikEigenschap() {
        try {
            // Als de gebruikeigenschap geen target heeft, open de kiesKarakterView.
            // boolean gebruikEigenschap = this.speler.getKarakter().gebruikEigenschap();
            if (this.speler.getKarakter().getNummer() == 8
                    && this.speler.getKarakter().getTarget() == null) {
                // haal karakter op, check of hij het gebouw vernietigd heeft.
                this.gebouwKaartController.cmdVernietigGebouw();
                System.out.println("hallo");
            }

            if (!this.speler.getKarakter().gebruikEigenschap()) {

                if (this.speler.getKarakter().getNummer() == 8) {
                    new MeldingController().build(
                            "Er is niet genoeg goud / geen kaart geselecteerd")
                            .cmdWeergeefMeldingView();
                    this.speler.getKarakter().setTarget(null);
                }

                // Speler = magier, kies speler view.
                if (this.speler.getKarakter().getNummer() == 3) {
                    // Show keuze view tussen stapel en speler
                    KarakterController karakterController =
                            new KarakterController(this.speler, "magier");
                    karakterController.show();

                    System.out.println("De speler is een magier");
                }

                // Speler = moordenaar of dief, kies karakter view.
                if (this.speler.getKarakter().getNummer() == 1
                        || this.speler.getKarakter().getNummer() == 2) {
                    System.out.println("De speler is een moordenaar");

                    KarakterController karakterController =
                            new KarakterController(this.speler, "karakter");
                    karakterController.show();
                }
            } else {
                this.speler.getKarakter().gebruikEigenschap();

                if (this.speler.getKarakter().getNummer() == 6) {
                    new MeldingController().build("1 extra goudstuk ontvangen")
                            .cmdWeergeefMeldingView();
                }
                if (this.speler.getKarakter().getNummer() == 7) {
                    new MeldingController().build("2 extra gebouwkaarten getrokken")
                            .cmdWeergeefMeldingView();
                }

                if (this.speler.getKarakter().getNummer() == 8) {
                    new MeldingController().build("Er is een gebouw vernietigd.")
                            .cmdWeergeefMeldingView();
                    this.speler.getKarakter().setTarget(null);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void modelChanged(SpelRemote spel) throws RemoteException {
        this.spel = spel;

        Platform.runLater(() -> {
            try {
                if (this.spel.getMaxAantalSpelers() == this.spel.getAantalSpelers()) {
                    this.meldingController.cmdSluitMeldingView();
                    this.karakterController = new KarakterController(this.speler, "ronde");
                    this.karakterController.show();

                } else {
                    this.meldingController.build("Wachten op spelers: "
                            + this.spel.getAantalSpelers() + "/" + this.spel.getMaxAantalSpelers());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println("SpeelveldController: Spel model changed!");
        System.out.println("Aantal spelers: " + this.spel.getAantalSpelers());
    }
}
