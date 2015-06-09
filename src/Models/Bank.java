package Models;
/**
 * 
 * @author Sander de Jong
 *
 */
public class Bank
{
	// Variables
	private int goudMunten;
	
	public Bank()
	{
		this.goudMunten = 30;
	}
	
	public void ontvangenGoud(int aantal)
	{
		this.goudMunten += aantal;

	}
	
	public int gevenGoud(int aantal)
	{
		this.goudMunten -= aantal;
		return aantal;
	}

	public int getGoudMunten()
	{
		return this.goudMunten;
	}
}
