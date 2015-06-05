package Models;

import Enumerations.Type;
import Interfaces.Karakter;

import java.util.ArrayList;

/**
 * Created by daanrosbergen on 03/06/15.
 * Edited by Sander de Jong on 05/06/15.
 */

public class Magier implements Karakter {

    // Variables
    private String naam = "Magiër";
    private int nummer = 3;
    private int bouwLimiet = 1;
    private Type type = Type.NORMAAL;

    @Override
    public void gebruikEigenschap() {
        // TODO: ruilen bouwkaarten
        System.out.println("faka");
    }

    // Ruil alle bouwkaarten met alle bouwkaarten van een ander speler/karakter??
    public void ruilMetKarakter(Speler target, Speler magier)
    {
        ArrayList<GebouwKaart> handTarget = target.getHand().getKaartenLijst();
        ArrayList<GebouwKaart> magierHand = magier.getHand().getKaartenLijst();
        target.getHand().setKaartenLijst(magierHand);
        magier.getHand().setKaartenLijst(handTarget);
    }

    // Leg een x aantal kaarten af op de stapel en pak een gelijk aantal nieuwe kaarten
    public void ruilMetStapel(Hand spelerHand)
    {

    }

    // Return karakternummer
    public int getNummer() {
        return nummer;
    }

    // Return gebouwlimiet
    public int getBouwLimiet() {
        return bouwLimiet;
    }
}
