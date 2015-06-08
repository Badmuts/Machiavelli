import Models.GebouwKaart;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Controllers.SpelController;
import Models.Spel;
import Models.Speler;

import java.util.ArrayList;

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

        /*Speler speler1 = new Speler(sp);
        Speler speler2 = new Speler(sp);

        // Testen goud
        System.out.println("Goud in bank: " + sp.getBank().getGoudMunten());
        System.out.println("Geld speler1: " + speler1.getPortemonnee().getGoudMunten());
        System.out.println("Geld speler2: " + speler2.getPortemonnee().getGoudMunten());
        speler1.getGoudVanBank(sp.getBank(), 2);
        speler2.setGoudOpBank(speler2.getPortemonnee(), 1);
        System.out.println("Speler 1 heeft goud van de bank gehaald, zijn budget is nu: " + speler1.getPortemonnee().getGoudMunten());
        System.out.println("Speler 2 heeft goud aan de bank betaald, zijn budget is nu: " + speler2.getPortemonnee().getGoudMunten());
        System.out.println("Goud in bank: " + sp.getBank().getGoudMunten() + "\n");

        // Testen kaarten & hand
        ArrayList<GebouwKaart> lstSpeler1 = speler1.getHand().getKaartenLijst();
        System.out.println("Kaarten in hand van Speler1:");
        for(int i = 0; i < speler1.getHand().getKaartenLijst().size(); i++)
        {
            System.out.println(lstSpeler1.get(i).getNaam() + " / " + lstSpeler1.get(i).getKosten());
        }*/
	}

    public static void main(String[] args) {
        launch(args);
    }
}
