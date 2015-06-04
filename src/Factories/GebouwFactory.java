package Factories;

import Models.GebouwKaart;

import java.util.ArrayList;

/**
 * Created by daanrosbergen on 04/06/15.
 */
public class GebouwFactory {

    private ArrayList<GebouwKaart> gebouwen = new ArrayList<GebouwKaart>();

    public GebouwFactory() {
        for (int i = 0; i < 65; i++) {
            // TODO: Hoe gaan we dit doen? Moet ik zelf de 65 unieke
            //       kaarten aanmaken of handelt GebouwKaart dat af?
            gebouwen.add(new GebouwKaart());
        }
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


}
