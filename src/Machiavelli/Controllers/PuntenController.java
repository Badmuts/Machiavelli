package Machiavelli.Controllers;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Machiavelli.Interfaces.Remotes.PuntenRemote;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Views.PuntenView;

public class PuntenController extends UnicastRemoteObject {
    private PuntenView puntenView;
    private PuntenRemote puntenModel;
    private SpelerRemote winnaar;
    private SpelRemote spel;

    public PuntenController(SpelRemote spel, PuntenRemote puntenModel) throws RemoteException {

        this.spel = spel;
        this.puntenModel = puntenModel;
        this.puntenView = new PuntenView(this);
        puntenView.show();
    }

    public String cmdBerekenScorelijst() throws RemoteException {
        ArrayList<SpelerRemote> tempList = puntenModel.berekenScorelijst();
        this.winnaar = tempList.get(0);
        for (int i = 0; i < this.spel.getSpelers().size(); i++) {
            if (this.spel.getSpelers().get(i).equals(this.winnaar)) {
                return cmdGetWinnaar();
            } else if (!this.spel.getSpelers().get(i).equals(this.winnaar)) {
                return cmdgetVerliezer();
            }
        }
        return "het spel is klaar";
    }

    public String cmdGetWinnaar() throws RemoteException {
        return this.spel.getSpelers().get(0).getKarakter().getNaam() + " heeft gewonnen met " + this.winnaar.getStad().getWaardeStad() + " punten!";
    }

    public String cmdgetVerliezer() throws RemoteException {
        return "Je hebt verloren! De winnaar heeft " + this.winnaar.getStad().getWaardeStad()
                + " punten";
    }
}
