package Machiavelli.Controllers;

import Machiavelli.Interfaces.Remotes.GebouwKaartRemote;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Views.GebouwKaartView;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by badmuts on 14-6-15.
 */
public class GebouwKaartController extends UnicastRemoteObject {
    private SpelRemote spel;
    private ArrayList<GebouwKaartRemote> gebouwKaarten = new ArrayList<GebouwKaartRemote>();
    private ArrayList<GebouwKaartView> gebouwKaartViews = new ArrayList<GebouwKaartView>();

    /**
     * Maakt voor elke GebouwKaart uit de factory een nieuwe
     * GebouwKaartView aan en koppelt deze Controller aan de GebouwKaartView.
     *
     * @param spel
     * @throws RemoteException
     */
    public GebouwKaartController(SpelRemote spel) throws RemoteException {
        this.spel = spel;
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
}
