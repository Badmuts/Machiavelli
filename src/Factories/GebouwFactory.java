package Factories;

import Models.GebouwKaart;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * @author Sander
 *
 */

public class GebouwFactory
{
	// Variables
	private ArrayList<GebouwKaart> lstStapel;
	
	public GebouwFactory()
	{
		lstStapel = new ArrayList<GebouwKaart>();
		schuddenKaarten(lstStapel);
	}
	
	private void vullenLijst(ArrayList<GebouwKaart> list)
	{
		
	}
	
	private void schuddenKaarten(ArrayList<GebouwKaart> list)
	{
		Collections.shuffle(list);
	}
	
	private GebouwKaart trekkenKaart()
	{
		return this.lstStapel.get(0);
	}
	
}
