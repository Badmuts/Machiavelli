package Machiavelli.Models.Karakters;

import Machiavelli.Enumerations.Type;
import Machiavelli.Interfaces.Bonusable;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Models.GebouwKaart;
import Machiavelli.Models.Speler;
import javafx.scene.image.Image;

import java.rmi.RemoteException;
import java.util.ArrayList;

/** 
 * Created by daanrosbergen on 03/06/15.
 * Edit by Bernd Oostrum
 * 
 * De speler heeft het karakter Koning gekozen. 
 * De eigenschappen van dit karakter worden gebruikt
 * door de speler tijdens de duur van een ronde.
 * 
 * De Koning mag de volgende ronde als eerst een karakter kiezen.
 * Ook ontvangt de koning 1 goudstuk voor elk monument gebouw
 * in zijn stad.
 */
public class Koning implements Karakter, Bonusable {
	
	private Speler speler = null;

	/*Eigenschappen van karakter Koning*/
	private final int nummer = 4;	
    private final int bouwLimiet = 1; 
    private final String naam = "Koning";
    private final Type type = Type.MONUMENT;
    private Object target;

    /**
	 * Overriden van de methode uit de interface Karakter,
	 * de Koning wordt aan de speler gekoppeld.
	 */
	@Override
	public void setSpeler(Speler speler) {
        this.speler = speler;
    }
	
	/**
	 * overriden van de methode uit de interface Karakter
	 *  en aanroepen van de methode beginBeurt
	 */
    @Override
    public void gebruikEigenschap() {
        // TODO: begint beurt
    }

    /*ontvangen bonusgoud voor monument gebouwen*/
    @Override
    public void ontvangenBonusGoud() {
        try {
            ArrayList<GebouwKaart> gebouwen = speler.getStad().getGebouwen();
            for(GebouwKaart gebouw: gebouwen) {
                if (gebouw.getType() == this.type) {
                    speler.getPortemonnee().ontvangenGoud(1);
                }
            }
        } catch (RemoteException re) {
            System.out.print(re);
        }
    }
    
    public int getNummer() {
        return nummer;
    }
    
    public int getBouwLimiet() {
        return bouwLimiet;
    }
    
    public String getNaam() {
        return naam;
    }

    public Type getType() {
        return type;
    }

    @Override
    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public Image getImage() {
        return null;
    }


}
