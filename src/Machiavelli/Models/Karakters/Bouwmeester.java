package Machiavelli.Models.Karakters;

import Machiavelli.Enumerations.Type;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Observers.KarakterObserver;
import Machiavelli.Interfaces.Remotes.SpelerRemote;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/** 
 * Created by daanrosbergen on 03/06/15.
 * Edit by Bernd Oostrum
 * 
 * De speler heeft het karakter Bouwmeester gekozen. 
 * De eigenschappen van dit karakter worden gebruikt
 * door de speler tijdens de duur van een ronde.
 * 
 * De Bouwmeester trekt 2 extra gebouwkaarten en mag
 * in zijn beurt 3 gebouwen bouwen.
 * 
 */
public class Bouwmeester extends UnicastRemoteObject implements Karakter, Serializable {
	
	public Bouwmeester() throws RemoteException {
	}

	private SpelerRemote speler = null;
	
	/** Eigenschappen van karakter Bouwmeester */
    private final int nummer = 7;	
    private final int bouwLimiet = 3; 
    private final String naam = "Bouwmeester";
    private final Type type = Type.NORMAAL;
    private final String image = "Machiavelli/Resources/Karakterkaarten/Portrait-Bouwmeester.png";

    private ArrayList<KarakterObserver> observers = new ArrayList<>();
    /**
	 * Overriden van de methode uit de interface Karakter,
	 * de Bouwmeester wordt aan de speler gekoppeld.
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
	 * overriden van de methode uit de interface Karakter.
	 * Er worden 2 gebouwkaarten uit de Gebouwfactory in de hand van de
	 * speler met het karakter bouwmeester geplaatst.
     * 
	 */
    @Override
    public boolean gebruikEigenschap() throws RemoteException {
        //TODO: 2 of 3 kaarten plaatsen in stad
    	try {
			this.speler.getHand().addGebouwen(this.speler.trekkenKaart(2));
			this.speler.setEigenschapGebruikt();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return false;
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
		// TODO Auto-generated method stub
		
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
