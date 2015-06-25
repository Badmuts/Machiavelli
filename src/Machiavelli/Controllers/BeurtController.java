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

  /*
   * public void cmdBeginRondeBeurt() throws RemoteException { beurt.BeginRondeBeurt(); }
   */
  public void cmdGeefBeurt() {
    try {
      beurt.geefBeurt();
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    try {
      beurt.notifyObservers();
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  public void cmdWeergeefInkomsten() {
    try {
      inkomstenController = new InkomstenController(this.speler);
      inkomstenController.show();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
