package Models;

import Interfaces.Karakter;

/**
 * 
 * @author Sander de Jong
 *
 */
public class Speler
{
	// Variables
	private Portemonnee portemonnee;
	private boolean isKoning;
	private Karakter karakter;
	private Hand hand;
	private Spel spel;
	private Stad stad;
	
	public Speler(Spel spel)
	{
		this.spel = spel;
		this.stad = new Stad(spel);
		portemonnee = new Portemonnee();
		// hand = new Hand();
	}
	
	public void getGoudVanBank(Bank bank, short aantal)
	{
		bank.gevenGoud(portemonnee, aantal);
	}
	
	public void setGoudOpBank(Portemonnee portemonnee, short aantal)
	{
		portemonnee.bestedenGoud(this.spel.getBank(), aantal);
	}
	
	public void setKaartInStad(GebouwKaart gebouw)
	{
		this.stad.addGebouw(gebouw);
	}
	
	public void trekkenKaart()
	{
		// NIET AF
		for (int i = 0; i < 2; i++)
		{
			this.spel.getGebouwFactory().trekKaart();
		}
	}
	
	public void setKarakter(Karakter karakter)
	{
		this.karakter = karakter;
	}
	
	public Karakter getKarakter()
	{
	    return this.karakter;
	}
	
	public Spel getSpel()
	{
		return this.spel;
	}
}
