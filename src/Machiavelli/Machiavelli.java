package Machiavelli;

import Machiavelli.Controllers.MenuController;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Models.GebouwKaart;
import Machiavelli.Views.MainMenuView;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.rmi.RemoteException;
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
        /**
         * Laad MainMenuView zien
         */

        // TODO Auto-generated method stub
        this.stage = primaryStage;
        this.stage.initStyle(StageStyle.UNDECORATED);
        this.stage.setResizable(false);
        this.stage.setTitle("Machiavelli");
//        Spel spel = new Spel();
//        SpelController spelController = new SpelController(spel);
//        spelController.show();
        MenuController menuController = new MenuController(new MainMenuView());

        /**
         * Testing method for Magier karakter class
         *
         * Het Magier karakter kan bepaalde kaarten uit zijn hand
         * ruilen met de gebouwstapel.
         */
        /*Speler speler1 = new Speler(spel);
        Karakter magier = new Magier();
        speler1.setKarakter(magier); // doet nu dus niks
        showHand(speler1);
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
        showHand(speler1);*/
    }

    public static synchronized Machiavelli getInstance() {
        return uniqueInstance;
    }

    // Deze method is voor testen
    public void showHand(SpelerRemote speler) {
        try {
            ArrayList<GebouwKaart> lst = speler.getHand().getKaartenLijst();
            System.out.println("Kaarten in hand:");
            for(int i = 0; i < speler.getHand().getKaartenLijst().size(); i++)
            {
                System.out.println(i + 1 + ") " + lst.get(i).getNaam() + " / " + lst.get(i).getType());
            }
            System.out.println();
        } catch (RemoteException re) {
            System.out.print(re);
        }
    }

    // Deze ook
    public void showStad(SpelerRemote speler) {
        try {
            ArrayList<GebouwKaart> lst = speler.getHand().getKaartenLijst();
            System.out.println("Kaarten in stad:");
            for(int i = 0; i < speler.getStad().getGebouwen().size(); i++)
            {
                System.out.println(i + 1 + ") " + lst.get(i).getNaam() + " / " + lst.get(i).getType());
            }
            System.out.println();
        } catch (RemoteException re) {
            System.out.print(re);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Stage getStage() {
        return this.stage;
    }
}
