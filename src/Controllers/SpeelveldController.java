package Controllers;

import javafx.stage.Stage;
import Models.Speelveld;
import Views.SpeelveldView;

public class SpeelveldController {
	private Speelveld speelveld;
	private SpeelveldView speelveldview;
	
	public SpeelveldController(Speelveld speelveld){
		this.speelveld = speelveld;
		//this.speelveldview = new SpeelveldView(this, speelveld);
		
	}
	public void show(){
		speelveldview.show(speelveld.getSecondaryStage());
	}
	
}
