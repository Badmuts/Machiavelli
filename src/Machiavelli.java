import Controllers.SpelController;
import Interfaces.Karakter;
import Models.GebouwKaart;
import Models.Karakters.Magier;
import Models.Spel;
import Models.Speler;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by daanrosbergen on 28/05/15.
 * Google Java Style Guide aanhouden
 */
public class Machiavelli extends Application {


	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.initStyle(StageStyle.UNDECORATED);
        Spel sp = new Spel(primaryStage);
        SpelController sc = new SpelController(sp);
        sc.show();

        Speler speler1 = new Speler(sp);
        Karakter magier = new Magier();
        speler1.setKarakter(magier); // doet nu dus niks
        showHand(speler1);
        Scanner s = new Scanner(System.in);
        ArrayList<GebouwKaart> l = new ArrayList<GebouwKaart>();

        // Kaart 1 2 en 4 ruilen. 2 komt bovenaan gevolgd door 3 nieuwe kaarten.
        l.add(speler1.getHand().getKaartenLijst().get(0));
        l.add(speler1.getHand().getKaartenLijst().get(1));
        l.add(speler1.getHand().getKaartenLijst().get(3));
        // speler1.getHand(), l
        // magier.setTarget(sp.getGebouwFactory());
        // magier.setRuilLijst(l);
        speler1.getKarakter().gebruikEigenschap();
        magier.gebruikEigenschap();
        showHand(speler1);

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

    // Deze ook
    public void showStad(Speler speler)
    {
        ArrayList<GebouwKaart> lst = speler.getHand().getKaartenLijst();
        System.out.println("Kaarten in stad:");
        for(int i = 0; i < speler.getStad().getGebouwen().size(); i++)
        {
            System.out.println(i + 1 + ") " + lst.get(i).getNaam() + " / " + lst.get(i).getType());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
