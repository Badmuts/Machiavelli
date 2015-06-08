
package Models.Karakters;

import Enumerations.Type;
import Interfaces.Karakter;
import Models.Speler;

/**
 * Created by daanrosbergen on 03/06/15.
 */
public class Moordenaar implements Karakter {

    private final String      naam                = "Moordenaar";
    private final int         nummer              = 1;
    private final int         bouwLimiet          = 1;
    private final Type        type                = Type.NORMAAL;

    private Karakter    vermoordKarakter    = null;
    private Speler      speler              = null;

    @Override
    public void gebruikEigenschap() {
        // TODO: vermoord karakter
    }

    @Override
    public void setSpeler(Speler speler) {

    }

    public void setVermoordKarakter(Karakter karakter) {
        this.vermoordKarakter = karakter;
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



