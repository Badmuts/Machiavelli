package Machiavelli;

import Machiavelli.Controllers.SpelController;
import Machiavelli.Models.GebouwKaart;
import Machiavelli.Models.Speelveld;
import Machiavelli.Models.Spel;
import Machiavelli.Models.Speler;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.util.ArrayList;


/**
 * Google Java Style Guide aanhouden
 */

public class Machiavelli extends Application {

    private static Machiavelli uniqueInstance;
    private Stage stage;

    public Machiavelli() {
        super();
        synchronized(Machiavelli.class){
            if(uniqueInstance != null) throw new UnsupportedOperationException(
                    getClass()+" is singleton but constructor called more than once");
            uniqueInstance = this;
        }
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        this.stage = primaryStage;
        this.stage.initStyle(StageStyle.UNDECORATED);
        this.stage.setResizable(false);
        this.stage.setTitle("Machiavelli");
        Spel spel = new Spel();
        SpelController spelController = new SpelController(spel);
        spelController.show();

        spel.setAantalSpelers(3);
        spel.NieuwSpel();

        showHand(spel.getSpelerLijst().get(0));

        XStream xs = new XStream(new DomDriver());
        xs.alias("spel", Spel.class);
        xs.alias("speler", Speler.class);
        xs.alias("gebouwkaart", GebouwKaart.class);
        xs.omitField(Image.class, "progress");
        xs.omitField(Image.class, "platformImage");
        xs.omitField(Speelveld.class, "speelveldcontroller");
        FileOutputStream fos = new FileOutputStream("testXML.xml");
        xs.toXML(spel, fos);

        InputStream in = new FileInputStream("testXML.xml");
        spel = null;
        spel = (Spel) xs.fromXML(in);

        showHand(spel.getSpelerLijst().get(0));



//        /**
//         * Testing method for Magier karakter class
//         *
//         * Het Magier karakter kan bepaalde kaarten uit zijn hand
//         * ruilen met de gebouwstapel.
//         */
//        Speler speler1 = new Speler(spel);
//        Karakter magier = new Magier();
//        speler1.setKarakter(magier); // doet nu dus niks
//        showHand(speler1);
//        ArrayList<GebouwKaart> l = new ArrayList<GebouwKaart>();
//
//        // Kaart 1 2 en 4 ruilen. 2 komt bovenaan gevolgd door 3 nieuwe kaarten.
//        l.add(speler1.getHand().getKaartenLijst().get(0));
//        l.add(speler1.getHand().getKaartenLijst().get(1));
//        l.add(speler1.getHand().getKaartenLijst().get(3));
//        // speler1.getHand(), l
//        // magier.setTarget(sp.getGebouwFactory());
//        // magier.setRuilLijst(l);
//        speler1.getKarakter().gebruikEigenschap();
//        magier.gebruikEigenschap();
//        showHand(speler1);
    }

    public static synchronized Machiavelli getInstance() {
        return uniqueInstance;
    }

    // Deze method is voor testen
    public void showHand(Speler speler) {
        ArrayList<GebouwKaart> lst = speler.getHand().getKaartenLijst();
        System.out.println("Kaarten in hand:");
        for(int i = 0; i < speler.getHand().getKaartenLijst().size(); i++)
        {
            System.out.println(i + 1 + ") " + lst.get(i).getNaam() + " / " + lst.get(i).getType());
        }
        System.out.println();
    }

    // Deze ook
    public void showStad(Speler speler) {
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

    public Stage getStage() {
        return this.stage;
    }
}
