package Models.Karakters;

import Enumerations.Type;
import Interfaces.Bonusable;
import Interfaces.Karakter;
import Models.GebouwKaart;
import Models.Speler;

import java.util.ArrayList;

/**
 * Created by daanrosbergen on 03/06/15.
 */
public class Condotierre implements Karakter, Bonusable {

    private final String      naam            = "Condotierre";
    private final int         nummer          = 8;
    private final int         bouwLimiet      = 1;
    private final Type        type            = Type.MILITAIR;

    private GebouwKaart vernietigGebouw = null;
    private Speler      speler          = null;

    public void gebruikEigenschap() {
        // TODO: sloopgebouw
        // this.selectGebouwView.start();
        // TODO: Iets van een listener? (voor gekozen kaart (SelectGebouwView))
        // TODO: Speler, remove gold (betaalGoud)
        // this.vernietigGebouw.getStad().removeGebouw(vernietigGebouw);
    }

    @Override
    public void setSpeler(Speler speler) {
        this.speler = speler;
    }

    @Override
    public void ontvangenBonusGoud() {
        ArrayList<GebouwKaart> gebouwen = speler.getStad().getGebouwen();
        for(GebouwKaart gebouw: gebouwen) {
            if (gebouw.getType() == this.type)
                speler.getPortemonnee().ontvangenGoud(1);
        }
    }

    /*
    TODO: implement registerSelectGebouwView
    public void registerSelectGebouwView(SelecteGebouwView selecteGebouwView) {
        this.selectGebouwView = selectGebouwView;
    } */

    public Type getType() {
        return type;
    }

    public int getBouwLimiet() {

        return bouwLimiet;
    }

    public int getNummer() {

        return nummer;
    }

    public String getNaam() {
        return naam;
    }
}
