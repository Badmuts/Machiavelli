package Machiavelli.Models.Karakters;

import Machiavelli.Enumerations.Type;
import Machiavelli.Factories.GebouwFactory;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Models.GebouwKaart;
import Machiavelli.Models.Hand;
import Machiavelli.Models.Speler;
import javafx.scene.image.Image;

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
    private Image image = new Image("Machiavelli/Resources/Karakterkaarten/Portrait-Magier.png");

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

    // Ruil alle bouwkaarten met alle bouwkaarten van een ander speler/karakter??
    private void ruilMetKarakter(Speler target, Speler magier) throws RemoteException {
        ArrayList<GebouwKaart> handTarget = target.getHand().getKaartenLijst();
        ArrayList<GebouwKaart> magierHand = magier.getHand().getKaartenLijst();
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
        ArrayList<GebouwKaart> tempList = hand.getSpeler().trekkenKaart(count);;
        for (int i = 0; i < tempList.size(); i++) {
            hand.addGebouw(tempList.get(i));
        }
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(GebouwFactory target) {
        this.target = target;
    }

    public void setTarget(Speler target) {
        this.target = target;
    }

    public void setRuilLijst(ArrayList<GebouwKaart> ruilLijst) {
        this.ruilLijst = ruilLijst;
    }
    
    public String getNaam() {
    	return this.naam;
    }
   
    public int getNummer() {
    	return this.nummer;
    }

    @Override
    public int getBouwLimiet() {
        return this.bouwLimiet;
    }

    public Type getType() {
		return this.type;
	}

    @Override
    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public Image getImage() {
        return this.image;
    }

    @Override
    public void beurtOverslaan() {

    }
}
