package Controllers;

import java.io.IOException;

import Models.Spelregels;
import Views.SpelregelsView;

/**
 * @author Jamie Kalloe
 */

public class RaadplegenSpelregelsController 
{
	//Variables
	private SpelregelsView spelRegelsView;
	private Spelregels spelRegels;
	
	public void cmdWeergeefSpelregels()
	{
		try {
			this.spelRegelsView = new SpelregelsView();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.spelRegelsView.getStage().show();
	}
}
