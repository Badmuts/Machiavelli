package Machiavelli.Views;

import Machiavelli.Controllers.GebouwKaartController;
import Machiavelli.Interfaces.Observers.HandObserver;
import Machiavelli.Interfaces.Remotes.GebouwKaartRemote;
import Machiavelli.Interfaces.Remotes.HandRemote;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class HandActionBarView extends UnicastRemoteObject implements HandObserver {

    private HandRemote hand;
    private ArrayList<GebouwKaartView> gebouwKaartViews = new ArrayList<GebouwKaartView>();
    private Rectangle kaartholder;
    private GebouwKaartController gebouwKaartController;
    private StackPane pane = new StackPane();

    /**
     * View voor de gebouwkaarten in de hand van de speler.
     *
     * @param hand
     */
    public HandActionBarView(HandRemote hand, GebouwKaartController gebouwKaartController) throws RemoteException {
        this.hand = hand;
        this.gebouwKaartController = gebouwKaartController;
        this.hand.addObserver(this);
        this.pane.setPrefSize(840, 275);
        this.pane.setCache(true);
        this.pane.setCacheShape(true);
        this.pane.setCacheHint(CacheHint.SPEED);

        createView();
    }

    private void createView() throws RemoteException {
        createBackground(); // Maak achtergrond aan
        buildGebouwKaartViewsArray(); // Vul gebouwKaartViews[]

        this.pane.getChildren().addAll(kaartholder); // Voeg achtergrond toe
        addGebouwKaartViews(); // Voeg views toe aan HandActionBarView (pane)
        this.pane.setLayoutX(250);
    }

    /**
     * Maak achtergrond aan waar de kaarten overheen worden
     * geplaatst.
     */
    private void createBackground() {
        kaartholder = new Rectangle(0, 0, 840, 250);
        kaartholder.setFill(Color.rgb(74, 74, 74));
        StackPane.setAlignment(kaartholder, Pos.BOTTOM_CENTER);
    }

    /**
     * Haalt alle GebouwKaartViews op van elke GebouwKaart
     * en plaatst deze in gebouwKaartViews[].
     */
    private void buildGebouwKaartViewsArray() throws RemoteException {
        ArrayList<GebouwKaartRemote> kaartenLijst = this.hand.getKaartenLijst();
        for (GebouwKaartRemote gebouwKaartRemote: kaartenLijst) {
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
        int totalWidth = 0;
        int index = 0;
        int delay = 75;
        // Loop  door gebouwKaartViews en wijzig de X coordinaat.
        for (GebouwKaartView gebouwKaartView: gebouwKaartViews) {
//            gebouwKaartView.view().setLayoutX(x); // Zet X coordinaat
            gebouwKaartView.view().setCache(true);
            gebouwKaartView.view().setCacheShape(true);
            gebouwKaartView.view().setCacheHint(CacheHint.SPEED);
            gebouwKaartView.view().setRotate(calcRotation(index, gebouwKaartViews.size()));
            handPane.getChildren().add(gebouwKaartView.view()); // Voeg view to aan Pane
            totalWidth += gebouwKaartView.view().getPrefWidth();

            TranslateTransition transition = new TranslateTransition(Duration.millis(250), gebouwKaartView.view());
            transition.setDelay(Duration.millis(delay));
            transition.setFromX(0);
            transition.setToX(x);
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(125), gebouwKaartView.view());
            fadeTransition.setDelay(Duration.millis(delay));
            fadeTransition.setFromValue(0.0);
            fadeTransition.setToValue(1.0);

            fadeTransition.play();
            transition.play();

            delay += 75;
            x += 130; // Verhoog X coordinaat met 100
            index++;
        }
        handPane.setMaxWidth(totalWidth);
        this.pane.getChildren().add(handPane);
        StackPane.setAlignment(handPane, Pos.TOP_CENTER);
    }

    private int calcRotation(int cardIndex, int totalCards) {
        // TODO: implement rotation calculation
        // int middle = totalCards/2;
        return 0;
    }

    @Override
    public void modelChanged(HandRemote hand) throws RemoteException {
        Platform.runLater(() -> {
            // TODO: update hand
            try {
                System.out.println("Hand view changed!");
                this.hand = hand;
                this.gebouwKaartViews.clear(); // Leeg gebouwKaartViews[]
                this.pane.getChildren().clear(); // Leeg het pane
                createView();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Pane getPane() {
        return this.pane;
    }
}
