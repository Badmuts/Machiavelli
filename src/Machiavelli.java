import Interfaces.Karakter;
import Models.*;
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
