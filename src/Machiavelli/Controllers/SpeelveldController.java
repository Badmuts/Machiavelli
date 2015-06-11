package Machiavelli.Controllers;

import java.rmi.RemoteException;

import Machiavelli.Models.Speelveld;
import Machiavelli.Views.KiesInkomstenView;
import Machiavelli.Views.SpeelveldView;

public class SpeelveldController {
	private Speelveld speelveld;
	private SpeelveldView speelveldview;
	private KiesInkomstenView inkomstenView;
	
	public SpeelveldController(Speelveld speelveld) throws RemoteException {
		this.speelveld = speelveld;
		this.speelveldview = new SpeelveldView(this, this.speelveld);
		
		speelveldview.getExitButton().setOnAction(event -> System.exit(0));
		
		this.inkomstenView = new KiesInkomstenView();
		this.speelveldview.getKiesInkomstenButton().setOnAction(event -> inkomstenView.show());
		
		this.speelveldview.show();
	}
	
}
