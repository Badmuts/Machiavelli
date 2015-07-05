package Machiavelli.Controllers;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Machiavelli.Interfaces.Remotes.BeurtRemote;
import Machiavelli.Interfaces.Remotes.KarakterFactoryRemote;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;

public class BeurtController extends UnicastRemoteObject {
    private SpelRemote spel;
    private BeurtRemote beurt;
    private SpelerRemote speler;
    private KarakterFactoryRemote karakterFactory;
    private InkomstenController inkomstenController;

    /**
     * @author Jimmy
     * 
     *         Verantwoordelijk voor het tonen van de views voor bepaalde spelers
     * 
     * @param beurt
     * @param spel
     * @param speler
     * @throws RemoteException
     */
    public BeurtController(BeurtRemote beurt, SpelRemote spel, SpelerRemote speler)
            throws RemoteException {
        // super(1099);
        this.spel = spel;
        this.beurt = beurt;
        this.speler = speler;
    }

    /**
     * Deze method roept de geefbeurt method aan in het BeurtModel
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
     * Deze method checkt welke view er getoont moet worden bij een beurt.
     */
    public void cmdNextObserver() {
        try {
            cmdGeefBeurt();
            if (beurt.getObserverIndex() == 0) {

                karakterFactory = this.spel.getKarakterFactory();
                karakterFactory.refreshFactory();

                // Loop door aantal spelers, en laat voor elke speler de karakterkeuze menu zien
                for (int i = 0; i < spel.getMaxAantalSpelers(); i++) {
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

}
