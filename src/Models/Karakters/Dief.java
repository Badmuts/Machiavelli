package Models.Karakters;

import Enumerations.Type;
import Interfaces.Karakter;
import Models.Speler;

/**
 * Created by daanrosbergen on 03/06/15.
 */

public class Dief implements Karakter {
	
	private final String      naam                = "Dief";
	private final int         nummer              = 2;
	private final int         bouwLimiet          = 1;
	private final Type        type                = Type.NORMAAL;

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
