package Factories;

import Enumerations.Type;
import Models.GebouwKaart;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by daanrosbergen on 04/06/15.
 */
public class GebouwFactory {

    private ArrayList<GebouwKaart> gebouwen = new ArrayList<GebouwKaart>();

    public GebouwFactory() {
        gebouwen.add(new GebouwKaart(6, "Bibliotheek", Type.NORMAAL, new Image("Resources/Gebouwkaarten/bibliotheek.png")));
        gebouwen.add(new GebouwKaart(5, "Werkplaats", Type.NORMAAL, new Image("Resources/Gebouwkaarten/werkplaats.png")));
        gebouwen.add(new GebouwKaart(6, "School voor Magiërs", Type.NORMAAL, new Image("Resources/Gebouwkaarten/school-voor-magiers.png")));
        gebouwen.add(new GebouwKaart(5, "Laboratorium", Type.NORMAAL, new Image("Resources/Gebouwkaarten/laboratorium.png")));
        gebouwen.add(new GebouwKaart(6, "Drakenpoort", Type.NORMAAL, new Image("Resources/Gebouwkaarten/drakenpoort.png")));
        gebouwen.add(new GebouwKaart(5, "Kerkhof", Type.NORMAAL, new Image("Resources/Gebouwkaarten/kerkhof.png")));
        gebouwen.add(new GebouwKaart(6, "Universiteit", Type.NORMAAL, new Image("Resources/Gebouwkaarten/universiteit.png")));
        //Deze is nog geen PNG
        gebouwen.add(new GebouwKaart(5, "Observatorium", Type.NORMAAL, new Image("Resources/Gebouwkaarten/burcht.png")));
        gebouwen.add(new GebouwKaart(2, "Hof der Wonderen", Type.NORMAAL, new Image("Resources/Gebouwkaarten/hof-der-wonderen.png")));
        for (int i = 0; i < 2; i++) {
            gebouwen.add(new GebouwKaart(2, "Kerker", Type.NORMAAL, new Image("Resources/Gebouwkaarten/kerker.png")));
            gebouwen.add(new GebouwKaart(5, "Raadhuis", Type.COMMERCIEL, new Image("Resources/Gebouwkaarten/raadhuis.png")));
            gebouwen.add(new GebouwKaart(5, "Kathedraal", Type.KERKELIJK, new Image("Resources/Gebouwkaarten/kathedraal.png")));
            gebouwen.add(new GebouwKaart(5, "Burcht", Type.MILITAIR, new Image("Resources/Gebouwkaarten/burcht.png")));
        }
        for (int i = 0; i < 3; i++) {
            gebouwen.add(new GebouwKaart(4, "Paleis", Type.MONUMENT, new Image("Resources/Gebouwkaarten/paleis.png")));
            gebouwen.add(new GebouwKaart(2, "Winkels", Type.COMMERCIEL, new Image("Resources/Gebouwkaarten/winkels.png")));
            gebouwen.add(new GebouwKaart(4, "Haven", Type.COMMERCIEL, new Image("Resources/Gebouwkaarten/haven.png")));
            gebouwen.add(new GebouwKaart(3, "Handelshuis", Type.COMMERCIEL, new Image("Resources/Gebouwkaarten/handelshuis.png")));
            gebouwen.add(new GebouwKaart(3, "Toernooiveld", Type.MILITAIR, new Image("Resources/Gebouwkaarten/toernooiveld.png")));
            gebouwen.add(new GebouwKaart(1, "Wachttoren", Type.MILITAIR, new Image("Resources/Gebouwkaarten/wachttoren.png")));
            gebouwen.add(new GebouwKaart(2, "Gevangenis", Type.MILITAIR, new Image("Resources/Gebouwkaarten/gevangenis.png")));
            gebouwen.add(new GebouwKaart(1, "Tempel", Type.KERKELIJK, new Image("Resources/Gebouwkaarten/tempel.png")));
            gebouwen.add(new GebouwKaart(3, "Klooster", Type.KERKELIJK, new Image("Resources/Gebouwkaarten/klooster.png")));
            gebouwen.add(new GebouwKaart(3, "Kerk", Type.KERKELIJK, new Image("Resources/Gebouwkaarten/kerk.png")));
        }
        for (int i = 0; i < 4; i++) {
            gebouwen.add(new GebouwKaart(4, "Kasteel", Type.MONUMENT, new Image("Resources/Gebouwkaarten/kasteel.png")));
            gebouwen.add(new GebouwKaart(2, "Markt", Type.COMMERCIEL, new Image("Resources/Gebouwkaarten/markt.png")));
        }
        for (int i = 0; i < 5; i++) {
            gebouwen.add(new GebouwKaart(3, "Landgoed", Type.MONUMENT, new Image("Resources/Gebouwkaarten/landgoed.png")));
            gebouwen.add(new GebouwKaart(1, "Taveerne", Type.COMMERCIEL, new Image("Resources/Gebouwkaarten/taveerne.png")));
        }
        this.schuddenKaarten();
    }

    public void addGebouw(GebouwKaart gebouw) {
        this.gebouwen.add(gebouw);
    }

    public GebouwKaart trekKaart() {
        GebouwKaart gebouw = gebouwen.get(0);
        this.gebouwen.remove(gebouw);
        return gebouw;
    }

    private void schuddenKaarten() {
        Collections.shuffle(gebouwen);
    }

    public ArrayList<GebouwKaart> getGebouwen()
    {
        return this.gebouwen;
    }

}