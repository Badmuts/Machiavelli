
package Machiavelli.Models.Karakters;

import Machiavelli.Enumerations.Type;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Models.Speler;

/** 
 * Created by daanrosbergen on 03/06/15.
 * Edit by Bernd Oostrum
 * 
 * De speler heeft het karakter Magier gekozen. 
 * De eigenschappen van dit karakter worden gebruikt
 * door de speler tijdens de duur van een ronde.
 * 
 * De Moordenaar kan als karaktereigenschap een ander
 * karakter vermoorden. Het vermoorde karakter speelt
 * deze ronde niet mee. 
 */
public class Moordenaar implements Karakter {
	
	private Speler speler = null;
	private Karakter vermoordKarakter = null;
    
	/** Eigenschappen van karakter Moordenaar. */
    private final int nummer = 1;	
    private final int bouwLimiet = 1; 
    private final String naam = "Moordenaar";
    private final Type type = Type.NORMAAL;
    
    /**
     * Overriden van de methode uit de interface Karakter,
     * de Moordenaar wordt aan de speler gekoppeld.
     */
    @Override
    public void setSpeler(Speler speler) {
    	this.speler = speler;
    }
    
    /**
	 * overriden van de methode uit de interface Karakter
	 * en een karakter vermoorden die vervolgens een beurt
	 * overslaat.
	 */
    
    @Override
    public void gebruikEigenschap() {
        // TODO: vermoord karakter
    	getVermoordKarakter();
    }

    public void setVermoordKarakter(Karakter karakter) {
        this.vermoordKarakter = karakter;
    }

    public Karakter getVermoordKarakter() {
		return vermoordKarakter;
	}

	public String getNaam() {
    	return this.naam;
    }
   
    public int getNummer() {
    	return this.nummer;
    }
    
    public int getBouwlimiet() {
    	return this.bouwLimiet;
    }
    
	public Type getType() {
		return this.type;
	}
}



