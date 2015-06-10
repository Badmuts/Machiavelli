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
			System.out.println(e);
		}
	}
	
	public void cmdWeergeefSpelregels() {
		this.spelRegelsView.show();
		//remove the lines under...!
		//de ic controller maakt de view, en weergeeft hem met de weergaafTrekkenKaartView method.
//		InkomstenController ic = new InkomstenController(new Speler(new Spel(new Stage())));
//		ic.weergeefTrekkenKaartView();
	}
}
