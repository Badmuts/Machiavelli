package Models.Karakters;

import Enumerations.Type;
import Interfaces.Karakter;
import Models.Speler;

/** 
 * Created by daanrosbergen on 03/06/15.
 * Edit by Bernd Oostrum
 * 
 * De speler heeft het karakter Bouwmeester gekozen. 
 * De eigenschappen van dit karakter worden gebruikt
 * door de speler tijdens de duur van een ronde.
 * 
 * De Bouwmeester trekt 2 extra gebouwkaarten en mag
 * in zijn beurt 3 gebouwen bouwen.
 * 
 */
public class Bouwmeester implements Karakter {
	
	@SuppressWarnings("unused")
	private Speler speler = null;
	
	/** Eigenschappen van karakter Bouwmeester */
    private final int nummer = 7;	
    private final int bouwLimiet = 3; 
    private final String naam = "Bouwmeester";
    private final Type type = Type.NORMAAL;
	
    /**
	 * Overriden van de methode uit de interface Karakter,
	 * de Bouwmeester wordt aan de speler gekoppeld.
	 */
    @Override
    public void setSpeler(Speler speler) {
        this.speler = speler;
    }   
   
    /**
	 * overriden van de methode uit de interface Karakter
	 * ??????
	 */
    @Override
    public void gebruikEigenschap() {
        //TODO: 2 of 3 kaarten plaatsen in stad
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
