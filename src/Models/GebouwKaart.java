package Models;

import Enumerations.Type;

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

    public GebouwKaart(int kosten, String naam, Type type) {
        this.kosten = kosten;
        this.naam = naam;
        this.type = type;
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

    public int getKosten() {

        return kosten;
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
}
