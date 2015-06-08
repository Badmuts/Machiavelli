package Controllers;

import java.io.IOException;

import Models.Spelregels;
import Views.SpelregelsView;

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
			this.spelRegelsView.getCloseButton().setOnAction(event -> this.spelRegelsView.getStage().close());
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void cmdWeergeefSpelregels() {
		this.spelRegelsView.getStage().show();
	}
}
