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
public class Koning implements Karakter, Bonusable {

    private final String      naam                = "Koning";
    private final int         nummer              = 4;
    private final int         bouwLimiet          = 1;
    private final Type        type                = Type.MONUMENT;

    private Speler      speler              = null;

    @Override
    public void gebruikEigenschap() {
        // TODO: begint beurt
    }

    @Override
    public void setSpeler(Speler speler) {

    }

    @Override
    public void ontvangenBonusGoud() {
        ArrayList<GebouwKaart> gebouwen = speler.getStad().getGebouwen();
        for(GebouwKaart gebouw: gebouwen) {
            if (gebouw.getType() == this.type)
                speler.getPortemonnee().ontvangenGoud(1);
        }
    }

    public Type getType() {
        return type;
    }

    public int getBouwLimiet() {

        return bouwLimiet;
    }

    public int getNummer() {

        return nummer;
    }

    public String getNaam() {

        return naam;
    }
}
