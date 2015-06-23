package Machiavelli.Controllers;

import Machiavelli.Interfaces.Remotes.GebouwKaartRemote;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Models.GebouwKaart;
import Machiavelli.Views.GebouwKaartView;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by badmuts on 14-6-15.
 */
public class GebouwKaartController extends UnicastRemoteObject {
    private SpelRemote spel;
    private SpelerRemote speler;
    private ArrayList<GebouwKaartRemote> gebouwKaarten = new ArrayList<>();
    private ArrayList<GebouwKaartView> gebouwKaartViews = new ArrayList<>();
    private ArrayList<GebouwKaartRemote> activeCards = new ArrayList<>();

    /**
     * Maakt voor elke GebouwKaart uit de factory een nieuwe
     * GebouwKaartView aan en koppelt deze Controller aan de GebouwKaartView.
     *
     * @param spel
     * @throws RemoteException
     */
    public GebouwKaartController(SpelRemote spel, SpelerRemote spelerRemote) throws RemoteException {
        this.spel = spel;
        this.speler = spelerRemote;
    }

    public ArrayList<GebouwKaartView> getObservers() {
        return this.gebouwKaartViews;
    }

    public void addView(GebouwKaartView gebouwKaartView) {
        gebouwKaartViews.add(gebouwKaartView);
    }

    public void addModel(GebouwKaartRemote gebouwKaartRemote) {
        this.gebouwKaarten.add(gebouwKaartRemote);
    }

    public void setActiveCard(GebouwKaartRemote gebouwKaartRemote) {
        this.activeCards.add(gebouwKaartRemote);
    }

    public void removeActiveCard(GebouwKaartRemote gebouwKaart) {
        this.activeCards.remove(gebouwKaart);
    }

    public void cmdBouwGebouw() {
        for (GebouwKaartRemote gebouwKaartRemote: activeCards) {
            try {
                this.speler.bouwenGebouw((GebouwKaart) gebouwKaartRemote);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
