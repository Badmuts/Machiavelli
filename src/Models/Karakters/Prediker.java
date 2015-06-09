package Models.Karakters;

import Enumerations.Type;
import Interfaces.Bonusable;
import Interfaces.Karakter;
import Models.GebouwKaart;
import Models.Speler;

import java.util.ArrayList;

/**
 * Created by daanrosbergen on 03/06/15.
 * Edited by Sander de Jong on 08/06/15.
 */
public class Prediker implements Karakter, Bonusable {

    private final String      naam                = "Prediker";
    private final int         nummer              = 5;
    private final int         bouwLimiet          = 1;
    private final Type        type                = Type.KERKELIJK;

    private Speler speler = null;

    public void gebruikEigenschap() {
        // TODO: beschermt tegen karakter Condotierre
    }

    public void ontvangenBonusGoud() {
        ArrayList<GebouwKaart> gebouwen = speler.getStad().getGebouwen();
        for(GebouwKaart gebouw: gebouwen) {
            if (gebouw.getType() == this.type)
                speler.getPortemonnee().ontvangenGoud(1);
        }
    }

    public void setSpeler(Speler speler) {
        this.speler = speler;
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
