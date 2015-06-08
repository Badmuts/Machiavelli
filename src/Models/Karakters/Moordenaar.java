
package Models.Karakters;

import Enumerations.Type;
import Interfaces.Karakter;
import Models.Speler;

/**
 * Created by daanrosbergen on 03/06/15.
 */
public class Moordenaar implements Karakter {

    private String      naam                = "Moordenaar";
    private int         nummer              = 1;
    private int         bouwLimiet          = 1;
    private Type        type                = Type.NORMAAL;
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
}



