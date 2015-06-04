package Models;

import java.util.ArrayList;

/**
 * Created by daanrosbergen on 03/06/15.
 */
public class Stad
{

	private ArrayList<GebouwKaart> gebouwen = new ArrayList<GebouwKaart>();
	private Spel spel;
	private short waardeStad;
	//private StadView stadView;

	public Stad(Spel spel)
	{
		this.spel = spel;
	}

	public ArrayList<GebouwKaart> getGebouwen()
	{
		return this.gebouwen;
	}

	public void addGebouw(GebouwKaart gebouw)
	{
		this.gebouwen.add(gebouw);
	}

	public void removeGebouw(GebouwKaart gebouw)
	{
		this.gebouwen.remove(gebouw);
		// Plaats gebouwkaart terug op gebouwenstapel (GebouwFactory)
		this.spel.getGebouwFactory().addGebouw(gebouw);
		// Update StadView
		// this.stadView.modelChanged();
	}
	
	public short getWaardeStad()
	{
		berekenWaarde(gebouwen);
		return this.waardeStad;
	}
	
	private void berekenWaarde(ArrayList<GebouwKaart> lijst)
	{
		short waarde = 0;
		for(int i = 0; i < lijst.size(); i++)
		{
			waarde += lijst.get(i).getKosten();
		}
		this.waardeStad = waarde;
	}

}
