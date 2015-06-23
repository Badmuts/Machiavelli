package Machiavelli.Models.Karakters;

import Machiavelli.Enumerations.Type;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Observers.KarakterObserver;
import Machiavelli.Interfaces.Remotes.HandRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Models.GebouwKaart;
import Machiavelli.Models.Speler;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by daanrosbergen on 03/06/15.
 * Edited by Sander de Jong on 05/06/15.
 * Edit by Bernd Oostrum
 * 
 * De speler heeft het karakter Magier gekozen. 
 * De eigenschappen van dit karakter worden gebruikt
 * door de speler tijdens de duur van een ronde.
 * 
 * De magier heeft als eigenschap dat hij al zijn 
 * handkaarten (ook als dit 0 is) mag ruilen met een
 * andere speler of naar keuze een aantal kaarten in de 
 * factory terugstoppen en dezelfde hoeveelheid weer trekken
 */


public class Magier implements Karakter, Serializable {

    // Variables
    private final String naam = "Magier";
    private final int nummer = 3;
    private final int bouwLimiet = 1;
    private final Type type = Type.NORMAAL;

    private SpelerRemote speler  = null;
    private Object  target  = null;
    private ArrayList<GebouwKaart> ruilLijst = new ArrayList<GebouwKaart>();
    private final String image = "Machiavelli/Resources/Karakterkaarten/Portrait-Magier.png";
    private ArrayList<KarakterObserver> observers = new ArrayList<>();

    /**
	 * Overriden van de methode uit de interface Karakter,
	 * de Magier wordt aan de speler gekoppeld.
	 */
	@Override
	public void setSpeler(SpelerRemote speler) throws RemoteException {
        this.speler = speler;
    }

    @Override
    public SpelerRemote getSpeler() throws RemoteException {
        return null;
    }

    /**
     *Ruil kaarten met de stapel of met een andere speler.
     *
     */
    
    @Override
    public void gebruikEigenschap() throws RemoteException {
        // TODO: ruilen bouwkaarten
        System.out.println("faka");
        try {
            while (getTarget() != null) {
                // Iets tonen/afvangen om target te setten (View aanpassen?)
                if (getTarget().equals(speler.getSpel().getGebouwFactory())) {
                    // Als het target de stapel met gebouwkaarten is
                    // ruilen met stapelkaarten implementeren
                    // Afvangen ruil lijst
                    ruilMetStapel(speler.getHand(), ruilLijst);
                    break;
                }
                else {
                    // Ruil kaarten met een speler.
                    ruilMetKarakter((Speler)getTarget(), this.speler);
                    break;
                }
            }
        } catch (RemoteException re) {
            System.out.print(re);
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
    
 // Ruil alle bouwkaarten met alle bouwkaarten van een ander speler/karakter??
    private void ruilMetKarakter(SpelerRemote target, SpelerRemote magier) throws RemoteException {
        ArrayList<GebouwKaart> handTarget = target.getHand().getKaartenLijst();
        ArrayList<GebouwKaart> magierHand = magier.getHand().getKaartenLijst();
        target.getHand().setKaartenLijst(magierHand);
        magier.getHand().setKaartenLijst(handTarget);
    }

    // Leg een x aantal kaarten af op de stapel en pak een gelijk aantal nieuwe kaarten
    private void ruilMetStapel(HandRemote hand, ArrayList<GebouwKaart> ruilLijst) throws RemoteException {
        // Afleggen en tellen gebouwkaarten.
        int count = 0;
        for (int i = 0; i < ruilLijst.size(); i++) {
            hand.removeGebouw(ruilLijst.get(i));
            count ++;
        }

        // Trek nieuwe kaarten. Misschien functie maken die een lijst van gebouwen aan hand kan toevoegen?
        ArrayList<GebouwKaart> tempList = hand.getSpeler().trekkenKaart(count);
        for (int i = 0; i < tempList.size(); i++) {
            hand.addGebouw(tempList.get(i));
        }
    }

    public void beurtOverslaan() throws RemoteException {}

    public Object getTarget() throws RemoteException {
        return target;
    }

    public void setRuilLijst(ArrayList<GebouwKaart> ruilLijst) throws RemoteException {
        this.ruilLijst = ruilLijst;
    }
}
