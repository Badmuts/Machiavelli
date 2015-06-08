import Interfaces.Karakter;
import Models.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Controllers.SpeelveldController;
import Controllers.SpelController;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by daanrosbergen on 28/05/15.
 */
public class Machiavelli extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
        Spel sp = new Spel(primaryStage);
        SpelController sc = new SpelController(sp);
        sc.show();

        Speler speler1 = new Speler(sp);
        Prediker p = new Prediker();
        System.out.println(speler1.getPortemonnee().getGoudMunten());
        speler1.setKarakter(p);
        showHand(speler1);
        p.ontvangenBonusGoud(speler1);
        System.out.println(speler1.getPortemonnee().getGoudMunten());
    }

    // Deze method is voor testen
    public void showHand(Speler speler)
    {
        ArrayList<GebouwKaart> lst = speler.getHand().getKaartenLijst();
        System.out.println("Kaarten in hand:");
        for(int i = 0; i < speler.getHand().getKaartenLijst().size(); i++)
        {
            System.out.println(i + 1 + ") " + lst.get(i).getNaam() + " / " + lst.get(i).getType());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
