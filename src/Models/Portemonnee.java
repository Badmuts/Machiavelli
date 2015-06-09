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
	private Bank bank;
	
	public Portemonnee(Bank bank)
	{
		this.bank = bank;
		bank.gevenGoud(this, 2);
	}
	
	public void bestedenGoud(Bank bank, int aantal)
	{
		bank.ontvangenGoud(aantal);
		this.goudMunten -= aantal;
	}
	
	public void ontvangenGoud(int aantal)
	{
		goudMunten += this.bank.gevenGoud(	aantal);
	}

	public int getGoudMunten()
	{
		return this.goudMunten;
	}
	
	public void ontvangenBonusGoud(int aantal){
		goudMunten += aantal;
	}
}
