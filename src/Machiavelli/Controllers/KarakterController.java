package Machiavelli.Controllers;

import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Views.KiesKarakterView;

import java.rmi.RemoteException;

/**
 * Created by daanrosbergen on 03/06/15.
 */
public class KarakterController {

    private String typeView;
    //TODO: krijg speler van beurt..
    private SpelerRemote speler;
    private KiesKarakterView karakterView;
    private Karakter target;
    
    public KarakterController(SpelerRemote speler, String typeView) throws RemoteException
    {
        // TYPE VAN VIEW IN CONSTRUCTOR
        // TYPES:
        // - KARAKTER: KIES KARAKTER ALS TARGET
        // - RONDE : KIES KARAKTER VOOR SPELER
        // - SPELER: KIES SPELER ALS TARGET
        this.typeView = typeView;
    	this.target = null;
    	this.speler = speler;
        this.karakterView = new KiesKarakterView(this.speler.getSpel().getKarakterFactory(), this);
    	this.karakterView.show();
//    	cmdTrekkenKaart();
    }
//
//    public KarakterController(Speler speler) {
//        this.speler = speler;
//    }
//
//    public void cmdGebruikKarakterEigenschap() {
//        Karakter karakter = this.speler.getKarakter();
//        karakter.gebruikEigenschap();
//    }

//    public void cmdKiesKarakter() throws RemoteException
//    {
//    	KarakterFactoryRemote karakterFactory = this.speler.getSpel().getKarakterFactory();
//
//    	for (Karakter karakter : karakterFactory.getKarakters()) {
//			karakterView.createKarakterView(karakter);
//		}
//
//    	this.karakterView.addButtonsToView();
//
//    	for (Button button: karakterView.getButtonList())
//    	{
//    		try
//    		{
//    			int buttonNumber = karakterView.getButtonList().indexOf(button);
//
//    			button.setOnAction((event) ->
//    			{
//    				try
//    				{
//    					this.target = karakterFactory.getKarakterByNumber(buttonNumber);
//	    				this.speler.setKarakter(target);
//    				}
//    				catch(Exception e)
//    				{
//    					e.printStackTrace();
//    				}
//
//    				//test if the karakter is deleted from the factory and close the view.
//    				finally
//    				{
//    					try
//    					{
//							for(Karakter karakter : karakterFactory.getKarakters())
//							{
//								System.out.println(karakterFactory.getKarakters().indexOf(karakter) + " " + karakter.getNaam());
//							}
//
//							System.out.println("De speler is een: " + this.speler.getKarakter().getNaam());
//
//							cmdSluitKiesKarakterView();
//
//		    				new MeldingController().build("Je bent deze ronde een " + this.getTarget().getNaam()).cmdWeergeefMeldingView();
//						}
//    					catch (Exception e)
//    					{
//							e.printStackTrace();
//						}
//    				}
//    			});
//			}
//    		catch (Exception e) {
//				e.printStackTrace();
//			}
//    	}
//    }

    public void cmdSetTarget(Karakter karakter) {
        try {
            this.speler.getKarakter().setTarget(karakter);
            this.speler.getKarakter().gebruikEigenschap();
            this.karakterView.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTypeView() {
        return this.typeView;
    }

    public void cmdSetKarakter(Karakter karakter) {
        try {
            this.speler.setKarakter(karakter);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void cmdSetSpelerTarget(SpelerRemote speler) {
        try {
            this.speler.getKarakter().setTarget(speler);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void show() {
        this.karakterView.show();
    }
}
