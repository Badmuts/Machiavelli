package Machiavelli.Controllers;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Machiavelli.Factories.KarakterFactory;
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
          if(beurt.getObserverIndex() == 0) {
            
            /*KarakterFactory karakterfactory = new KarakterFactory();
            this.spel.setKarakterFactory(karakterfactory); */
            
            //Loop door aantal spelers, en laat voor elke speler de karakterkeuze menu zien
            for(int i = 0; i < spel.getMaxAantalSpelers(); i++) {
            beurt.getObserverList().get(beurt.getObserverIndex()).showKarakterMenu();
            int y = beurt.getObserverIndex();
            beurt.setObserverIndex(y += 1);

              if (beurt.getObserverIndex() >= beurt.getObserverList().size()) {
                beurt.setObserverIndex(0);
              }
            }
          } else {
              beurt.getInkomstenView();
            }
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
