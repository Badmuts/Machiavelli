package Machiavelli.Views;

import Machiavelli.Controllers.GebouwKaartController;
import Machiavelli.Interfaces.Observers.SpelObserver;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Models.Speler;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class StedenGrid extends UnicastRemoteObject implements SpelObserver {

    private ArrayList<Speler> spelers;
    private final GebouwKaartController gebouwKaartController;
    private GridPane pane;

    public StedenGrid(SpelRemote spel, GebouwKaartController gebouwKaartController) throws RemoteException {
        pane = new GridPane();
        this.gebouwKaartController = gebouwKaartController;
        this.spelers = spel.getSpelers();
        spel.addObserver(this);
        this.createSteden();
    }

    private void createSteden() throws RemoteException {
        int rowIndex = 1;
        int columnIndex = 1;
        for (Speler speler: spelers) {
            pane.add(new StadView(speler.getStad(), this.gebouwKaartController).getPane(), columnIndex, rowIndex);
            if (columnIndex >= 4 || rowIndex >= 2) {
                columnIndex = 1;
                rowIndex = 1;
            } else {
                columnIndex++;
                rowIndex++;
            }
        }
    }

    public Pane getPane() {
        return pane;
    }

    @Override
    public void modelChanged(SpelRemote spel) throws RemoteException {
        Platform.runLater(() -> {
            //if you change the UI, do it here !
            try {
                this.spelers = spel.getSpelers();
                pane.getChildren().clear();
                this.createSteden();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
