package Machiavelli.Views;

import Machiavelli.Controllers.GebouwKaartController;
import Machiavelli.Interfaces.Observers.HandObserver;
import Machiavelli.Interfaces.Remotes.GebouwKaartRemote;
import Machiavelli.Interfaces.Remotes.HandRemote;
import Machiavelli.Models.Hand;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class HandActionBarView extends UnicastRemoteObject implements HandObserver {

    private HandRemote hand;
    private ArrayList<GebouwKaartView> gebouwKaartViews = new ArrayList<GebouwKaartView>();
    private Rectangle kaartholder;
    private GebouwKaartController gebouwKaartController;
    private Pane pane = new StackPane();
    private Rectangle clip;

    /**
     * View voor de gebouwkaarten in de hand van de speler.
     *
     * @param hand
     */
    public HandActionBarView(HandRemote hand, GebouwKaartController gebouwKaartController) throws RemoteException {
        this.hand = hand;
        this.gebouwKaartController = gebouwKaartController;
        try {
            this.hand.addObserver(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        createBackground(); // Maak achtergrond aan
        buildGebouwKaartViewsArray(); // Vul gebouwKaartViews[]

        this.pane.getChildren().addAll(kaartholder); // Voeg achtergrond toe
        addGebouwKaartViews(); // Voeg views toe aan HandActionBarView (pane)
//        this.pane.setPrefHeight(250);
//        StackPane.setAlignment(kaartholder, Pos.BOTTOM_CENTER);
    }

    /**
     * Maak achtergrond aan waar de kaarten overheen worden
     * geplaatst.
     */
    private void createBackground() {
        kaartholder = new Rectangle(0, 0, 840, 250);
        kaartholder.setFill(Color.rgb(74, 74, 74));
        clip = new Rectangle(0, 0, 840, 250);
        this.pane.setClip(clip);
    }

    /**
     * Haalt alle GebouwKaartViews op van elke GebouwKaart
     * en plaatst deze in gebouwKaartViews[].
     */
    private void buildGebouwKaartViewsArray() throws RemoteException {
        // Hand heeft GebouwKaarten.
        // GebouwKaarten hebben observers
        // GebouwController heeft GebouwKaartViews wat GebouwkaartObservers zijn
        // Haal de GebouwkaartObserver op.
        // Help?!
        for (GebouwKaartRemote gebouwKaartRemote: hand.getKaartenLijst()) {
            GebouwKaartView gebouwKaartView = new GebouwKaartView(this.gebouwKaartController, gebouwKaartRemote);
            gebouwKaartRemote.addObserver(gebouwKaartView); // Add view (observer) to remote
            this.gebouwKaartController.addView(gebouwKaartView); // Add view to controller
            this.gebouwKaartController.addModel(gebouwKaartRemote); // Add model to controller
            this.gebouwKaartViews.add(gebouwKaartView); // Add view to local gebouwKaartView[]
        }
    }

    /**
     * Voeg gebouwKaart views toe aan de HandActionBarView.
     * Wijzig de X coordinaat van elke GebouwKaartView zodat
     * deze de vorige kaart overlapt.
     */
    private void addGebouwKaartViews() {
        Pane handPane = new Pane();
        int x = 0; // X coordinaat (voor uitlijning)
        double y = -40.0;
        int index = 0;
        // Loop  door gebouwKaartViews en wijzig de X coordinaat.
        for (GebouwKaartView gebouwKaartView: gebouwKaartViews) {
            gebouwKaartView.view().setLayoutX(x); // Zet X coordinaat
            gebouwKaartView.view().setLayoutY(y);
            gebouwKaartView.view().setRotate(calcRotation(index, gebouwKaartViews.size()));
            handPane.getChildren().add(gebouwKaartView.view()); // Voeg view to aan Pane
            x += 130; // Verhoog X coordinaat met 100
            index++;
        }
        this.pane.getChildren().add(handPane);
    }

    private int calcRotation(int cardIndex, int totalCards) {
        // TODO: implement rotation calculation
//        int middle = totalCards/2;
        return 0;
    }

    @Override
    public void modelChanged(Hand hand) throws RemoteException {
        // TODO: update hand
        this.hand = hand;
    }

    public Pane getPane() {
        return this.pane;
    }
}
