package Factories;

import Models.GebouwKaart;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by daanrosbergen on 04/06/15.
 */
public class GebouwFactory {

    private ArrayList<GebouwKaart> gebouwen = new ArrayList<GebouwKaart>();

    public GebouwKaart createGebouwKaart() {
        // TODO:
    }

    public void addGebouw(GebouwKaart gebouw) {
        this.gebouwen.add(gebouw);
    }

    public ArrayList<GebouwKaart> trekkenKaarten() {
        ArrayList<GebouwKaart> tmpGebouwKaartenArray = new ArrayList<GebouwKaart>();
        tmpGebouwKaartenArray.add(gebouwen.get(1));
        tmpGebouwKaartenArray.add(gebouwen.get(2));
        return tmpGebouwKaartenArray;
    }

    public void schuddenKaarten() {
        Collections.shuffle(gebouwen);
    }

}