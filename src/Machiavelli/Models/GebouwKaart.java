package Machiavelli.Models;

import Machiavelli.Enumerations.Type;
import Machiavelli.Interfaces.Remotes.GebouwKaartRemote;
import javafx.scene.image.Image;

import java.rmi.RemoteException;

/**
 * Dit is de blauwdruk voor een gebouwkaart. De gebouwkaarten worden
 * door de spelers gebruikt om het spel te winnen. De gebouwkaarten
 * hebben een enumeration genaamd type die aangeeft of een bepaald
 * karakter extra bonusgoud ontvangt.
 *
 * @author Sander
 * @version 0.1
 *
 */
public class GebouwKaart implements GebouwKaartRemote {
    // Variables
	private int kosten;
	private String naam;
	private Type type;
	private Stad stad;
    private Image image;

    // Een kaart wordt aangemaakt met de meegegeven waardes
    public GebouwKaart(int kosten, String naam, Type type, Image image) {
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
    }

    public String getNaam() throws RemoteException {
        return naam;
    }

    public void setNaam(String naam) throws RemoteException {
        this.naam = naam;
    }

    public int getKosten() throws RemoteException
    {
        return this.kosten;
    }

    public void setKosten(int kosten) throws RemoteException {
        this.kosten = kosten;
    }

    public Stad getStad() throws RemoteException {
        return stad;
    }

    public void setStad(Stad stad) throws RemoteException {
        this.stad = stad;
    }

    public Image getImage() throws RemoteException {
        return image;
    }

    public void setImage(Image image) throws RemoteException {
        this.image = image;
    }
}
