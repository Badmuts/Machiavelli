package Machiavelli.Models.Karakters;

import Machiavelli.Enumerations.Type;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Remotes.GebouwKaartRemote;
import Machiavelli.Models.GebouwKaart;
import Machiavelli.Models.Hand;
import Machiavelli.Models.Speler;

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

public class Magier implements Karakter {

    // Variables
    private final String naam = "Magier";
    private final int nummer = 3;
    private final int bouwLimiet = 1;
    private final Type type = Type.NORMAAL;

    private Speler  speler  = null;
    private Object  target  = null;
    private ArrayList<GebouwKaart> ruilLijst = new ArrayList<GebouwKaart>();
    private final String image = "Machiavelli/Resources/Karakterkaarten/Portrait-Magier.png";
    private ArrayList<KarakterObserver> observers = new ArrayList<>();

    /**
	 * Overriden van de methode uit de interface Karakter,
	 * de Magier wordt aan de speler gekoppeld.
	 */
	@Override
	public void setSpeler(Speler speler) {
        this.speler = speler;
    }

    @Override
    public Speler getSpeler() {
        return null;
    }

    @Override
    public void gebruikEigenschap() {
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

    // Ruil alle bouwkaarten met alle bouwkaarten van een ander speler/karakter??
    private void ruilMetKarakter(Speler target, Speler magier) throws RemoteException {
        ArrayList<GebouwKaartRemote> handTarget = target.getHand().getKaartenLijst();
        ArrayList<GebouwKaartRemote> magierHand = magier.getHand().getKaartenLijst();
        target.getHand().setKaartenLijst(magierHand);
        magier.getHand().setKaartenLijst(handTarget);
    }

    // Leg een x aantal kaarten af op de stapel en pak een gelijk aantal nieuwe kaarten
    private void ruilMetStapel(Hand hand, ArrayList<GebouwKaart> ruilLijst) throws RemoteException {
        // Afleggen en tellen gebouwkaarten.
        int count = 0;
        for (int i = 0; i < ruilLijst.size(); i++) {
            hand.removeGebouw(ruilLijst.get(i));
            count ++;
        }

        // Trek nieuwe kaarten. Misschien functie maken die een lijst van gebouwen aan hand kan toevoegen?
        ArrayList<GebouwKaartRemote> tempList = hand.getSpeler().trekkenKaart(count);
        for (int i = 0; i < tempList.size(); i++) {
            hand.addGebouw(tempList.get(i));
        }
    }

    @Override
    public void beurtOverslaan() {

    }
}
