package Machiavelli.Controllers;

import Machiavelli.Models.Spelregels;
import Machiavelli.Views.SpelregelsView;

/**
 * @author Jamie Kalloe
 */

public class RaadplegenSpelregelsController {
	
	//Variables
	private SpelregelsView spelRegelsView;
	private Spelregels spelRegels;
	
	public RaadplegenSpelregelsController()
	{
		try {
			this.spelRegelsView = new SpelregelsView();
			this.spelRegelsView.getCloseButton().setOnAction(event -> this.spelRegelsView.close());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cmdWeergeefSpelregels() {
		this.spelRegelsView.show();
		//remove the lines under...!
	}
}
