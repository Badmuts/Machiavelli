package Machiavelli.Controllers;

import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Remotes.KarakterFactoryRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Views.KiesKarakterView;
import Machiavelli.Views.MagierKeuzeView;

import java.rmi.RemoteException;

/**
 * Created by daanrosbergen on 03/06/15.
 */
public class KarakterController {

    private String typeView;
    //TODO: krijg speler van beurt..
    private SpelerRemote speler;
    private KiesKarakterView karakterView;
    private MagierKeuzeView magierKeuzeView;

    public KarakterController(SpelerRemote speler, String typeView) throws RemoteException
    {
        // TYPE VAN VIEW IN CONSTRUCTOR
        // TYPES:
        // - KARAKTER: KIES KARAKTER ALS TARGET
        // - RONDE : KIES KARAKTER VOOR SPELER
        // - SPELER: KIES SPELER ALS TARGET
        // - MAGIER: KIES SPELER OF STAPEL
        this.typeView = typeView;
    	this.speler = speler;
        this.karakterView = new KiesKarakterView(this.speler.getSpel().getKarakterFactory(), this);
//    	this.karakterView.show();
    }

    public void cmdSetTarget(Karakter karakter) {
        try {
            this.speler.getKarakter().setTarget(karakter);
            this.speler.getKarakter().gebruikEigenschap();
            this.karakterView.close();
            new MeldingController().build("Je hebt je karaktereigenschap gebruikt op de " + karakter.getNaam()).cmdWeergeefMeldingView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTypeView() {
        return this.typeView;
    }

    public void cmdSetKarakter(Karakter karakter) {
        try {
        	KarakterFactoryRemote karakterFactory = this.speler.getSpel().getKarakterFactory();
        	
        	Karakter gekozenKarakter = karakterFactory.getKarakterByNumber(karakter.getNummer());
        	
        	this.speler.setKarakter(gekozenKarakter);
        	
        	this.karakterView.close();
        	new MeldingController().build("Je bent deze ronde een " + gekozenKarakter.getNaam()).cmdWeergeefMeldingView();
        	
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void cmdSetSpelerTarget(SpelerRemote speler) {
        try {
            this.speler.getKarakter().setTarget(speler);
            //execute magier eigenschap ?
            this.speler.getKarakter().gebruikEigenschap();
            this.karakterView.close();
            new MeldingController().build("Je hebt je karaktereigenschap op de " + speler.getKarakter().getNaam() + " gebruikt").cmdWeergeefMeldingView();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void show() {
        if (String.valueOf(this.typeView).equals("magier")) {
            magierKeuzeView = new MagierKeuzeView(this);
            magierKeuzeView.show();
        } else {
            this.karakterView.show();
        }
    }
    
    public SpelerRemote getSpeler()
    {
    	return this.speler;
    }

    public void cmdKiesSpelerTarget() {

    }
}
