package Models;
/**
 * 
 * @author Sander de Jong
 *
 */
public class Portemonnee
{
	// Variables
	private int goudMunten;
	
	public Portemonnee()
	{
		// Dit goud moet uit de bank gehaald worden.
		ontvangenGoud(2);
	}
	
	public void bestedenGoud(Bank bank, int aantal)
	{
		bank.ontvangenGoud(aantal);
		this.goudMunten -= aantal;
	}
	
	public void ontvangenGoud(int aantal)
	{
		goudMunten += aantal;
	}

	public int getGoudMunten()
	{
		return this.goudMunten;
	}
}
