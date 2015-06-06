package Models;

import java.util.ArrayList;

/**
 *
 * @author Sander de Jong
 *
 */
public class Beurt
{
    // Variables
    private Spel spel;
    private ArrayList<Speler> spelerLijst;

    public Beurt(Spel spel, ArrayList<Speler> spelerLijst)
    {
        this.spel = spel;
        this.spelerLijst = spelerLijst;
    }

    // Best method naam
    public void geefBeurt(Speler speler)
    {
        // Speler moet iets hebben om te bepalen of hij aan de beurt is of niet?
    }
}
