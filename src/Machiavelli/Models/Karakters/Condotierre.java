package Machiavelli.Models.Karakters;

import Machiavelli.Enumerations.Type;
import Machiavelli.Interfaces.Bonusable;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Observers.KarakterObserver;
import Machiavelli.Interfaces.Remotes.GebouwKaartRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Interfaces.Remotes.StadRemote;
import Machiavelli.Models.GebouwKaart;
import Machiavelli.Models.Stad;

import java.io.Serializable;
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
public class Condotierre implements Karakter, Bonusable, Serializable {

	private GebouwKaart target = null;
	private SpelerRemote speler = null;
	
	/** Eigenschappen van karakter Condotierre */
	private final int nummer = 8;	
	private final int bouwLimiet = 1; 
	private final String naam = "Condotierre";
    private final Type type = Type.MILITAIR;
    private final String image = "Machiavelli/Resources/Karakterkaarten/Portrait-Condotierre.png";
    private ArrayList<KarakterObserver> observers = new ArrayList<>();

    /**
	 * Overriden van de methode uit de interface Karakter,
	 * de Condotierre wordt aan de speler gekoppeld.
	 */
    @Override
    public void setSpeler(SpelerRemote speler) throws RemoteException {
        this.speler = speler;
    }

    @Override
    public SpelerRemote getSpeler() throws RemoteException {
        return null;
    }

    @Override
    public void setTarget(Object target) throws RemoteException {
    	this.target = (GebouwKaart) target;
    	gebruikEigenschap();
    }

    @Override
    public String getImage() throws RemoteException {
        return this.image;
    }
    
    /**
	 * overriden van de methode uit de interface Karakter
	 * en aanroepen van de methode selectGebouwView
	 * Er wordt gewacht op de keuze van de speler. 
	 * Vervolgens wordt het het gekozen gebouw verwijderd 
	 * uit de stad van de speler waarin dit gebouw gekozen is
	 */
    public void gebruikEigenschap() throws RemoteException {
    	try {
			if (target != null && target.getStad().getSpeler().getKarakter().getNaam() != "Prediker") {
					vernietigGebouw(this.target.getStad(), getTarget());
				}
			else {
				//TODO speelveldview aanroepen met klikbare gebouwen in steden
			}
					
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   	
    }
    
    //Verwijder gebouw uit stad van een andere speler en verwijder de kosten??
    private void vernietigGebouw(StadRemote stad, GebouwKaart target) {
    	
    	try {
    		speler.setGoudOpBank(speler.getPortemonnee(), target.getKosten()-1);
			target.getStad().removeGebouw(this.getTarget());
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /** ontvangen bonusgoud voor militaire gebouwen */
    @Override
    public void ontvangenBonusGoud() {
    	try
    	{
	        ArrayList<GebouwKaartRemote> gebouwen = speler.getStad().getGebouwen();
	        for(GebouwKaartRemote gebouw: gebouwen) {
	            if (gebouw.getType() == this.type)
	                speler.getPortemonnee().ontvangenGoud(1);
	        }
    	}
    	catch(RemoteException e)
    	{
    		e.printStackTrace();
    	}
    }
    /*
    TODO: implement registerSelectGebouwView
    public void registerSelectGebouwView(SelecteGebouwView selecteGebouwView) {
        this.selectGebouwView = selectGebouwView;
    } */
    
    @Override
    public int getBouwLimiet() throws RemoteException {
        return this.bouwLimiet;
    }
    
    public GebouwKaart getTarget() throws RemoteException {
    	return target;
    }
	
	public String getNaam() throws RemoteException {
    	return this.naam;
    }
   
    public int getNummer() throws RemoteException {
    	return this.nummer;
    }

	public Type getType() throws RemoteException {
		return this.type;
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
