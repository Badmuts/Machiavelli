package Machiavelli.Models;

import Machiavelli.Interfaces.Remotes.BeurtRemote;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Sander de Jong
 *
 */
public class Beurt implements BeurtRemote {
    // Variables
    private Spel spel;
    private ArrayList<Speler> spelerLijst;
    private Speler speler;

    public Beurt(Spel spel, ArrayList<Speler> spelerLijst)
    {
        this.spel = spel;
        this.spelerLijst = spelerLijst;
    }

    // Best method naam
    public void geefBeurt(Speler speler) throws RemoteException
    {
        // TODO: een speler object een beurt geven.
    }
    
    public Speler getSpeler() throws RemoteException
    {
    	return this.speler;
    }
    
    public void setSpeler(Speler speler) throws RemoteException {
    	this.speler = speler;
    }
}
