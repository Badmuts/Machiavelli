package Models;

import Enumerations.Type;
import Interfaces.Karakter;

import java.util.ArrayList;

/**
 * Created by daanrosbergen on 03/06/15.
 * Edited by Sander de Jong on 08/06/15.
 */
public class Prediker implements Karakter {
    @Override
    public void gebruikEigenschap() {
        // TODO: beschermt tegen karakter Condotierre
    }

    // Hand van speler ophalen en checken hoeveel speler
    public void ontvangenGebouwBonusGoud(Speler speler)
    {
        ArrayList<GebouwKaart> tempList = speler.getStad().getGebouwen();
        int count = 0;

        for(int i = 0; i < tempList.size(); i ++)
        {
            // Voel me niet zo lekker
            if (tempList.get(i).getType() == Type.KERKELIJK)
            {
                count++;
            }
        }

        speler.getPortemonnee().ontvangenGoud(count);
    }
}
