package Machiavelli.Models.Karakters;

import Machiavelli.Enumerations.Type;
import Machiavelli.Interfaces.Bonusable;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Observers.KarakterObserver;
import Machiavelli.Interfaces.Remotes.GebouwKaartRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
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
public class Koning extends UnicastRemoteObject implements Karakter, Bonusable, Serializable {

    private boolean isBonusable = true;

    public Koning() throws RemoteException {
        super(1099);
    }

	private SpelerRemote speler = null;

	/*Eigenschappen van karakter Koning*/
	private final int nummer = 4;	
    private final int bouwLimiet = 1; 
    private final String naam = "Koning";
    private final Type type = Type.MONUMENT;
    
    private final String image = "Machiavelli/Resources/Karakterkaarten/Portrait-Koning.png";
    private ArrayList<KarakterObserver> observers = new ArrayList<>();

    /**
	 * Overriden van de methode uit de interface Karakter,
	 * de Koning wordt aan de speler gekoppeld.
	 */
	@Override
	public void setSpeler(SpelerRemote speler) throws RemoteException {
        this.speler = speler;
    }

    @Override
    public SpelerRemote getSpeler() throws RemoteException {
        return speler;
    }

    /**
	 * overriden van de methode uit de interface Karakter
	 *  en aanroepen van de methode beginBeurt
	 */
    @Override
    public boolean gebruikEigenschap() throws RemoteException {
        // TODO: begint beurt
        return true;
    }

    /*ontvangen bonusgoud voor monument gebouwen*/
    @Override
    public void ontvangenBonusGoud() throws RemoteException {
        if (isBonusable) {
            ArrayList<GebouwKaartRemote> gebouwen = speler.getStad().getGebouwen();
            for (GebouwKaartRemote gebouw : gebouwen) {
                if (gebouw.getType() == this.type) {
                    speler.getPortemonnee().ontvangenGoud(1);
                }
            }
            this.isBonusable = false;
            notifyObservers();
        }
    }
    
    @Override
    public int getNummer() throws RemoteException {
        return nummer;
    }
    
    @Override
    public int getBouwLimiet() throws RemoteException {
        return bouwLimiet;
    }
    
    @Override
    public String getNaam() throws RemoteException {
        return naam;
    }

    @Override
    public Type getType() throws RemoteException {
        return type;
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

	@Override
	public void setTarget(Object target) throws RemoteException {
	}

	@Override
	public Object getTarget() throws RemoteException {
		return null;
	}

    @Override
    public boolean isBonusable() throws RemoteException {
        return isBonusable;
    }
}
