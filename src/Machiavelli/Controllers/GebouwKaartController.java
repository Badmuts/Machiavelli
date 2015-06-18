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
        // Loop door de Gebouwen in GebouwFactory heen en maak een nieuwe view
        try {
            for (GebouwKaartRemote gebouwKaart: this.spel.getGebouwFactory().getGebouwen()) {
                // Maak een nieuwe view
                GebouwKaartView gebouwKaartView = new GebouwKaartView(this, gebouwKaart);
                // Voeg view toe aan model
                gebouwKaart.addObserver(gebouwKaartView);
                // Sla view op in controller
                gebouwKaartViews.add(gebouwKaartView);
                gebouwKaarten.add(gebouwKaart);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<GebouwKaartView> getObservers() {
        return this.gebouwKaartViews;
    }
}
