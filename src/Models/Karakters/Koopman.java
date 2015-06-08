package Models.Karakters;

import Enumerations.Type;
import Interfaces.Bonusable;
import Interfaces.Karakter;
import Models.GebouwKaart;
import Models.Speler;

import java.util.ArrayList;

/**
 * Created by daanrosbergen on 03/06/15.
 */
public class Koopman implements Karakter, Bonusable {

    private String      naam                = "Koopman";
    private int         nummer              = 6;
    private int         bouwLimiet          = 1;
    private Type        type                = Type.COMMERCIEL;
    private Speler      speler              = null;

    @Override
    public void gebruikEigenschap() {
    }

    @Override
    public void setSpeler(Speler speler) {
        this.speler = speler;
    }

    @Override
    public void ontvangenBonusGoud() {
        ArrayList<GebouwKaart> gebouwen = speler.getStad().getGebouwen();
        for(GebouwKaart gebouw: gebouwen) {
            if (gebouw.getType() == Type.COMMERCIEL)
                speler.getPortemonnee().ontvangenGoud(1);
        }
    }
}
