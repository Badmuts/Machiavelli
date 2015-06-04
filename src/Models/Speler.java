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
	
	public Speler(Spel spel)
	{
		this.spel = spel;
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
	
	public void setKaartInStad()
	{
		
	}
	
	public void trekkenKaart()
	{
		
	}
	
	public void setKarakter(Karakter karakter)
	{
		this.karakter = karakter;
	}
	
	public Karakter getKarakter()
	{
	    return this.karakter;
	}
}
