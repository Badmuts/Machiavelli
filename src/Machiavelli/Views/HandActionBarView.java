package Machiavelli.Views;

import Machiavelli.Controllers.GebouwKaartController;
import Machiavelli.Interfaces.Observers.HandObserver;
import Machiavelli.Models.GebouwKaart;
import Machiavelli.Models.Hand;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class HandActionBarView extends Pane implements HandObserver {

    private Hand hand;
    private ArrayList<GebouwKaartView> gebouwKaartViews = new ArrayList<GebouwKaartView>();
    private Rectangle kaartholder;
    private GebouwKaartController gebouwKaartController;

    /**
     * View voor de gebouwkaarten in de hand van de speler.
     *
     * @param hand
     */
    public HandActionBarView(Hand hand, GebouwKaartController gebouwKaartController) {
        super();
        this.hand = hand;
        this.gebouwKaartController = gebouwKaartController;
        try {
            this.hand.addObserver(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        createBackground(); // Maak achtergrond aan
        createGebouwKaartViews(); // Vul gebouwKaartViews[]

        this.getChildren().addAll(kaartholder); // Voeg achtergrond toe
        addGebouwKaartViews(); // Voeg views toe aan HandActionBarView (pane)
    }

    /**
     * Maak achtergrond aan waar de kaarten overheen worden
     * geplaatst.
     */
    private void createBackground() {
        kaartholder = new Rectangle(0, 0, 840, 250);
        kaartholder.setFill(Color.GRAY);
    }

    /**
     * Haalt alle GebouwKaartViews op van elke GebouwKaart
     * en plaatst deze in gebouwKaartViews[].
     */
    private void createGebouwKaartViews() {
        try {
            // Hand heeft GebouwKaarten.
            // GebouwKaarten hebben observers
            // GebouwController heeft GebouwKaartViews wat GebouwkaartObservers zijn
            // Haal de GebouwkaartObserver op.
            // Help?!
            ArrayList<GebouwKaartView> observers = gebouwKaartController.getObservers();
            for (GebouwKaart gebouwKaart: hand.getKaartenLijst()) {
                System.out.println(gebouwKaart);
//                for (GebouwKaartObserver observer: gebouwKaart.getObservers()) {
//                    gebouwKaartViews.add((GebouwKaartView)observer);
//                }
            }

        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }

    /**
     * Voeg gebouwKaart views toe aan de HandActionBarView.
     * Wijzig de X coordinaat van elke GebouwKaartView zodat
     * deze de vorige kaart overlapt.
     */
    private void addGebouwKaartViews() {
        int x = 0; // X coordinaat (voor uitlijning)
        // Loop  door gebouwKaartViews en wijzig de X coordinaat.
        for (GebouwKaartView gebouwKaartView: gebouwKaartViews) {
            gebouwKaartView.view().setLayoutX(x); // Zet X coordinaat
            this.getChildren().add(gebouwKaartView.view()); // Voeg view to aan Pane
            x += 100; // Verhoog X coordinaat met 100
        }
    }

    private int calcRotation(int cardIndex, int totalCards) {
        // TODO: implement rotation calculation
        return 1;
    }

    @Override
    public void modelChanged(Hand hand) throws RemoteException {
        this.hand = hand;
    }
}
