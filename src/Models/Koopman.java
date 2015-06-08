package Models;

import Enumerations.Type;
import Interfaces.Karakter;

/**
 * 
 * Create by Bernd Oostrum
 */


/** De speler heeft het karakter koopman gekozen. 
 * De eigenschappen van dit karakter worden gebruikt
 * door de speler tijdens de duur van een ronde.
 * 
 *
 */
public class Koopman implements Karakter {
	
    // Eigenschappen van karakter Koopman
    private int nummer = 6;
    private int bouwLimiet = 1;
    private String naam = "Koopman";
    private Type type = Type.COMMERCIEL;
	
	


	//overriden van de methode uit de interface Karakter
	@Override
    public void gebruikEigenschap() {
		// TODO: ontvangt 1 goudstuk	
		//ontvangenBonusGoud(null);
		
    	
    	
       }
    
    //ontvangen bonusgoud
    public void ontvangenBonusGoud(Speler koopman){
    	
    	koopman.getPortemonnee().ontvangenBonusGoud(1);
    	

    	

    }
    // Return karakternaam
    public String getNaam(){
    	return this.naam;
    }
   
    // Return karakternummer
    public int getNummer(){
    	return this.nummer;
    }
    
    // Return bouwlimiet
    public int getBouwlimiet(){
    	return this.bouwLimiet;
    }

	public Type getType() {
		return this.type;
	}
    
    
    
    
}

