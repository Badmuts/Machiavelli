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
 * Created by daanrosbergen on 03/06/15. Edit by Bernd Oostrum
 * 
 * De speler heeft het karakter Dief gekozen. De eigenschappen van dit karakter worden gebruikt door
 * de speler tijdens de duur van een ronde.
 * 
 * De dief kan als eigenschap een ander karakter bestelen van al zijn goudstukken. De karakters
 * moordenaar en het het slachtoffer van de moordenaar kunnen niet gekozen worden de dief ontvangt
 * pas de goudstukken als het bestolen karakter aan de beurt is
 */

public class Dief extends UnicastRemoteObject implements Karakter, Serializable {
    private SpelerRemote speler = null;
    private Object target = null;

    /** Eigenschappen van karakter Dief. */
    private final int nummer = 2;
    private final int bouwLimiet = 1;
    private final String naam = "Dief";
    private final Type type = Type.NORMAAL;

    private final String image = "Machiavelli/Resources/Karakterkaarten/Portrait-Dief.png";
    private ArrayList<KarakterObserver> observers = new ArrayList<>();

    public Dief() throws RemoteException {
        // super(1099);
    }

    /**
     * Overriden van de methode uit de interface Karakter, de Dief wordt aan de speler gekoppeld.
     * 
     * @throws RemoteException
     */
    @Override
    public void setSpeler(SpelerRemote speler) throws RemoteException {
        this.speler = speler;
    }

    /**
     * De speler selecteert een karakter, deze wordt als target geset. Vervolgens wordt de
     * gebruikEigenschap methode aangroepen om dit karakter te bestelen.
     * 
     * @param target dit het geselecteerde karakter
     * @throws RemoteException
     */
    @Override
    public void setTarget(Object target) throws RemoteException {
        this.target = target;
        gebruikEigenschap();
    }

    /**
     * overriden van de methode uit de interface Karakter en aanroepen van de methode
     * selectKarakterView Er wordt gewacht op de keuze van de speler. Vervolgens wordt het het
     * gekozen karakter bestolen van al zijn goudstukken op het moment dat deze aan de beurt is.
     * 
     * @return true eigenschap is gebruikt
     * @throws RemoteException
     */
    @Override
    public boolean gebruikEigenschap() throws RemoteException {
        if (target != null && speler.getKarakter().getNummer() != 1) {
            BesteelKarakter(this.speler, (SpelerRemote) getTarget());
            this.speler.setEigenschapGebruikt(true);
            target = null; // target wordt gereset
            return true;
        } else {
            return false;
        }
    }

    /**
     * De dief ontvangt al de goudstukken van het geselecteerde karakter. De goudstukken van het
     * bestolen karakter wordt op de bank gezet, vervolgens ontvangt de dief dit goud weer van de
     * bank.
     * 
     * @param speler dit is de dief
     * @param target dit is het geselecteerde karakter
     * @throws RemoteException
     */
    private void BesteelKarakter(SpelerRemote speler, SpelerRemote target) {
        try {
            System.out.println("naam speler: " + speler.getKarakter().getNaam()
                    + " | Goudstukken: " + speler.getPortemonnee().getGoudMunten());
            System.out.println("naam target: " + target.getKarakter().getNaam()
                    + " | Goudstukken: " + target.getPortemonnee().getGoudMunten());
            speler.getGoudVanBank(speler.getSpel().getBank(), target.getPortemonnee()
                    .getGoudMunten());
            target.setGoudOpBank(target.getPortemonnee(), target.getPortemonnee().getGoudMunten());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
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
        for (KarakterObserver observer : observers) {
            observer.modelChanged(this);
        }
    }
}
