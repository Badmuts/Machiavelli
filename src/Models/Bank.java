package Models;
/**
 * 
 * @author Sander de Jong
 *
 */
public class Bank
{
	// Variables
	private short goudMunten;
	
	public Bank()
	{
		this.goudMunten = 30;
	}
	
	public void ontvangenGoud(short aantal)
	{
		this.goudMunten += aantal;
	}
	
	public void gevenGoud(Portemonnee portemonnee, short aantal)
	{
		portemonnee.ontvangenGoud(aantal);
	}
}
