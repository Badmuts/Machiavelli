package Machiavelli.Models.Karakters;

import Machiavelli.Enumerations.Type;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Models.Speler;

import java.rmi.RemoteException;
import java.util.ArrayList;

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
	
	private Speler speler = null;
	private Karakter target = null;
    
	/** Eigenschappen van karakter Dief. */
    private final int nummer = 2;	
    private final int bouwLimiet = 1; 
    private final String naam = "Dief";
    private final Type type = Type.NORMAAL;
    
    /*Afbeelding van de Dief*/
    private final String image = "Machiavelli/Resources/Karakterkaarten/Portrait-Dief.png";
    private ArrayList<KarakterObserver> observers = new ArrayList<>();

    /**
   	 * Overriden van de methode uit de interface Karakter,
   	 * de Dief wordt aan de speler gekoppeld.
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
        gebruikEigenschap();
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
    	if (target != null && target.getNaam() != "Moordenaar") {
    		BesteelKarakter(this.speler, getTarget());
    		
    	}
    	else {
    		//TODO view aanroepen om target te selecteren
    	}
    }
    
    private void BesteelKarakter(Speler speler, Karakter target) {
		try {
			speler.getGoudVanBank(speler.getSpel().getBank(), target.getSpeler().getPortemonnee().getGoudMunten());
			target.getSpeler().setGoudOpBank(target.getSpeler().getPortemonnee(), target.getSpeler().getPortemonnee().getGoudMunten());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
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
	
	public Karakter getTarget() {
		return this.target;
	}

    @Override
    public String getImage() throws RemoteException {
        return this.image;
    }

    @Override
    public void beurtOverslaan() {

    }

}
