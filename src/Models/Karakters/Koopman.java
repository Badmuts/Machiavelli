package Models.Karakters;

import Enumerations.Type;
import Interfaces.Bonusable;
import Interfaces.Karakter;
import Models.GebouwKaart;
import Models.Speler;

import java.util.ArrayList;

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
public class Koopman implements Karakter, Bonusable {
	
    // Eigenschappen van karakter Koopman
    private final int nummer = 6;
    private final int bouwLimiet = 1;
    private final String naam = "Koopman";
    private final Type type = Type.COMMERCIEL;
    private Speler speler = null;
	
	


	//overriden van de methode uit de interface Karakter
	@Override
    public void gebruikEigenschap() {
		// TODO: ontvangt 1 goudstuk	
		ontvangenBonusGoud();
       }
	
	//Overriden van de methode uit de interface Karakter
	@Override
	public void setSpeler(Speler speler) {
        this.speler = speler;
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
    //return gebouwtype
	public Type getType() {
		return this.type;
	}
    
	//ontvangen bonusgoud voor commerciele gebouwen
    @Override
    public void ontvangenBonusGoud() {
        ArrayList<GebouwKaart> gebouwen = speler.getStad().getGebouwen();
        for(GebouwKaart gebouw: gebouwen) {
            if (gebouw.getType() == this.type)
                speler.getPortemonnee().ontvangenGoud(1);
        }
    }

    public int getBouwLimiet() {
        return bouwLimiet;
    }
}
