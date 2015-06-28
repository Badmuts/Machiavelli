package Machiavelli.Controllers;

import Machiavelli.Interfaces.Remotes.BeurtRemote;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Views.SpeelveldView;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BeurtController extends UnicastRemoteObject {
  private SpelRemote spel;
  private BeurtRemote beurt;
  private SpelerRemote speler;
  private InkomstenController inkomstenController;
  private SpeelveldView speelveldView;


  public BeurtController(BeurtRemote beurt, SpelRemote spel, SpelerRemote speler) throws RemoteException {
//    super(1099);
    this.spel = spel;
    this.beurt = beurt;
    this.speler = speler;
  }

  /**
   * Deze method roept de geefbeurt method aan in het BeurtModel.
   * De beurt.getobserver.get() haalt de observer op voor de volgende speler
   * en laat bij diegene de inkomsten view zien met de showInkomsten method 
   * in speelveldview.
   * 
   */
  public void cmdGeefBeurt() {
    try {
          beurt.geefBeurt();
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
    /**
     * Het oproepen van de inkomsten view.
     */
  public void cmdShowInkomsten() {
    try {
      inkomstenController = new InkomstenController(this.speler);
      inkomstenController.show();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  /**
   * Het oproepen van de karakter kiezen view.
   */
  public void cmdShowKarakterKiezen() {
    
    try {
      KarakterController karaktercontroller = new KarakterController(this.speler, "ronde");
      karaktercontroller.show();
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }
  
  
}
