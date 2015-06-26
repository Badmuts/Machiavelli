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
 * Edited by Sander de Jong on 08/06/15.
 * Edited by Bernd Oostrum
 * 
 * De speler heeft het karakter Prediker gekozen. 
 * De eigenschappen van dit karakter worden gebruikt
 * door de speler tijdens de duur van een ronde.
 * 
 * Uit de stad van de prediker kunnen geen gebouwen worden
 * verwijderd.
 */
public class Prediker extends UnicastRemoteObject implements Karakter, Bonusable, Serializable {
	
	public Prediker() throws RemoteException {
	}

	private SpelerRemote speler = null;
	private ArrayList<KarakterObserver> observers = new ArrayList<>();

	/** Eigenschappen van karakter Prediker. */
    private final int nummer = 5;	
    private final int bouwLimiet = 1; 
    private final String naam = "Prediker";
    private final Type type = Type.KERKELIJK;
    @SuppressWarnings("unused")
	private Object target;
    
    private final String image = "Machiavelli/Resources/Karakterkaarten/Portrait-Prediker.png";
   

    @Override
    public void setSpeler(SpelerRemote speler) throws RemoteException {
        this.speler = speler;
    }

    @Override
    public SpelerRemote getSpeler() throws RemoteException {
        return speler;
    }

    @Override
    public boolean gebruikEigenschap() throws RemoteException {
        return true;
    }
    
    /** ontvangen bonusgoud voor Kerk gebouwen */
    @Override
    public void ontvangenBonusGoud() throws RemoteException {
        ArrayList<GebouwKaartRemote> gebouwen = speler.getStad().getGebouwen();
        for(GebouwKaartRemote gebouw: gebouwen) {
            if (gebouw.getType() == this.type)
                speler.getPortemonnee().ontvangenGoud(1);
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

    @Override
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

	@Override
	public Object getTarget() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}
