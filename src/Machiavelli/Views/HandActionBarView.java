package Machiavelli.Views;

import Machiavelli.Interfaces.Observers.HandObserver;
import Machiavelli.Interfaces.Remotes.HandRemote;
import Machiavelli.Models.GebouwKaart;
import Machiavelli.Models.Hand;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class HandActionBarView extends Pane implements HandObserver {

    private HandRemote hand;
    private ArrayList<GebouwKaartView> gebouwKaartViews = new ArrayList<GebouwKaartView>();
    private Rectangle kaartholder;

    /**
     * View voor de gebouwkaarten in de hand van de speler.
     *
     * @param hand
     */
    public HandActionBarView(Hand hand) {
        super();
        this.hand = hand;
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
            // Loop door kaarten in hand
            for (GebouwKaart gebouwKaart: hand.getKaartenLijst()) {
                // Haal view op van gebouwkaart view en plaats in gebouwKaartViews[]
                gebouwKaartViews.add(gebouwKaart.getGebouwkaartView());
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
            gebouwKaartView.setLayoutX(x); // Zet X coordinaat
            this.getChildren().add(gebouwKaartView); // Voeg view to aan Pane
            x += 100; // Verhoog X coordinaat met 100
        }
    }

    @Override
    public void modelChanged(HandRemote hand) throws RemoteException {
        // TODO: Redraw view (this)
        this.hand = hand;
    }

    private int calcRotation(int cardIndex, int totalCards) {
        // TODO: implement rotation calculation
        return 1;
    }
}
