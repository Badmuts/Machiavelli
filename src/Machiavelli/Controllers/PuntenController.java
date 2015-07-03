package Machiavelli.Controllers;

import java.rmi.RemoteException;
import java.util.ArrayList;

import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Models.PuntenModel;
import Machiavelli.Views.PuntenView;

public class PuntenController {

    private PuntenModel puntenModel;
    private PuntenView puntenView;
    private SpelerRemote winnaar;
    private ArrayList<SpelerRemote> verliezers;

    public PuntenController(SpelRemote spel) throws RemoteException {

        puntenView = new PuntenView();
        puntenModel = new PuntenModel(spel);
    }

    public void cmdBerekenScorelijst()
    {
        try {
            ArrayList<SpelerRemote> tempList = puntenModel.berekenScorelijst();
            this.winnaar = tempList.get(0);
            tempList.remove(0);
            this.verliezers = tempList;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String cmdGetWinnaar()
    {
        try {
            return "Je hebt gewonnen met " + this.winnaar.getStad().getWaardeStad();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String cmdgetVerliezer()
    {
        try {
            return "Je hebt verloren! De winnaar heeft " + this.winnaar.getStad().getWaardeStad();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
