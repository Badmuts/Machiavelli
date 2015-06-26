package Machiavelli.Controllers;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;



import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Remotes.KarakterFactoryRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Views.KiesKarakterView;
import Machiavelli.Views.MagierKeuzeView;



import java.rmi.RemoteException;
import java.util.ArrayList;


/**
 * Created by daanrosbergen on 03/06/15.
 */
public class KarakterController extends UnicastRemoteObject {

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
    }

    public void cmdSetTarget(Karakter karakter) {
        try {
        	//Speler (dief0 heeft op de knop gelikt en de controller krijgt de gekozen karakter mee.
        	//Checken of er een speler zit aan het karakter.
        	//Op basis daarvan, actie ondernemen
        	//Of een melding laten zien!
        	ArrayList<Karakter> spelendeKarakters = new ArrayList<Karakter>();
        	for(SpelerRemote splr : this.speler.getSpel().getSpelers())
        	{
        		if(splr.getKarakter() != null)
        		{
        			spelendeKarakters.add(splr.getKarakter());
        		}
        	}
        	
        	boolean speeltMee = spelendeKarakters.contains(karakter);
        	
        	System.out.println("De volgende karakters spelen mee:");
        	for(Karakter krktr : spelendeKarakters)
        	{
        		System.out.println(krktr.getNummer() + " " + krktr.getNaam());
        		if(krktr.equals(karakter))
        		{
        			System.out.println("De " + karakter.getNummer() + " " + karakter.getNaam() + " speelt mee");
        			break;
        		}
        	}
        	
        	//check of het gekozen karakter (knop) meedoet aan het spel
//        	for(Karakter krktr : spelendeKarakters)
//        	{
        		if(speeltMee)
        		{
        			//Speler zet de target voor zijn karakter.
                    this.speler.getKarakter().setTarget(karakter);
                    
                    //Het karakter van de speler moet de initierende speler meekrijgen.
                    this.speler.getKarakter().setSpeler(this.speler);
                    
                    //Speler gebruik de eigenschap van zijn karakter.
                    this.speler.getKarakter().gebruikEigenschap();
                    this.karakterView.close();
                    new MeldingController().build("Je hebt je karaktereigenschap gebruikt op de " + karakter.getNaam()).cmdWeergeefMeldingView();
//        			break;
        		}
        		else
        		{
        			System.out.println("het gekozen karakter " + karakter.getNaam() + " speelt NIET mee");
        		    this.karakterView.close();
        			new MeldingController().build("De " + karakter.getNaam() + " speelt niet mee!").cmdWeergeefMeldingView();
//        			break;
        		}
//        	}
        	spelendeKarakters.clear();
        	spelendeKarakters = null;
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

    public void cmdSetTarget(Object target) {
        try {
            this.getSpeler().getKarakter().setTarget(target);
        } catch (Exception e) {
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

    public void close() {
        if (String.valueOf(this.typeView).equals("magier")) {
            magierKeuzeView.close();
        } else {
            this.karakterView.close();
        }
    }
    
    public SpelerRemote getSpeler()
    {
    	return this.speler;
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

    public void cmdGebruikEigenschap() {
        try {
            this.speler.getKarakter().gebruikEigenschap();
            this.close();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setTypeView(String type) {
        this.typeView = type;
    }
}
