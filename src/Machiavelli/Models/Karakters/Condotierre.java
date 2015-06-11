package Machiavelli.Models.Karakters;

import Machiavelli.Enumerations.Type;
import Machiavelli.Interfaces.Bonusable;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Models.GebouwKaart;
import Machiavelli.Models.Speler;

import java.rmi.RemoteException;
import java.util.ArrayList;

/** 
 * Created by daanrosbergen on 03/06/15.
 * Editted by Bernd Oostrum
 * 
 * De speler heeft het karakter Condotierre gekozen. 
 * De eigenschappen van dit karakter worden gebruikt
 * door de speler tijdens de duur van een ronde.
 * 
 * De Condotierre kan een gebouw uit een stad van een 
 * andere speler vernietigen. Ook ontvangt hij 1 goudstuk
 * voor elk militair gebouw in zijn stad.
 */
public class Condotierre implements Karakter, Bonusable {

	private GebouwKaart vernietigGebouw = null;
	private Speler      speler          = null;
	
	/** Eigenschappen van karakter Condotierre */
	private final int nummer = 8;	
	private final int bouwLimiet = 1; 
	private final String naam = "Condotierre";

    @Override
    public int getBouwLimiet() {
        return this.bouwLimiet;
    }

    private final Type type = Type.MILITAIR;
    
    /**
	 * Overriden van de methode uit de interface Karakter,
	 * de Condotierre wordt aan de speler gekoppeld.
	 */
    @Override
    public void setSpeler(Speler speler) {
        this.speler = speler;
    }
    
    /**
	 * overriden van de methode uit de interface Karakter
	 * en aanroepen van de methode selectGebouwView
	 * Er wordt gewacht op de keuze van de speler. 
	 * Vervolgens wordt het het gekozen gebouw verwijderd 
	 * uit de stad van de speler waarin dit gebouw gekozen is
	 */
    public void gebruikEigenschap() {
        // TODO: sloopgebouw

        // this.selectGebouwView.start();
        // TODO: Iets van een listener? (voor gekozen kaart (SelectGebouwView))
        // TODO: Speler, remove gold (betaalGoud)
        // this.vernietigGebouw.getStad().removeGebouw(vernietigGebouw);
    }

    /** ontvangen bonusgoud voor militaire gebouwen */
    @Override
    public void ontvangenBonusGoud() throws RemoteException {
        ArrayList<GebouwKaart> gebouwen = speler.getStad().getGebouwen();
        for(GebouwKaart gebouw: gebouwen) {
            if (gebouw.getType() == this.type)
                speler.getPortemonnee().ontvangenGoud(1);
        }
    }

    /*
    TODO: implement registerSelectGebouwView
    public void registerSelectGebouwView(SelecteGebouwView selecteGebouwView) {
        this.selectGebouwView = selectGebouwView;
    } */

    public GebouwKaart getVernietigGebouw() {
		return vernietigGebouw;
	}

	public void setVernietigGebouw(GebouwKaart vernietigGebouw) {
		this.vernietigGebouw = vernietigGebouw;
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
