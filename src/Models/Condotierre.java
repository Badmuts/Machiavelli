package Models;

import Enummerations.Type;
import Interfaces.Karakter;

/**
 * Created by daanrosbergen on 03/06/15.
 */
public class Condotierre implements Karakter {

    // View die wacht op input van de user (selecteren gebouw)
    // TODO: Create SelectGebouwView
    private SelectGebouwView selectGebouwView;
    // Te vernietigen gebouw
    private GebouwKaart vernietigGebouw;
    // Standard attributes
    private int nummer = 8;
    private int bouwLimiet = 1;
    private String naam = "Condotierre";
    private Type type = Type.MILITAIR;

    public void gebruikEigenschap() {
        // TODO: sloopgebouw
        this.selectGebouwView.start();
        // TODO: Iets van een listener? (voor gekozen kaart (SelectGebouwView))
        // TODO: Speler, remove gold (betaalGoud)
        this.vernietigGebouw.getStad().removeGebouw(vernietigGebouw);
    }

    public void registerSelectGebouwView(SelecteGebouwView selecteGebouwView) {
        this.selectGebouwView = selectGebouwView;
    }
}
