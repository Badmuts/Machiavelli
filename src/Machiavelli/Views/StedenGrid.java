package Machiavelli.Views;

import Machiavelli.Controllers.GebouwKaartController;
import Machiavelli.Interfaces.Observers.SpelObserver;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
/**
 * Deze view maakt een GridPane aan en plaatst daar StadViews in.
 *
 * Deze klasse update wanneer Spel wijzigd.
 *
 * Deze klasse extends UnicastRemoteObject en kan op die manier ontvangen van
 * andere Remote objecten.
 *
 * @author Daan Rosbergen
 * @version 1.0
 */
public class StedenGrid extends UnicastRemoteObject implements SpelObserver {

    private ArrayList<SpelerRemote> spelers;
    private final GebouwKaartController gebouwKaartController;
    private GridPane pane;

    /**
     *
     * @param spel
     * @param gebouwKaartController
     * @throws RemoteException
     */
    public StedenGrid(SpelRemote spel, GebouwKaartController gebouwKaartController) throws RemoteException {
        pane = new GridPane();
        pane.setPadding(new Insets(0, 0, 0, 0));
        this.gebouwKaartController = gebouwKaartController;
        this.spelers = spel.getSpelers();
        spel.addObserver(this);
        this.createSteden();
    }

    /**
     * Maakt voor elke stad een nieuwe StadView en plaatst deze in
     * het GridPane.
     *
     * @throws RemoteException
     */
    private void createSteden() throws RemoteException {
        int rowIndex = 1;
        int columnIndex = 1;
        for (SpelerRemote speler: spelers) {
            pane.add(new StadView(speler.getStad(), this.gebouwKaartController).getPane(), columnIndex, rowIndex);
            if (columnIndex >= 4) {
                columnIndex = 1;
                rowIndex++;
            } else {
                columnIndex++;
            }
        }
    }

    /**
     * Methode om opgebouwde view op te halen.
     *
     * @return Pane met de uiteindelijke StedenGrid
     */
    public Pane getPane() {
        return pane;
    }

    /**
     * Update view als Spel wijzigd.
     *
     * @param spel Nieuw Spel model
     * @throws RemoteException
     */
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
