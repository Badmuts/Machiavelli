package Models;

import Enummerations.Type;
import Interfaces.Karakter;

/**
 * Created by daanrosbergen on 03/06/15.
 */

private String      naam                = "Magiër";
private int         nummer              = 3;
private int         bouwLimiet          = 1;
private Type        type                = Type.NORMAAL;



public class Magier implements Karakter {
    @Override
    public void gebruikEigenschap() {
        // TODO: ruilen bouwkaarten
    }
}
