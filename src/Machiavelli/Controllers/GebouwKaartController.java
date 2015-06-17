package Machiavelli.Controllers;

import Machiavelli.Factories.GebouwFactory;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Models.GebouwKaart;
import Machiavelli.Views.GebouwKaartView;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by badmuts on 14-6-15.
 */
public class GebouwKaartController extends UnicastRemoteObject implements Serializable {
    private SpelRemote spel;
    private ArrayList<GebouwKaart> gebouwKaarten = new ArrayList<GebouwKaart>();
    private ArrayList<GebouwKaartView> gebouwKaartViews = new ArrayList<GebouwKaartView>();

    /**
     * Maakt voor elke GebouwKaart uit de factory een nieuwe
     * GebouwKaartView aan en koppelt deze Controller aan de GebouwKaartView.
     *
     * @param gebouwFactory
     * @throws RemoteException
     */
    public GebouwKaartController(SpelRemote spel) throws RemoteException {
        this.spel = spel;
        // Loop door de Gebouwen in GebouwFactory heen en maak een nieuwe view
        for (GebouwKaart gebouwKaart: this.spel.getGebouwFactory().getGebouwen()) {
            // Maak een nieuwe view
            GebouwKaartView gebouwKaartView = new GebouwKaartView(this, gebouwKaart);
            // Voeg view toe aan model
            gebouwKaart.addObserver(gebouwKaartView);
            gebouwKaart.setKosten(10000);
            // Sla view op in controller
            gebouwKaartViews.add(gebouwKaartView);
            gebouwKaarten.add(gebouwKaart);
        }
        this.spel.getGebouwFactory().setGebouwen(gebouwKaarten);
        GebouwFactory factory9000 = this.spel.getGebouwFactory();
    }

    public ArrayList<GebouwKaartView> getObservers() {
        return this.gebouwKaartViews;
    }
}
