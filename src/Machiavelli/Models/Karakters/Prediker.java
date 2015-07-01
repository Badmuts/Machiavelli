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
    private boolean isBonusable = true;
	private SpelerRemote speler = null;

	/** Eigenschappen van karakter Prediker. */
    private final int nummer = 5;	
    private final int bouwLimiet = 1; 
    private final String naam = "Prediker";
    private final Type type = Type.KERKELIJK;
    
    private final String image = "Machiavelli/Resources/Karakterkaarten/Portrait-Prediker.png";
    private ArrayList<KarakterObserver> observers = new ArrayList<>();

    public Prediker() throws RemoteException {
      //  super(1099);
	}
    
    @Override
    public void setSpeler(SpelerRemote speler) throws RemoteException {
        this.speler = speler;
    }

    @Override
    public boolean gebruikEigenschap() throws RemoteException {
        return true;
    }
    
    /**
   	 * De Prediker ontvangt 1 goudstuk per kerk gebouw in zijn stad als de knop Bonusgoud
   	 * wordt ingedrukt. isBonusable wordt op false gezet, zodat deze methode maar 1 keer per beurt aangeroepen kan worden.
   	 * 
   	 * @throws RemoteException
   	 */
    @Override
    public void ontvangenBonusGoud() throws RemoteException {
        if (isBonusable) {
            ArrayList<GebouwKaartRemote> gebouwen = speler.getStad().getGebouwen();
            for (GebouwKaartRemote gebouw : gebouwen) {
                if (gebouw.getType() == this.type)
                    speler.getPortemonnee().ontvangenGoud(1);
            }
            this.isBonusable = false;
            notifyObservers();
        }
    }

    @Override
    public SpelerRemote getSpeler() throws RemoteException {
        return speler;
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
    }

    @Override
    public String getImage() throws RemoteException {
        return this.image;
    }
    
    @Override
	public Object getTarget() throws RemoteException {
		return null;
	}

    @Override
    public boolean isBonusable() throws RemoteException {
        return isBonusable;
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
}
