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

//        Speler speler1 = new Speler(sp);
//        Speler speler2 = new Speler(sp);
//
//        // Testen goud
//        System.out.println("Goud in bank: " + sp.getBank().getGoudMunten());
//        System.out.println("Geld speler1: " + speler1.getPortemonnee().getGoudMunten());
//        System.out.println("Geld speler2: " + speler2.getPortemonnee().getGoudMunten());
//        speler1.getGoudVanBank(sp.getBank(), 2);
//        speler2.setGoudOpBank(speler2.getPortemonnee(), 1);
//        System.out.println("Speler 1 heeft goud van de bank gehaald, zijn budget is nu: " + speler1.getPortemonnee().getGoudMunten());
//        System.out.println("Speler 2 heeft goud aan de bank betaald, zijn budget is nu: " + speler2.getPortemonnee().getGoudMunten());
//        System.out.println("Goud in bank: " + sp.getBank().getGoudMunten() + "\n");
//
//        // Testen kaarten & hand
//
//        System.out.println("Kaarten in de stapel: ");
//        ArrayList<GebouwKaart> lstStapel = sp.getGebouwFactory().getGebouwen();
//        for (int i = 0; i < lstStapel.size(); i++)
//        {
//            System.out.println(i + 1 + ") " + lstStapel.get(i).getNaam());
//        }
//        System.out.println();
//
//        ArrayList<GebouwKaart> lstSpeler1 = speler1.getHand().getKaartenLijst();
//        System.out.println("Kaarten in hand van Speler1:");
//        for(int i = 0; i < speler1.getHand().getKaartenLijst().size(); i++)
//        {
//            System.out.println(lstSpeler1.get(i).getNaam() + " / " + lstSpeler1.get(i).getKosten());
//        }
//        System.out.println();
//
//        // Testen trekken kaart
//        lstSpeler1 = speler1.trekkenKaart();
//        int j = 1;
//        for(int i = 0; i < lstSpeler1.size(); i ++)
//        {
//            System.out.println(j + ": " + lstSpeler1.get(i).getNaam() + " / " + lstSpeler1.get(i).getKosten());
//            j++;
//        }
//        System.out.print("Kies een kaart: ");
//        Scanner s = new Scanner(System.in);
//        int tempIndex = s.nextInt() - 1;
//        speler1.selecterenKaart(lstSpeler1, tempIndex);
//        System.out.println();
//
//        lstSpeler1 = speler1.getHand().getKaartenLijst();
//        System.out.println("Kaarten in hand van Speler1:");
//        for(int i = 0; i < speler1.getHand().getKaartenLijst().size(); i++)
//        {
//            System.out.println(lstSpeler1.get(i).getNaam() + " / " + lstSpeler1.get(i).getKosten());
//        }
//
//        System.out.println();
//
//        System.out.println("Kaarten in de stapel: ");
//        lstStapel = sp.getGebouwFactory().getGebouwen();
//        for (int i = 0; i < lstStapel.size(); i++)
//        {
//            System.out.println(i + 1 + ") " + lstStapel.get(i).getNaam());
//        }
//        System.out.println();

        // Test magier eigenschap: Ruilen met speler
        Speler speler1 = new Speler(sp);
        Speler speler2 = new Speler(sp);

        speler1.setKarakter(new Magier());

        showHand(speler1);
        showHand(speler2);

        Magier magier = new Magier();
        //speler1.getKarakter().ruilMetKarakter(speler1, speler2); Werkt niet
        magier.ruilMetKarakter(speler1, speler2);
        System.out.println("Speler 1 ruilt zijn kaarten met speler 2\n");

        showHand(speler1);
        showHand(speler2);
        // Einde test

	}

    // Deze method is voor test magier eigenschap
    public void showHand(Speler speler)
    {
        ArrayList<GebouwKaart> lst = speler.getHand().getKaartenLijst();
        System.out.println("Kaarten in hand:");
        for(int i = 0; i < speler.getHand().getKaartenLijst().size(); i++)
        {
            System.out.println(lst.get(i).getNaam() + " / " + lst.get(i).getKosten());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        launch(args);


    }
}
