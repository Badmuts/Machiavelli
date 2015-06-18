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
 * De speler heeft het karakter Koopman gekozen. 
 * De eigenschappen van dit karakter worden gebruikt
 * door de speler tijdens de duur van een ronde.
 * 
 * De Koopman ontvangt 1 goudstuk bij het gebruik van  
 * zijn eigenschap en ontvangt 1 goudstuk voor elk 
 * commericiel gebouw in zijn stad.
 */
public class Koopman implements Karakter, Bonusable {
	
	private Speler speler = null; 
	
	/** Eigenschappen van karakter Koopman. */
    private final int nummer = 6;	
    private final int bouwLimiet = 1; 
    private final String naam = "Koopman";
    private final Type type = Type.COMMERCIEL;
    private Object target;
    
    private Image image = new Image("Machiavelli/Resources/Karakterkaarten/Portrait-Koopman.png");

    /**
	 * Overriden van de methode uit de interface Karakter,
	 * de Koopman wordt aan de speler gekoppeld.
	 */
	@Override
	public void setSpeler(Speler speler) throws RemoteException {
        this.speler = speler;
    }

    @Override
    public Speler getSpeler() throws RemoteException {
        return null;
    }

    // TODO: ontvangt 1 goudstuk
	/**
	 * overriden van de methode uit de interface Karakter
	 * en aanroepen van de methode ontvangenBonusGoud
	 */
	@Override
    public void gebruikEigenschap() throws RemoteException {
		try {
            ontvangenBonusGoud();
        } catch (RemoteException re) {
            System.out.print(re);
        }
    }
	
	/**
	 * Deze methode wordt aangroepen door gebruikEigenschap()
	 * de speler met het karakter koopman ontvangt 1 goudstuk
	 */
    public void ontvangenBonusGoud(Speler koopman) throws RemoteException {
    	koopman.getPortemonnee().ontvangenGoud(1);
    }

	/** ontvangen bonusgoud voor commerciele gebouwen */
    @Override
    public void ontvangenBonusGoud() throws RemoteException {
        ArrayList<GebouwKaart> gebouwen = speler.getStad().getGebouwen();
        for (GebouwKaart gebouw : gebouwen) {
            if (gebouw.getType() == this.type)
                speler.getPortemonnee().ontvangenGoud(1);
        }
    }
    
    public String getNaam() throws RemoteException {
    	return this.naam;
    }
   
    public int getNummer() throws RemoteException {
    	return this.nummer;
    }

    @Override
    public int getBouwLimiet() throws RemoteException {
        return this.bouwLimiet;
    }

	public Type getType() throws RemoteException {
		return this.type;
	}

    @Override
    public void setTarget(Object target) throws RemoteException {
        this.target = target;
    }

    @Override
    public Image getImage() throws RemoteException {
        return this.image;
    }

    @Override
    public void beurtOverslaan() throws RemoteException {

    }

}