package Machiavelli.Models.Karakters;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Machiavelli.Enumerations.Type;
import Machiavelli.Interfaces.Bonusable;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Observers.KarakterObserver;
import Machiavelli.Interfaces.Remotes.GebouwKaartRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Models.Speler;

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
public class Koopman extends UnicastRemoteObject implements Karakter, Bonusable, Serializable {
	
	public Koopman() throws RemoteException {
    }
	
	private SpelerRemote speler = null;
	
	/** Eigenschappen van karakter Koopman. */
    private final int nummer = 6;	
    private final int bouwLimiet = 1; 
    private final String naam = "Koopman";
    private final Type type = Type.COMMERCIEL;
    private final String image = "Machiavelli/Resources/Karakterkaarten/Portrait-Koopman.png";

	private Object target;
    private ArrayList<KarakterObserver> observers = new ArrayList<>();

    /**
	 * Overriden van de methode uit de interface Karakter,
	 * de Koopman wordt aan de speler gekoppeld.
	 */
	@Override
	public void setSpeler(SpelerRemote speler) throws RemoteException {
        this.speler = speler;
    }

    @Override
    public SpelerRemote getSpeler() throws RemoteException {
        return this.speler;
    }

	/**
	 * overriden van de methode uit de interface Karakter
	 * en aanroepen van de methode ontvangenBonusGoud
	 */
	@Override
    public boolean gebruikEigenschap() throws RemoteException {
		try {
            ontvangenBonusGoud();
            this.speler.setEigenschapGebruikt(true);
        } catch (RemoteException re) {
            System.out.print(re);
        }
        return false;
    }

	/** ontvangen bonusgoud voor commerciele gebouwen */
    @Override
    public void ontvangenBonusGoud() throws RemoteException {
        ArrayList<GebouwKaartRemote> gebouwen = speler.getStad().getGebouwen();
        for (GebouwKaartRemote gebouw : gebouwen) {
            if (gebouw.getType() == this.type) {
                speler.getPortemonnee().ontvangenGoud(1);
            }
        }
    }
    
    @Override
    public String getNaam() throws RemoteException {
    	return this.naam;
    }
   
    @Override
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
    public String getImage() throws RemoteException {
        return this.image;
    }

    @Override
    public void addObserver(KarakterObserver observer) throws RemoteException {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() throws RemoteException {
        for (KarakterObserver observer: observers) {
            observer.modelChanged(this);
        }
    }

    /**
	 * Deze methode wordt aangroepen door gebruikEigenschap()
	 * de speler met het karakter koopman ontvangt 1 goudstuk
	 */
    public void ontvangenBonusGoud(Speler koopman) throws RemoteException {
    	koopman.getPortemonnee().ontvangenGoud(1);
    }

	@Override
	public Object getTarget() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
