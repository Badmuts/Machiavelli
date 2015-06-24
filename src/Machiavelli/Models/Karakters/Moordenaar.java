
package Machiavelli.Models.Karakters;

import Machiavelli.Enumerations.Type;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Observers.KarakterObserver;
import Machiavelli.Interfaces.Remotes.SpelerRemote;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

/** 
 * Created by daanrosbergen on 03/06/15.
 * Edit by Bernd Oostrum
 * 
 * De speler heeft het karakter Magier gekozen. 
 * De eigenschappen van dit karakter worden gebruikt
 * door de speler tijdens de duur van een ronde.
 * 
 * De Moordenaar kan als karaktereigenschap een ander
 * karakter vermoorden. Het vermoorde karakter speelt
 * deze ronde niet mee. 
 */
public class Moordenaar implements Karakter, Serializable {
	
	private SpelerRemote speler = null;
    
	/** Eigenschappen van karakter Moordenaar. */
    private final int nummer = 1;	
    private final int bouwLimiet = 1; 
    private final String naam = "Moordenaar";
    private final Type type = Type.NORMAAL;
    private Object target;
    private final String image = "Machiavelli/Resources/Karakterkaarten/Portrait-Moordenaar.png";
    private ArrayList<KarakterObserver> observers = new ArrayList<>();

    /**
     * Overriden van de methode uit de interface Karakter,
     * de Moordenaar wordt aan de speler gekoppeld.
     */
    @Override
    public void setSpeler(SpelerRemote speler) throws RemoteException {
    	this.speler = speler;
    }
    @Override
    public SpelerRemote getSpeler() throws RemoteException {
    	return speler;
    }
    
    @Override
    public void setTarget(Object target) throws RemoteException {
    	this.target = target;
    }
    
    /**
	 * overriden van de methode uit de interface Karakter
	 * en een karakter vermoorden die vervolgens een beurt
	 * overslaat.
	 */
    
    @Override
    public boolean gebruikEigenschap() throws RemoteException {
        // TODO: vermoord karakter
    	if (target != null) {
    		vermoordKarakter(this.getVermoordKarakter());
    	}
    	else {
    		//TODO: view aanroepen
    	}
        return false;
    }
    
    //beurt overslaan met ifjes???

    public void vermoordKarakter(Karakter target) throws RemoteException {
    	//target.getSpeler()
    	//methode eindigenbeurt van de target aanroepen.
    	}
    
    public Karakter getVermoordKarakter() throws RemoteException {
		return (Karakter)target;
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
}



