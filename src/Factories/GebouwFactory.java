package Factories;

import Enumerations.Type;
import Models.GebouwKaart;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by daanrosbergen on 04/06/15.
 */
public class GebouwFactory {

    private ArrayList<GebouwKaart> gebouwen = new ArrayList<GebouwKaart>();

    public GebouwFactory() {
        // 1 Bibliotheek: 6 punten: Normaal
        gebouwen.add(new GebouwKaart(6, "Bibliotheek", Type.NORMAAL));
        // 1 Werkplaats: 5 punten: Normaal
        gebouwen.add(new GebouwKaart(5, "Werkplaats", Type.NORMAAL));
        // 1 School voor magiers: 6 punten: Normaal
        gebouwen.add(new GebouwKaart(6, "School voor Magiër", Type.NORMAAL));
        // 1 Laboratoruim: 5 punten: Normaal
        gebouwen.add(new GebouwKaart(5, "Laboratorium", Type.NORMAAL));
        // 1 Drakenpoort: 6 punten: Normaal
        gebouwen.add(new GebouwKaart(6, "Drakenpoort", Type.NORMAAL));
        // 1 Kerkhof: 5 punten: Normaal
        gebouwen.add(new GebouwKaart(5, "Kerkhof", Type.NORMAAL));
        // 1 Universiteit: 6 punten: Normaal
        gebouwen.add(new GebouwKaart(6, "Universiteit", Type.NORMAAL));
        // 1 Observatorium: 5 punten: Normaal
        gebouwen.add(new GebouwKaart(5, "Observatorium", Type.NORMAAL));
        // 1 Hof der Wonderen: 2 punten: Normaal
        gebouwen.add(new GebouwKaart(2, "Hof der Wonderen", Type.NORMAAL));
        for (int i = 0; i < 2; i++) {
            // 2 Kerker: 3 punten: Normaal
            gebouwen.add(new GebouwKaart(2, "Kerker", Type.NORMAAL));
            // 2 Raadhuis: 5 punten: Commerciel
            gebouwen.add(new GebouwKaart(5, "Raadhuis", Type.COMMERCIEL));
            // 2 Kathedraal: 5 punten: Kerkelijk
            gebouwen.add(new GebouwKaart(5, "Kathedraal", Type.KERKELIJK));
            // 2 Burcht: 5 punten: Militair
            gebouwen.add(new GebouwKaart(5, "Burcht", Type.MILITAIR));
        }
        for (int i = 0; i < 3; i++) {
            // 3 Paleis: 4 punten: Monument
            gebouwen.add(new GebouwKaart(4, "Paleis", Type.MONUMENT));
            // 3 Winkels: 2 punten: Commerciel
            gebouwen.add(new GebouwKaart(2, "Winkels", Type.COMMERCIEL));
            // 3 Haven: 4 punten: Commerciel
            gebouwen.add(new GebouwKaart(4, "Haven", Type.COMMERCIEL));
            // 3 Handelshuis: 3 punten: Commerciel
            gebouwen.add(new GebouwKaart(3, "Handelshuis", Type.COMMERCIEL));
            // 3 Toernooiveld: 3 punten: Militair
            gebouwen.add(new GebouwKaart(3, "Toernooiveld", Type.MILITAIR));
            // 3 Wachttoren: 1 punt: Militair
            gebouwen.add(new GebouwKaart(1, "Wachttoren", Type.MILITAIR));
            // 3 Gevangenis: 2 punten: Militair
            gebouwen.add(new GebouwKaart(2, "Gevangenis", Type.MILITAIR));
            // 3 Tempel: 1 punt: Kerkelijk
            gebouwen.add(new GebouwKaart(1, "Tempel", Type.KERKELIJK));
            // 3 Klooster: 3 punten: Kerkelijk
            gebouwen.add(new GebouwKaart(3, "Klooster", Type.KERKELIJK));
            // 3 Kerk: 2 punten: Kerkelijk
            gebouwen.add(new GebouwKaart(3, "Kerk", Type.KERKELIJK));
        }
        for (int i = 0; i < 4; i++) {
            // 4 Kasteel: 4 punten: Monument
            gebouwen.add(new GebouwKaart(4, "Kasteel", Type.MONUMENT));
            // 4 Markt: 2 punten: Commerciel
            gebouwen.add(new GebouwKaart(2, "Markt", Type.COMMERCIEL));
        }
        for (int i = 0; i < 5; i++) {
            // 5 Landgoed: 3 punten: Monument
            gebouwen.add(new GebouwKaart(3, "Landgoed", Type.MONUMENT));
            // 5 Taveerne: 1 punt: Commerciel
            gebouwen.add(new GebouwKaart(1, "Taveerne", Type.COMMERCIEL));
        }
    }

    public void addGebouw(GebouwKaart gebouw) {
        this.gebouwen.add(gebouw);
    }

    public GebouwKaart trekKaart() {
        GebouwKaart gebouw = gebouwen.get(0);
        gebouwen.remove(gebouw);
        return gebouw;
    }

    public void schuddenKaarten() {
        Collections.shuffle(gebouwen);
    }

}