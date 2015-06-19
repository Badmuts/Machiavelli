package Machiavelli.Models.Karakters;

import Machiavelli.Enumerations.Type;
import Machiavelli.Interfaces.Bonusable;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Observers.KarakterObserver;
import Machiavelli.Models.GebouwKaart;
import Machiavelli.Models.Speler;
import Machiavelli.Models.Stad;
import javafx.scene.image.Image;


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
public class Condotierre implements Karakter, Bonusable {

	private GebouwKaart target = null;
	private Speler speler = null;
	
	/** Eigenschappen van karakter Condotierre */
	private final int nummer = 8;	
	private final int bouwLimiet = 1; 
	private final String naam = "Condotierre";
    private final Type type = Type.MILITAIR;
    
    private Image image = new Image("Machiavelli/Resources/Karakterkaarten/Portrait-Condotierre.png");
    private ArrayList<KarakterObserver> observers = new ArrayList<>();

    /**
	 * Overriden van de methode uit de interface Karakter,
	 * de Condotierre wordt aan de speler gekoppeld.
	 */
    @Override
    public void setSpeler(Speler speler) throws RemoteException {
        this.speler = speler;
    }

    @Override
    public Speler getSpeler() throws RemoteException {
        return null;
    }

    @Override
    public void setTarget(Object target) throws RemoteException {
    	this.target = (GebouwKaart) target;
    	gebruikEigenschap();
    }

    @Override
    public Image getImage() throws RemoteException {
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
    private void vernietigGebouw(Stad stad, GebouwKaart target) {
    	
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
    public void ontvangenBonusGoud() throws RemoteException {
        ArrayList<GebouwKaart> gebouwen = speler.getStad().getGebouwen();
        for(GebouwKaart gebouw: gebouwen) {
            if (gebouw.getType() == this.type)
                speler.getPortemonnee().ontvangenGoud(1);
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
