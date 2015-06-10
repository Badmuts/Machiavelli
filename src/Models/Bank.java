package Models;
/**
 * Dit is de bank van het spel. Per spel is er één bank aanwezig die het geld beheerd.
 * Spelers kunnen via de portemonnee geld van de bank halen en geld aan de bank geven.
 *
 * @author Sander de Jong
 * @version 0.1
 *
 */
public class Bank
{
	// Variables
	private int goudMunten;

	// De bank begint met 30 goudmunten
	public Bank() {
		this.goudMunten = 30;
	}

	// De bank ontvangt een x aantal goud
	public void ontvangenGoud(int aantal) {
		this.goudMunten += aantal;
	}

	// De bank geeft een x aantal goud en haalt het van het totaal af
	public int gevenGoud(int aantal) {
		this.goudMunten -= aantal;
		return aantal;
	}

	public int getGoudMunten() {
		return this.goudMunten;
	}
}
