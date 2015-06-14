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

    public HandActionBarView(Hand hand) {
        super();
        this.hand = hand;

        createBackground();
        createGebouwKaartViews();

        this.getChildren().addAll(kaartholder);
        addGebouwKaartViews();
    }

    private void addGebouwKaartViews() {
        int x = 0;
        for (GebouwKaartView gebouwKaartView: gebouwKaartViews) {
            gebouwKaartView.setLayoutX(x);
            this.getChildren().add(gebouwKaartView);
            x += 100;
        }
    }

    private void createBackground() {
        kaartholder = new Rectangle(0, 0, 840, 250);
        kaartholder.setFill(Color.GRAY);
    }

    @Override
    public void modelChanged(HandRemote hand) throws RemoteException {
        this.hand = hand;
    }

    private void createGebouwKaartViews() {
        try {
            ArrayList<GebouwKaart> kaarten = hand.getKaartenLijst();
            for (GebouwKaart gebouwKaart: kaarten) {
                gebouwKaartViews.add(gebouwKaart.getGebouwkaartView());
            }
        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }

    private int calcRotation(int i, int totalLength) {
        // TODO: implement rotation calculation
        return 1;
    }
}
