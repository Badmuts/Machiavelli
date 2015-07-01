package Machiavelli.Controllers;

import Machiavelli.Models.Spelregels;
import Machiavelli.Views.SpelregelsView;

/**
 * @author Jamie Kalloe
 *         <p>
 *         De RaadplegenSpelregelsController wordt gebruikt om de spelregelsView aan te maken en te
 *         weergeven op het scherm.
 */

public class RaadplegenSpelregelsController {

    // Variables
    private SpelregelsView spelRegelsView;
    private Spelregels spelRegels;

    /**
     * Maakt een nieuwe spelregelsView view aan.
     */
    public RaadplegenSpelregelsController() {
        try {
            this.spelRegelsView = new SpelregelsView(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Roept de weergeef/show functie van de spelregelsView aan.
     */
    public void cmdWeergeefSpelregels() {
        this.spelRegelsView.show();
    }

    /**
     * Roept de sluit/close functie van de spelregelsView aan.
     */
    public void cmdSluitSpelregels() {
        this.spelRegelsView.close();
    }
}
