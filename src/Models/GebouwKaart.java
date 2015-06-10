package Models;

import Enumerations.Type;
import javafx.scene.image.Image;

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
public class GebouwKaart
{
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getKosten()
    {
        return this.kosten;
    }

    public void setKosten(int kosten) {
        this.kosten = kosten;
    }

    public Stad getStad() {
        return stad;
    }

    public void setStad(Stad stad) {
        this.stad = stad;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
