package Models;

import Enumerations.Type;
import Interfaces.Karakter;

/**
 * Created by daanrosbergen on 03/06/15.
 */





public class Magier implements Karakter {

    // Dit stond buiten de klasse.
    private String naam = "Magiër";
    private int         nummer              = 3;
    private int         bouwLimiet          = 1;
    private Type        type                = Type.NORMAAL;

    @Override
    public void gebruikEigenschap() {
        // TODO: ruilen bouwkaarten
    }
}
