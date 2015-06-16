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
        System.out.println(this.hand);
        try {
            this.hand.addObserver(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        createBackground();
        createGebouwKaartViews();

        this.getChildren().addAll(kaartholder);
        addGebouwKaartViews();
    }

    private void createBackground() {
        kaartholder = new Rectangle(0, 0, 840, 250);
        kaartholder.setFill(Color.GRAY);
    }

    private void createGebouwKaartViews() {
        try {
            for (GebouwKaart gebouwKaart: hand.getKaartenLijst()) {
                System.out.println(gebouwKaart);
                if (gebouwKaart.getGebouwkaartView() == null) {
                    System.out.println("Geen gebouwkaartView gevonden!");
                }
                gebouwKaartViews.add(gebouwKaart.getGebouwkaartView());
            }
        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }

    private void addGebouwKaartViews() {
        int x = 0;
        System.out.println("Aantal gebouwKaartViews: " + gebouwKaartViews.size());
        for (GebouwKaartView gebouwKaartView: gebouwKaartViews) {
            System.out.println(gebouwKaartView);
            gebouwKaartView.setLayoutX(x);
            this.getChildren().add(gebouwKaartView);
            x += 100;
        }
    }

    @Override
    public void modelChanged(HandRemote hand) throws RemoteException {
        System.out.println("Hand changed!");
        this.hand = hand;
    }

    private int calcRotation(int i, int totalLength) {
        // TODO: implement rotation calculation
        return 1;
    }
}
