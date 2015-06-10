package Machiavelli.Controllers;

import Machiavelli.Models.Speelveld;
import Machiavelli.Views.SpeelveldView;

public class SpeelveldController {
	private Speelveld speelveld;
	private SpeelveldView speelveldview;
	
	public SpeelveldController(Speelveld speelveld) {
		this.speelveld = speelveld;
		this.speelveldview = new SpeelveldView(this, this.speelveld);
		
		speelveldview.getExitButton().setOnAction(event -> System.exit(0));
		this.speelveldview.show();
	}
	
}
