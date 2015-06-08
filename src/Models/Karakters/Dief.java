package Models.Karakters;

import Enumerations.Type;
import Interfaces.Karakter;
import Models.Speler;

/**
 * Created by daanrosbergen on 03/06/15.
 */

public class Dief implements Karakter {
	
	private String      naam                = "Dief";
	private int         nummer              = 2;
	private int         bouwLimiet          = 1;
	private Type        type                = Type.NORMAAL;
	private Karakter    besteelKarakter     = null;
    private Speler      speler              = null;

    @Override
    public void gebruikEigenschap() {
        // TODO: besteel karakter
    }

    @Override
    public void setSpeler(Speler speler) {
        this.speler = speler;
    }

    public void setbesteelKarakter(Karakter karakter) {
        this.besteelKarakter = karakter;
    }
}
