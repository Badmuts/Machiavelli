package Models;
/**
 * De portemonnee beheerd het geld van de speler. Via de portemonnee
 * kan de speler aan andere spelers of de bank betalen. Ook ontvangt
 * de speler via de portemonnee goud.
 *
 * @author Sander de Jong
 * @version 0.1
 *
 */
public class Portemonnee
{
	// Variables
	private int goudMunten;
	private Bank bank;

	// Een portemonnee start met 2 goudmunten. Deze worden uit de bank gehaald
	public Portemonnee(Bank bank) {
		this.bank = bank;
		goudMunten += this.bank.gevenGoud(2);
	}

	// Goud aan de bank betalen
	public void bestedenGoud(Bank bank, int aantal) {
		bank.ontvangenGoud(aantal);
		this.goudMunten -= aantal;
	}

	// Ontvangen van een x aantal goud
	public void ontvangenGoud(int aantal) {
		goudMunten += this.bank.gevenGoud(aantal);
	}

	public int getGoudMunten() {
		return this.goudMunten;
	}
}