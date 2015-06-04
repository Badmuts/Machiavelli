package Models;

import Enummerations.Type;
import Interfaces.Karakter;

/**
 * Created by daanrosbergen on 03/06/15.
 */




public class Dief implements Karakter {
	
	private String      naam                = "Moordenaar";
	private int         nummer              = 1;
	private int         bouwLimiet          = 1;
	private Type        type                = Type.NORMAAL;
	private Karakter    besteelKarakter    = null;
	
	
    @Override
    public void gebruikEigenschap() {
        // TODO: besteel karakter
    }
    
   public void setbesteelKarakter(Karakter karakter) {
        this.besteelKarakter = karakter;
    }
}
