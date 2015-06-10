package Machiavelli.Models.Karakters;

import Machiavelli.Enumerations.Type;
import Machiavelli.Interfaces.Bonusable;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Models.GebouwKaart;
import Machiavelli.Models.Speler;

import java.util.ArrayList;

/**
 * Created by daanrosbergen on 03/06/15.
 * Edited by Sander de Jong on 08/06/15.
 * 
 * De speler heeft het karakter Prediker gekozen. 
 * De eigenschappen van dit karakter worden gebruikt
 * door de speler tijdens de duur van een ronde.
 * 
 * Uit de stad van de prediker kunnen geen gebouwen worden
 * verwijderd.
 */
public class Prediker implements Karakter, Bonusable {
	
	private Speler speler = null;

	/** Eigenschappen van karakter Prediker. */
    private final int nummer = 5;	
    private final int bouwLimiet = 1; 
    private final String naam = "Prediker";
    private final Type type = Type.KERKELIJK;
    
    @Override
    public void setSpeler(Speler speler) {
        this.speler = speler;
    }

    @Override
    public void gebruikEigenschap() {
        // TODO: beschermt tegen karakter Condotierre
    }
    
    /** ontvangen bonusgoud voor Kerk gebouwen */
    public void ontvangenBonusGoud() {
        ArrayList<GebouwKaart> gebouwen = speler.getStad().getGebouwen();
        for(GebouwKaart gebouw: gebouwen) {
            if (gebouw.getType() == this.type)
                speler.getPortemonnee().ontvangenGoud(1);
        }
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
