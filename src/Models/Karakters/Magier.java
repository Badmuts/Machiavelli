package Models.Karakters;

import Enumerations.Type;
import Factories.GebouwFactory;
import Interfaces.Karakter;
import Models.GebouwKaart;
import Models.Hand;
import Models.Speler;

import java.util.ArrayList;

/**
 * Created by daanrosbergen on 03/06/15.
 * Edited by Sander de Jong on 05/06/15.
 */

public class Magier implements Karakter {

    // Variables
    private final String naam = "Magier";
    private final int nummer = 3;
    private final int bouwLimiet = 1;
    private final Type type = Type.NORMAAL;

    private Speler  speler  = null;
    private Object  target  = null;
    private ArrayList<GebouwKaart> ruilLijst = new ArrayList<GebouwKaart>();

    @Override
    public void gebruikEigenschap() {
        // TODO: ruilen bouwkaarten
        System.out.println("faka");
        while (getTarget() != null) {
            // Iets tonen/afvangen om target te setten (View aanpassen?)
            if (getTarget().equals(speler.getSpel().getGebouwFactory())) {
                // Als het target de stapel met gebouwkaarten is
                // ruilen met stapelkaarten implementeren
                // Afvangen ruil lijst
                ruilMetStapel(speler.getHand(), ruilLijst);
                break;
            } else {
                // Ruil kaarten met een speler.
                ruilMetKarakter((Speler)getTarget(), this.speler);
                break;
            }
        }
    }

    @Override
    public void setSpeler(Speler speler) {
        this.speler = speler;
    }

    // Ruil alle bouwkaarten met alle bouwkaarten van een ander speler/karakter??
    private void ruilMetKarakter(Speler target, Speler magier)
    {
        ArrayList<GebouwKaart> handTarget = target.getHand().getKaartenLijst();
        ArrayList<GebouwKaart> magierHand = magier.getHand().getKaartenLijst();
        target.getHand().setKaartenLijst(magierHand);
        magier.getHand().setKaartenLijst(handTarget);
    }

    // Leg een x aantal kaarten af op de stapel en pak een gelijk aantal nieuwe kaarten
    private void ruilMetStapel(Hand hand, ArrayList<GebouwKaart> ruilLijst)
    {
        // Afleggen en tellen gebouwkaarten.
        int count = 0;
        for (int i = 0; i < ruilLijst.size(); i++)
        {
            hand.removeGebouw(ruilLijst.get(i));
            count ++;
        }

        // Trek nieuwe kaarten. Misschien functie maken die een lijst van gebouwen aan hand kan toevoegen?
        ArrayList<GebouwKaart> tempList = hand.getSpeler().trekkenKaart(count);;
        for (int i = 0; i < tempList.size(); i++)
        {
            hand.addGebouw(tempList.get(i));
        }
    }

    // Return karakternummer
    public int getNummer() {
        return this.nummer;
    }

    // Return gebouwlimiet
    public int getBouwLimiet() {
        return this.bouwLimiet;
    }

    public Type getType() {
        return type;
    }

    public String getNaam() {

        return naam;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(GebouwFactory target) {
        this.target = target;
    }

    public void setTarget(Speler target) {
        this.target = target;
    }

    public void setRuilLijst(ArrayList<GebouwKaart> ruilLijst) {
        this.ruilLijst = ruilLijst;
    }
}
