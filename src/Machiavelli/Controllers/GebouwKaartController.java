package Machiavelli.Controllers;

import Machiavelli.Factories.GebouwFactory;
import Machiavelli.Models.GebouwKaart;
import Machiavelli.Views.GebouwKaartView;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by badmuts on 14-6-15.
 */
public class GebouwKaartController implements Serializable {
    private ArrayList<GebouwKaart> gebouwKaarten;
    private ArrayList<GebouwKaartView> gebouwKaartViews = new ArrayList<>();

    /**
     * Maakt voor elke GebouwKaart uit de factory een nieuwe
     * GebouwKaartView aan en koppelt deze Controller aan de GebouwKaartView.
     *
     * @param gebouwFactory
     * @throws RemoteException
     */
    public GebouwKaartController(GebouwFactory gebouwFactory) throws RemoteException {
        // Loop door de GebouwFactory heen en maak een nieuwe view
        for (GebouwKaart gebouwKaart: gebouwFactory.getGebouwen()) {
            GebouwKaartView gebouwKaartView = new GebouwKaartView(this, gebouwKaart);
            gebouwKaart.registratieView(gebouwKaartView);
            gebouwKaartViews.add(gebouwKaartView);
        }
        // Sla de gebouwkaarten op in de controller.
        this.gebouwKaarten = gebouwFactory.getGebouwen();
    }
}
