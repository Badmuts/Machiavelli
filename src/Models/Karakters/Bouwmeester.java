package Models.Karakters;

import Enumerations.Type;
import Interfaces.Karakter;
import Models.Speler;

/**
 * Created by daanrosbergen on 03/06/15.
 */
public class Bouwmeester implements Karakter {
	
	private final String      naam                = "Bouwmeester";
    private final int         nummer              = 7;
    private final int         bouwLimiet          = 3;
    private final Type        type                = Type.NORMAAL;

    private Speler      speler              = null;

    @Override
    public void gebruikEigenschap() {
        //TODO: 2 of 3 kaarten plaatsen in stad
    }

    @Override
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
