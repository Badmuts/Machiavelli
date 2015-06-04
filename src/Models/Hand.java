package Models;

import java.util.*;

/**
 * 
 * @author Sander
 *
 */
public class Hand
{
	// Variables
	private List<GebouwKaart> kaartenLijst;
	private Speler speler;
	
	public Hand(Speler speler)
	{
		this.speler = speler;
		
		for(int i = 0; i < 5; i ++)
		{
			this.speler.getSpel().getGebouwFactory().trekKaart();
		}
	}
	
	public void addGebouw(GebouwKaart kaart)
	{
		
		
	}
	
	public void getGebouwen()
	{
		
	}
	
	public void removeGebouw()
	{
		
	}
}
