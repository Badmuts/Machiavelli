package Machiavelli.Models.Karakters;

import Machiavelli.Enumerations.Type;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Observers.KarakterObserver;
import Machiavelli.Models.Speler;
import javafx.scene.image.Image;

import java.rmi.RemoteException;
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
public class Bouwmeester implements Karakter {
	
	@SuppressWarnings("unused")
	private Speler speler = null;
	
	/** Eigenschappen van karakter Bouwmeester */
    private final int nummer = 7;	
    private final int bouwLimiet = 3; 
    private final String naam = "Bouwmeester";
    private final Type type = Type.NORMAAL;
    
    private Image image = new Image("Machiavelli/Resources/Karakterkaarten/Portrait-Bouwmeester.png");
    private ArrayList<KarakterObserver> observers = new ArrayList<>();

    /**
	 * Overriden van de methode uit de interface Karakter,
	 * de Bouwmeester wordt aan de speler gekoppeld.
	 */
    @Override
    public void setSpeler(Speler speler) throws RemoteException {
        this.speler = speler;
    }

    @Override
    public Speler getSpeler() throws RemoteException {
        return speler;
    }

    /**
	 * overriden van de methode uit de interface Karakter.
	 * Er worden 2 gebouwkaarten uit de Gebouwfactory in de hand van de
	 * speler met het karakter bouwmeester geplaatst.
     * 
	 */
    @Override
    public void gebruikEigenschap() throws RemoteException {
        //TODO: 2 of 3 kaarten plaatsen in stad
    	try {
			this.speler.getHand().addGebouwen(this.speler.trekkenKaart(2));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public void setTarget(Object target) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

    @Override
    public Image getImage() throws RemoteException {
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
    public void beurtOverslaan() throws RemoteException {

    }
}
