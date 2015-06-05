package Models;

import Enumerations.Type;
import Interfaces.Karakter;

/**
 * Created by daanrosbergen on 03/06/15.
 */
public class Koopman implements Karakter {

    private String      naam                = "Koopman";
    private int         nummer              = 6;
    private int         bouwLimiet          = 1;
    private Type        type                = Type.COMMERCIEL;
    
    @Override
    public void gebruikEigenschap() {
        // TODO: ontvangt 1 goudstuk
    	
    	
    }
}
