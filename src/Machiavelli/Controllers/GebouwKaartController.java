package Machiavelli.Controllers;

import Machiavelli.Factories.GebouwFactory;
import Machiavelli.Interfaces.Remotes.GebouwKaartRemote;
import Machiavelli.Interfaces.Remotes.SpelRemote;
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
        GebouwFactory factory = this.spel.getGebouwFactory();
        // Loop door de Gebouwen in GebouwFactory heen en maak een nieuwe view
        try {
            for (GebouwKaartRemote gebouwKaart: factory.getGebouwen()) {
                // Maak een nieuwe view
                GebouwKaartView gebouwKaartView = new GebouwKaartView(this, gebouwKaart);
                // Voeg view toe aan model
                gebouwKaart.addObserver(gebouwKaartView);
                gebouwKaart.setKosten(10000);
                // Sla view op in controller
                gebouwKaartViews.add(gebouwKaartView);
                gebouwKaarten.add(gebouwKaart);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        this.spel.getGebouwFactory().setGebouwen(gebouwKaarten); // TEMP: Plaats gebouwkaart[] in de factory, overschrijf de oude.
        GebouwFactory factory9000 = this.spel.getGebouwFactory(); // TEST: Wat zit er in de factory?

        for (GebouwKaartRemote gebouwKaartRemote: factory9000.getGebouwen()) {
            gebouwKaartRemote.setKosten(12);
        }
    }

    public ArrayList<GebouwKaartView> getObservers() {
        return this.gebouwKaartViews;
    }
}
