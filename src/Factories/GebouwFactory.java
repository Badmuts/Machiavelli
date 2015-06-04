package Factories;

import Models.GebouwKaart;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by daanrosbergen on 04/06/15.
 */
public class GebouwFactory {

    private ArrayList<GebouwKaart> gebouwen = new ArrayList<GebouwKaart>();

    private static GebouwKaart createGebouwKaart() {
        // TODO: Hoe gaan we dit doen?
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