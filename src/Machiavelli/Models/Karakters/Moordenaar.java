
package Machiavelli.Models.Karakters;

import Machiavelli.Enumerations.Type;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Models.Speler;
import javafx.scene.image.Image;

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
    
	/** Eigenschappen van karakter Moordenaar. */
    private final int nummer = 1;	
    private final int bouwLimiet = 1; 
    private final String naam = "Moordenaar";
    private final Type type = Type.NORMAAL;
    private Object target;

    /**
     * Overriden van de methode uit de interface Karakter,
     * de Moordenaar wordt aan de speler gekoppeld.
     */
    @Override
    public void setSpeler(Speler speler) {
    	this.speler = speler;
    }
    @Override
    public Speler getSpeler() {
    	return speler;
    }
    
    @Override
    public void setTarget(Object target) {
    	this.target = (Karakter) target;
    }
    
    /**
	 * overriden van de methode uit de interface Karakter
	 * en een karakter vermoorden die vervolgens een beurt
	 * overslaat.
	 */
    
    @Override
    public void gebruikEigenschap() {
        // TODO: vermoord karakter
    	if (target != null) {
    		vermoordKarakter(this.getVermoordKarakter());
    	}
    	else {
    		//TODO: view aanroepen
    	}
    }
    
    //beurt overslaan met ifjes???

    public void vermoordKarakter(Karakter target) {
    	target.beurtOverslaan();
    }

    public Karakter getVermoordKarakter() {
		return (Karakter)target;
	}

	public String getNaam() {
    	return this.naam;
    }
   
    public int getNummer() {
    	return this.nummer;
    }

    @Override
    public int getBouwLimiet() {
        return this.bouwLimiet;
    }

    public Type getType() {
		return this.type;
	}

    @Override
    public Image getImage() {
        return null;
    }

    @Override
    public void beurtOverslaan() {

    }
}



