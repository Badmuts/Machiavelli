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
		this.goudMunten = 2;
	}
	
	public void bestedenGoud(Bank bank, int aantal)
	{
		bank.ontvangenGoud(aantal);
	}
	
	public void ontvangenGoud(int aantal)
	{
		goudMunten += aantal;
	}
}
