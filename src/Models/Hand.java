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
	private ArrayList<GebouwKaart> kaartenLijst;
	private Speler speler;
	
	public Hand(Speler speler)
	{
		this.speler = speler;
		
		kaartenLijst = new ArrayList<GebouwKaart>();
		for(int i = 0; i < 4; i ++)
		{
			kaartenLijst.add(this.speler.getSpel().getGebouwFactory().trekKaart());
		}
	}
	
	public void addGebouw(GebouwKaart kaart)
	{
		kaartenLijst.add(kaart);
	}

	public void removeGebouw(GebouwKaart gebouw)
	{
		this.kaartenLijst.remove(gebouw);
	}

	public ArrayList<GebouwKaart> getKaartenLijst()
	{
		return this.kaartenLijst;
	}
}
