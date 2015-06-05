package Models;

import Enumerations.Type;
import Interfaces.Karakter;

/**
 * Created by daanrosbergen on 03/06/15.
 */
public class Bouwmeester implements Karakter {
	
	private String      naam                = "Bouwmeester";
    private int         nummer              = 7;
    private int         bouwLimiet          = 3;
    private Type        type                = Type.NORMAAL;
   
	
	
    @Override
    public void gebruikEigenschap() {
        //TODO: 2 of 3 kaarten plaatsen in stad
    }
}
