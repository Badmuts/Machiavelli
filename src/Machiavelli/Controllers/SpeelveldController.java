package Machiavelli.Controllers;

import Machiavelli.Models.Speelveld;
import Machiavelli.Models.Spel;
import Machiavelli.Views.SpeelveldView;

public class SpeelveldController {
	private Speelveld speelveld;
	private SpeelveldView speelveldview;
	
	public SpeelveldController(Spel spel) {
        this.speelveldview = new SpeelveldView(this, this.speelveld);
        this.speelveld = new Speelveld(spel, this.speelveldview);

		speelveldview.getExitButton().setOnAction(event -> System.exit(0));

		this.speelveldview.show();
	}
	
}
