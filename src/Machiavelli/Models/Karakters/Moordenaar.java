
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
 * De speler heeft het karakter Magier gekozen. 
 * De eigenschappen van dit karakter worden gebruikt
 * door de speler tijdens de duur van een ronde.
 * 
 * De Moordenaar kan als karaktereigenschap een ander
 * karakter vermoorden. Het vermoorde karakter speelt
 * deze ronde niet mee. 
 */
public class Moordenaar extends UnicastRemoteObject implements Karakter, Serializable {
	private SpelerRemote speler = null;
	private Object target;
    
	/** Eigenschappen van karakter Moordenaar. */
    private final int nummer = 1;	
    private final int bouwLimiet = 1; 
    private final String naam = "Moordenaar";
    private final Type type = Type.NORMAAL;
    
    private final String image = "Machiavelli/Resources/Karakterkaarten/Portrait-Moordenaar.png";
    private ArrayList<KarakterObserver> observers = new ArrayList<>();

    public Moordenaar() throws RemoteException {
//      super(1099);
	}
    
    /**
     * Overriden van de methode uit de interface Karakter,
     * de Moordenaar wordt aan de speler gekoppeld.
     */
    @Override
    public void setSpeler(SpelerRemote speler) throws RemoteException {
    	this.speler = speler;
    }
   
    /**
	 * De speler selecteert een karakter, deze wordt als target geset.
	 * Vervolgens wordt de gebruikEigenschap methode aangroepen om dit karakter te vermoorden.
	 * 
	 * @param target dit is het geselecteerde karakter
	 * @throws RemoteException
	 */
    @Override
    public void setTarget(Object target) throws RemoteException {
    	this.target = (Karakter) target;
    	gebruikEigenschap();
    }
    
    /**
	 * overriden van de methode uit de interface Karakter
	 * en aanroepen van de methode selectKarakterView
	 * Er wordt gewacht op de keuze van de speler. 
	 * Vervolgens wordt het het gekozen karakter vermoord.
	 * 
	 * @return true eigenschap is gebruikt
	 * @throws RemoteException
	 */
    @Override
    public boolean gebruikEigenschap() throws RemoteException {
        // TODO: vermoord karakter
    	if (target != null) {
    		vermoordKarakter((Karakter) this.getTarget());
    		this.speler.setEigenschapGebruikt(true);
    		target = null; //target wordt gereset
    		return true;
    	} else {
    		return false;
    	}
    }
    
    /**
   	 *  De moordenaar vermoord het geselecteerde karakter.
   	 *  Deze slaat een een beurt over.
   	 *  
   	 *  @param object dit is het geselecteerde karakter
   	 *  @throws RemoteException
   	 */
    public void vermoordKarakter(Karakter target) throws RemoteException {
    	//TODO: Het target slaat zijn beurt over.
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
	public Object getTarget() throws RemoteException {
		return this.target;
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



