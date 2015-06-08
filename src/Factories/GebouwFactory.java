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
        // 1 Bibliotheek: 6 punten: Normaal
        gebouwen.add(new GebouwKaart(6, "Bibliotheek", Type.NORMAAL, new Image("../Resources/Gebouwkaarten/bibliotheek.png")));
        // 1 Werkplaats: 5 punten: Normaal
        gebouwen.add(new GebouwKaart(5, "Werkplaats", Type.NORMAAL, new Image("../Resources/Gebouwkaarten/werkplaats.png")));
        // 1 School voor magiers: 6 punten: Normaal
        gebouwen.add(new GebouwKaart(6, "School voor Magiërs", Type.NORMAAL, new Image("../Resources/Gebouwkaarten/school-voor-magiers.png")));
        // 1 Laboratoruim: 5 punten: Normaal
        gebouwen.add(new GebouwKaart(5, "Laboratorium", Type.NORMAAL, new Image("../Resources/Gebouwkaarten/laboratorium.png")));
        // 1 Drakenpoort: 6 punten: Normaal
        gebouwen.add(new GebouwKaart(6, "Drakenpoort", Type.NORMAAL, new Image("../Resources/Gebouwkaarten/drakenpoort.png")));
        // 1 Kerkhof: 5 punten: Normaal
        gebouwen.add(new GebouwKaart(5, "Kerkhof", Type.NORMAAL, new Image("../Resources/Gebouwkaarten/kerkhof.png")));
        // 1 Universiteit: 6 punten: Normaal
        gebouwen.add(new GebouwKaart(6, "Universiteit", Type.NORMAAL, new Image("../Resources/Gebouwkaarten/universiteit.png")));
        // 1 Observatorium: 5 punten: Normaal
        gebouwen.add(new GebouwKaart(5, "Observatorium", Type.NORMAAL, new Image("../Resources/Gebouwkaarten/observatorium.png")));
        // 1 Hof der Wonderen: 2 punten: Normaal
        gebouwen.add(new GebouwKaart(2, "Hof der Wonderen", Type.NORMAAL, new Image("../Resources/Gebouwkaarten/hof-der-wonderen.png")));
        for (int i = 0; i < 2; i++) {
            // 2 Kerker: 3 punten: Normaal
            gebouwen.add(new GebouwKaart(2, "Kerker", Type.NORMAAL, new Image("../Resources/Gebouwkaarten/kerker.png")));
            // 2 Raadhuis: 5 punten: Commerciel
            gebouwen.add(new GebouwKaart(5, "Raadhuis", Type.COMMERCIEL, new Image("../Resources/Gebouwkaarten/raadhuis.png")));
            // 2 Kathedraal: 5 punten: Kerkelijk
            gebouwen.add(new GebouwKaart(5, "Kathedraal", Type.KERKELIJK, new Image("../Resources/Gebouwkaarten/kathedraal.png")));
            // 2 Burcht: 5 punten: Militair
            gebouwen.add(new GebouwKaart(5, "Burcht", Type.MILITAIR, new Image("../Resources/Gebouwkaarten/burcht.png")));
        }
        for (int i = 0; i < 3; i++) {
            // 3 Paleis: 4 punten: Monument
            gebouwen.add(new GebouwKaart(4, "Paleis", Type.MONUMENT, new Image("../Resources/Gebouwkaarten/paleis.png")));
            // 3 Winkels: 2 punten: Commerciel
            gebouwen.add(new GebouwKaart(2, "Winkels", Type.COMMERCIEL, new Image("../Resources/Gebouwkaarten/winkels.png")));
            // 3 Haven: 4 punten: Commerciel
            gebouwen.add(new GebouwKaart(4, "Haven", Type.COMMERCIEL, new Image("../Resources/Gebouwkaarten/haven.png")));
            // 3 Handelshuis: 3 punten: Commerciel
            gebouwen.add(new GebouwKaart(3, "Handelshuis", Type.COMMERCIEL, new Image("../Resources/Gebouwkaarten/handelshuis.png")));
            // 3 Toernooiveld: 3 punten: Militair
            gebouwen.add(new GebouwKaart(3, "Toernooiveld", Type.MILITAIR, new Image("../Resources/Gebouwkaarten/toernooiveld.png")));
            // 3 Wachttoren: 1 punt: Militair
            gebouwen.add(new GebouwKaart(1, "Wachttoren", Type.MILITAIR, new Image("../Resources/Gebouwkaarten/wachttoren.png")));
            // 3 Gevangenis: 2 punten: Militair
            gebouwen.add(new GebouwKaart(2, "Gevangenis", Type.MILITAIR, new Image("../Resources/Gebouwkaarten/gevangenis.png")));
            // 3 Tempel: 1 punt: Kerkelijk
            gebouwen.add(new GebouwKaart(1, "Tempel", Type.KERKELIJK, new Image("../Resources/Gebouwkaarten/tempel.png")));
            // 3 Klooster: 3 punten: Kerkelijk
            gebouwen.add(new GebouwKaart(3, "Klooster", Type.KERKELIJK, new Image("../Resources/Gebouwkaarten/klooster.png")));
            // 3 Kerk: 2 punten: Kerkelijk
            gebouwen.add(new GebouwKaart(3, "Kerk", Type.KERKELIJK, new Image("../Resources/Gebouwkaarten/kerk.png")));
        }
        for (int i = 0; i < 4; i++) {
            // 4 Kasteel: 4 punten: Monument
            gebouwen.add(new GebouwKaart(4, "Kasteel", Type.MONUMENT, new Image("../Resources/Gebouwkaarten/kasteel.png")));
            // 4 Markt: 2 punten: Commerciel
            gebouwen.add(new GebouwKaart(2, "Markt", Type.COMMERCIEL, new Image("../Resources/Gebouwkaarten/markt.png")));
        }
        for (int i = 0; i < 5; i++) {
            // 5 Landgoed: 3 punten: Monument
            gebouwen.add(new GebouwKaart(3, "Landgoed", Type.MONUMENT, new Image("../Resources/Gebouwkaarten/landgoed.png")));
            // 5 Taveerne: 1 punt: Commerciel
            gebouwen.add(new GebouwKaart(1, "Taveerne", Type.COMMERCIEL, new Image("../Resources/Gebouwkaarten/taveerne.png")));
        }
        this.schuddenKaarten();
    }

    public void addGebouw(GebouwKaart gebouw) {
        this.gebouwen.add(gebouw);
    }

    public GebouwKaart trekKaart() {
        GebouwKaart gebouw = gebouwen.get(0);
        gebouwen.remove(gebouw);
        return gebouw;
    }

    private void schuddenKaarten() {
        Collections.shuffle(gebouwen);
    }

}