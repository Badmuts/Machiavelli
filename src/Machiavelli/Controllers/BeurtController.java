package Machiavelli.Controllers;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Machiavelli.Interfaces.Remotes.BeurtRemote;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;

public class BeurtController extends UnicastRemoteObject {
  private SpelRemote spel;
  private BeurtRemote beurt;
  private SpelerRemote speler;
  private InkomstenController inkomstenController;


  public BeurtController(BeurtRemote beurt, SpelRemote spel, SpelerRemote speler) throws RemoteException {
    this.spel = spel;
    this.beurt = beurt;
    this.speler = speler;
  }

  /**
   * Deze method roept de geefbeurt method aan in het BeurtModel.
   * 
   */
  public void cmdGeefBeurt() {
    try {
          beurt.geefBeurt();
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    cmdShowInkomsten();
  }
  
  public void cmdShowInkomsten() {
    try {
      inkomstenController = new InkomstenController(this.speler);
      inkomstenController.show();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
