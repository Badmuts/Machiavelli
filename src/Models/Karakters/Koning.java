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

    private String      naam                = "Koning";
    private int         nummer              = 4;
    private int         bouwLimiet          = 1;
    private Type        type                = Type.MONUMENT;
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
            if (gebouw.getType() == Type.MONUMENT)
                speler.getPortemonnee().ontvangenGoud(1);
        }
    }
}
