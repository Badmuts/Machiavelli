package Models;

import Enumerations.Type;
import javafx.scene.image.Image;

/**
 * 
 * @author Sander
 *
 */
public class GebouwKaart
{
	private int kosten;
	private String naam;
	private Type type;
	private Stad stad;
    private Image image;

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

    public void setKosten(int kosten) {
        this.kosten = kosten;
    }

    public void setStad(Stad stad) {
        this.stad = stad;
    }

    public Stad getStad() {
        return stad;
    }
    
    public int getKosten()
    {
    	return this.kosten;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
