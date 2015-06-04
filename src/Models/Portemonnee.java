package Models;
/**
 * 
 * @author Sander de Jong
 *
 */
public class Portemonnee
{
	// Variables
	private short goudMunten;
	
	public Portemonnee()
	{
		this.goudMunten = 2;
	}
	
	public void bestedenGoud(Bank bank, short aantal)
	{
		bank.ontvangenGoud(aantal);
	}
	
	public void ontvangenGoud(short aantal)
	{
		goudMunten += aantal;
	}
}
