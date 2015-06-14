package Machiavelli.Controllers;

import Machiavelli.Interfaces.Observers.SpelObserver;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Models.Speelveld;
import Machiavelli.Models.Spel;
import Machiavelli.Models.Speler;
import Machiavelli.Views.SpeelveldView;

import java.rmi.RemoteException;

public class SpeelveldController implements SpelObserver {
	private Speelveld speelveld;
	private SpeelveldView speelveldview;
	private Spel spel;
	
	public SpeelveldController(Speelveld speelveld, Spel spel) {
        this.speelveld = speelveld;
        this.spel = spel;
        try {
            this.spel.addObserver(this);
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        this.speelveldview = new SpeelveldView(this, this.speelveld);
        this.speelveld.registratieView(this.speelveldview);

		speelveldview.getExitButton().setOnAction(event -> System.exit(0));

		this.speelveldview.show();
	}

	public Spel getSpel() {
		return this.spel;
	}

    @Override
    public void modelChanged(SpelRemote spel) throws RemoteException {
        // tmp casting
        System.out.println("SpeelveldController: Spel model changed!");
        this.spel = (Spel)spel;
        System.out.println("Aantal spelers: " + this.spel.getAantalSpelers());
    }
}
