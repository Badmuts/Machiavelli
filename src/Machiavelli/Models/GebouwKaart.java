package Machiavelli.Models;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.ListIterator;

import Machiavelli.Enumerations.Type;
import Machiavelli.Interfaces.Observers.GebouwKaartObserver;
import Machiavelli.Interfaces.Remotes.GebouwKaartRemote;
import Machiavelli.Interfaces.Remotes.StadRemote;

/**
 * @author Sander
 *
 *Dit is de blauwdruk voor een gebouwkaart. De gebouwkaarten worden door de spelers
 *gebruikt om het spel te winnen. De gebouwkaarten hebben een enumeration genaamd type die
 *aangeeft of een bepaald karakter extra bonusgoud ontvangt.
 */
public class GebouwKaart extends UnicastRemoteObject implements Serializable, GebouwKaartRemote {
    // Variables
    private int kosten;
    private String naam;
    private Type type;
    private StadRemote stad;
    private String image;
    private ArrayList<GebouwKaartObserver> observers = new ArrayList<GebouwKaartObserver>();

    // Een kaart wordt aangemaakt met de meegegeven waardes
    public GebouwKaart(int kosten, String naam, Type type, String image) throws RemoteException {
        // super(1099);
        this.kosten = kosten;
        this.naam = naam;
        this.type = type;
        this.image = image;
    }

    public Type getType() throws RemoteException {
        return type;
    }

    public void setType(Type type) throws RemoteException {
        this.type = type;
        notifyObservers();
    }

    public String getNaam() throws RemoteException {
        return naam;
    }

    public void setNaam(String naam) throws RemoteException {
        this.naam = naam;
        notifyObservers();
    }

    public int getKosten() throws RemoteException {
        return this.kosten;
    }

    public void setKosten(int kosten) throws RemoteException {
        this.kosten = kosten;
        notifyObservers();
    }

    public StadRemote getStad() throws RemoteException {
        return stad;
    }

    public void setStad(StadRemote stad) throws RemoteException {
        this.stad = stad;
        notifyObservers();
    }

    public String getImage() throws RemoteException {
        return image;
    }

    public void setImage(String image) throws RemoteException {
        this.image = image;
        notifyObservers();
    }

    public void addObserver(GebouwKaartObserver gebouwKaartObserver) throws RemoteException {
        observers.add(gebouwKaartObserver);
    }

    public void notifyObservers() throws RemoteException {
        for (GebouwKaartObserver observer : observers) {
            observer.modelChanged(this);
        }
    }

    public ArrayList<GebouwKaartObserver> getObservers() throws RemoteException {
        return this.observers;
    }

    public String toString() {
        try {
            return "Gebouwkaart: " + this.getNaam() + " Punten: " + this.getKosten();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Gebouwkaart Remote Excp";
    }

}
