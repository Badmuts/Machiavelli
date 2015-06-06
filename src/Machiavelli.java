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
        Magier magier = new Magier();
        speler1.setKarakter(magier); // doet nu dus niks
        showHand(speler1);
        Scanner s = new Scanner(System.in);
        ArrayList<GebouwKaart> l = new ArrayList<GebouwKaart>();

        // Kaart 1 2 en 4 ruilen. 2 komt bovenaan gevolgd door 3 nieuwe kaarten.
        l.add(speler1.getHand().getKaartenLijst().get(0));
        l.add(speler1.getHand().getKaartenLijst().get(1));
        l.add(speler1.getHand().getKaartenLijst().get(3));
        magier.ruilMetStapel(speler1.getHand(), l);
        showHand(speler1);


    }

    // Deze method is voor testen
    public void showHand(Speler speler)
    {
        ArrayList<GebouwKaart> lst = speler.getHand().getKaartenLijst();
        System.out.println("Kaarten in hand:");
        for(int i = 0; i < speler.getHand().getKaartenLijst().size(); i++)
        {
            System.out.println(i + 1 + ") " + lst.get(i).getNaam() + " / " + lst.get(i).getKosten());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        launch(args);


    }
}
