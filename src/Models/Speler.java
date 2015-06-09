package Models;

import Interfaces.Karakter;

import java.util.ArrayList;

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
		this.portemonnee = new Portemonnee(this.spel.getBank());
		this.hand = new Hand(this);
	}
	
	public void getGoudVanBank(Bank bank, int aantal)
	{
		bank.gevenGoud(aantal);
	}
	
	public void setGoudOpBank(Portemonnee portemonnee, int aantal)
	{
		portemonnee.bestedenGoud(this.spel.getBank(), aantal);
	}
	
	public void setKaartInStad(GebouwKaart gebouw)
	{
		this.stad.addGebouw(gebouw);
		this.hand.removeGebouw(gebouw);
	}
	
	public ArrayList<GebouwKaart> trekkenKaart()
	{
		ArrayList<GebouwKaart> tempList = new ArrayList<GebouwKaart>();
		for (int i = 0; i < 2; i++)
		{
			tempList.add(this.spel.getGebouwFactory().trekKaart());
		}
		return tempList;
	}

	// Voor magier eigenschap, die hoeft niet te kiezen. Kan ook aparte method worden..
	public ArrayList<GebouwKaart> trekkenKaart(int aantal)
	{
		ArrayList<GebouwKaart>tempList = new ArrayList<GebouwKaart>();
		for (int i = 0; i < aantal; i++)
		{
			tempList.add(this.spel.getGebouwFactory().trekKaart());
		}
		return tempList;
	}

	public void selecterenKaart(ArrayList<GebouwKaart> lijst, int index)
	{
		this.getHand().addGebouw(lijst.get(index));
		lijst.remove(index);
		this.getSpel().getGebouwFactory().addGebouw(lijst.get(0));
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

	public Portemonnee getPortemonnee()
	{
		return this.portemonnee;
	}

	public Hand getHand()
	{
		return this.hand;
	}

	public Stad getStad()
	{
		return this.stad;
	}
}
