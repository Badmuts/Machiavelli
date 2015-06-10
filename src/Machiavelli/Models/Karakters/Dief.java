package Machiavelli.Models.Karakters;

import Machiavelli.Enumerations.Type;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Models.Speler;

/** 
 * Created by daanrosbergen on 03/06/15.
 * Edit by Bernd Oostrum
 * 
 * De speler heeft het karakter Dief gekozen. 
 * De eigenschappen van dit karakter worden gebruikt
 * door de speler tijdens de duur van een ronde.
 * 
 * De dief kan als eigenschap een ander karakter bestelen
 * van al zijn goudstukken. De karakters moordenaar en het
 * het slachtoffer van de moordenaar kunnen niet gekozen worden
 * de dief ontvangt pas de goudstukken als het bestolen karakter
 * aan de beurt is
 */

public class Dief implements Karakter {
	
	private Karakter    besteelKarakter     = null;
    @SuppressWarnings("unused")
	private Speler      speler              = null;
    
	/** Eigenschappen van karakter Dief. */
    private final int nummer = 2;	
    private final int bouwLimiet = 1; 
    private final String naam = "Dief";
    private final Type type = Type.NORMAAL;
	
    /**
   	 * Overriden van de methode uit de interface Karakter,
   	 * de Dief wordt aan de speler gekoppeld.
   	 */
   	@Override
   	public void setSpeler(Speler speler) {
           this.speler = speler;
       }

   	/**
	 * overriden van de methode uit de interface Karakter
	 * en aanroepen van de methode selectKarakterView
	 * Er wordt gewacht op de keuze van de speler. 
	 * Vervolgens wordt het het gekozen karakter bestolen van
	 * al zijn goudstukken op het moment dat deze aan de beurt is. 
	 */
    @Override
    public void gebruikEigenschap() {
        // TODO: besteel karakter
    }
    
    public void setBesteelKarakter(Karakter besteelKarakter) {
		this.besteelKarakter = besteelKarakter;
	}
    
    public Karakter getBesteelKarakter() {
		return besteelKarakter;
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

    public int getBouwlimiet() {
    	return this.bouwLimiet;
    }
    
	public Type getType() {
		return this.type;
	}

}
