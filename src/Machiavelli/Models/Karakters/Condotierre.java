package Machiavelli.Models.Karakters;

import Machiavelli.Enumerations.Type;
import Machiavelli.Interfaces.Bonusable;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Observers.KarakterObserver;
import Machiavelli.Interfaces.Remotes.GebouwKaartRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Interfaces.Remotes.StadRemote;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/** 
 * Created by daanrosbergen on 03/06/15.
 * Edited by Bernd Oostrum
 * 
 * De speler heeft het karakter Condotierre gekozen. 
 * De eigenschappen van dit karakter worden gebruikt
 * door de speler tijdens de duur van een ronde.
 * 
 * De Condotierre kan een gebouw uit een stad van een 
 * andere speler vernietigen. Ook ontvangt hij 1 goudstuk
 * voor elk militair gebouw in zijn stad.
 */
public class Condotierre extends UnicastRemoteObject implements Karakter, Bonusable, Serializable {
    private boolean isBonusable = true;
	private GebouwKaartRemote target = null;
	private SpelerRemote speler = null;
	
	/** Eigenschappen van karakter Condotierre */
	private final int nummer = 8;	
	private final int bouwLimiet = 1; 
	private final String naam = "Condotierre";
    private final Type type = Type.MILITAIR;
    
    private final String image = "Machiavelli/Resources/Karakterkaarten/Portrait-Condotierre.png";
    private ArrayList<KarakterObserver> observers = new ArrayList<>();

    public Condotierre() throws RemoteException {
//    	super(1099);
	}
    
    /**
	 * Overriden van de methode uit de interface Karakter,
	 * de Condotierre wordt aan de speler gekoppeld.
	 * 
	 * @throws RemoteException
	 */
    @Override
    public void setSpeler(SpelerRemote speler) throws RemoteException {
        this.speler = speler;
    }

    /**
	 * De speler selecteert een gebouw in een stad, deze wordt als target geset.
	 * Vervolgens wordt de gebruikEigenschap methode aangroepen om dit gebouw te verwijderen.
	 * 
	 * @throws RemoteException
	 */
    @Override
    public void setTarget(Object target) throws RemoteException {
    	this.target = (GebouwKaartRemote) target;
    	gebruikEigenschap();
    }

    /**
	 * De Condotierre selecteert eerst een gebouwkaart op het speelveld.
	 * Daarna kan pas deze methode worden uitgevoerd. De geselecteerde 
	 * gebouwkaart wordt uit de stad verwijderd. De gebruikEigenschap methode
	 * kan per beurt maar een keer worden aangeroepen. Als de gebouwkaart in de
	 * stad van de Prediker staat, kan deze niet worden verwijderd. 
	 * 
	 * @return true eigenschap is gebruikt.
	 * @throws RemoteException
	 */
    @Override
    public boolean gebruikEigenschap() throws RemoteException {	
    	System.out.println(target == null);
    	boolean gebruikEigenschap;
		 
			if (this.target == null) {
				gebruikEigenschap = false;	
			}
			else {
				if (target.getStad().getSpeler().getKarakter().getNummer() != 5 && target.getStad().getSpeler().getKarakter().getNummer() != 8) {
					boolean kanVernietigen = vernietigGebouw(this.target.getStad(), getTarget());
					if(!kanVernietigen) {
						gebruikEigenschap = false;
					} else {
						gebruikEigenschap = true;
					}
					this.speler.setEigenschapGebruikt(true);
				}
				else {
					gebruikEigenschap = false;
				}
			}
		return gebruikEigenschap;
    }
    
    /**
   	 * De kosten voor het vernietigen gebouw worden uit de portemonnee gehaald en op de bank gezet.
   	 * De gebouwkaart wordt teruggeplaatst in de GebouwFactory. 
   	 * 
   	 * @param stad
   	 * @param target in het geval van de Condotierre is de target een gebouwkaart
   	 * @throws RemoteException
  	 */
    private boolean vernietigGebouw(StadRemote stad, GebouwKaartRemote target) throws RemoteException {
    	System.out.println("het target is " +  target);
        boolean genoegGoud = false;
        if (speler.getPortemonnee().getGoudMunten() >= target.getKosten()-1) {
            target.getStad().removeGebouw(target);
            genoegGoud = true;
        }
    	return genoegGoud;
    }
    
    /**
   	 * De Condotierre ontvangt 1 goudstuk per militair gebouw in zijn stad als de knop Bonusgoud
   	 * wordt ingedrukt. isBonusable wordt op false gezet, zodat deze methode maar 1 keer per beurt aangeroepen kan worden.
   	 * 
   	 * @throws RemoteException
   	 */
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
    public SpelerRemote getSpeler() throws RemoteException {
        return speler;
    }
    
    @Override
    public int getBouwLimiet() throws RemoteException {
        return this.bouwLimiet;
    }
    
    @Override
    public GebouwKaartRemote getTarget() throws RemoteException {
    	return target;
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
	public Type getType() throws RemoteException {
		return this.type;
	}
    
    @Override
    public String getImage() throws RemoteException {
        return this.image;
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
