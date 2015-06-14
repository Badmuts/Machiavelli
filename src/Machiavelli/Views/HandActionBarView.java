package Machiavelli.Views;

import Machiavelli.Interfaces.Observers.HandObserver;
import Machiavelli.Interfaces.Remotes.HandRemote;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.rmi.RemoteException;

public class HandActionBarView extends Pane implements HandObserver {

    private HandRemote hand;

    public HandActionBarView() {
        super();
        Rectangle kaartholder = new Rectangle(0, 0, 840, 250);
        kaartholder.setFill(Color.GRAY);
        this.getChildren().addAll(kaartholder);
    }

    @Override
    public void modelChanged(HandRemote hand) throws RemoteException {
        this.hand = hand;
    }
}
