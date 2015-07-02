package Machiavelli.Views;

import Machiavelli.Controllers.*;
import Machiavelli.Interfaces.Observers.BeurtObserver;
import Machiavelli.Interfaces.Observers.PortemonneeOberserver;
import Machiavelli.Interfaces.Observers.SpeelveldObserver;
import Machiavelli.Interfaces.Remotes.BeurtRemote;
import Machiavelli.Interfaces.Remotes.PortemonneeRemote;
import Machiavelli.Interfaces.Remotes.SpeelveldRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Machiavelli;
import Machiavelli.Models.Speelveld;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * View voor het Speelveld
 *
 * Deze klasse update wanneer Speelveld, Portemonnee of Beurt wijzigd.
 *
 * Deze klasse extends UnicastRemoteObject en kan op die manier ontvangen van
 * andere Remote objecten.
 *
 * @author Daan Rosbergen
 * @version 1.0
 */
public class SpeelveldView extends UnicastRemoteObject implements SpeelveldObserver,
        PortemonneeOberserver, BeurtObserver {
    private SpelerRemote speler;
    private BeurtRemote beurt;
    private GebouwKaartController gebouwKaartController;
    private SpeelveldController speelveldcontroller;
    private BeurtController beurtController;
    private Speelveld speelveld;
    private Scene speelveldscene;
    private Pane speelveldpane;
    private Stage stage = Machiavelli.getInstance().getStage();
    private AnchorPane actionBar;
    private ButtonHolderActionBarView buttonHolderActionBarView;
    private HandActionBarView handActionBarView;
    private KarakterActionBarView karakterActionBarView;
    private PortemonneeRemote portemonnee;
    private Text portemonneeView;
    private Pane topBar;
    private Pane steden;
    private boolean disabled;

    /**
     *
     * @param speelveldcontroller
     * @param speelveld
     * @param gebouwKaartController
     * @param speler
     * @param beurtController
     * @param beurt
     * @throws RemoteException
     */
    public SpeelveldView(SpeelveldController speelveldcontroller, Speelveld speelveld,
            GebouwKaartController gebouwKaartController, SpelerRemote speler,
            BeurtController beurtController, BeurtRemote beurt) throws RemoteException {
        this.speelveld = speelveld;

        this.speler = speler;
        this.beurtController = beurtController;
        this.beurt = beurt;

        this.speelveldcontroller = speelveldcontroller;
        this.gebouwKaartController = gebouwKaartController;
        this.portemonnee = speler.getPortemonnee();

        this.beurt.addObserver(this);
        this.portemonnee.addObserver(this);

        this.createStedenHolder();
        this.createPortemonnee();
        this.createTopStatusBar();
        this.createKarakterHolder();
        this.createKaartHolder();
        this.createButtonHolder();
        this.createActionBar();

        BorderPane speelveldpane = new BorderPane();
        speelveldpane.setPrefSize(1440, 900);
        speelveldpane.setCenter(steden);
        speelveldpane.setTop(topBar);
        speelveldpane.setBottom(this.actionBar);
        speelveldpane.getStylesheets().add("Machiavelli/Resources/style.css");
        speelveldpane.getStyleClass().add("speelveld");
        Pane container = new Pane();
        container.setMinSize(1440, 900);
        container.setPrefSize(1440, 900);
        container.getChildren().add(speelveldpane);
        speelveldscene = new Scene(container, 1440, 900);

        setDisable(true);

        this.show();
    }

    /**
     * Voegt StedenGrid toe aan view.
     */
    private void createStedenHolder() {
        try {
            this.steden =
                    new StedenGrid(this.speelveldcontroller.getSpel(), this.gebouwKaartController)
                            .getPane();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Maakt de TopStatusBar aan met portemonnee en spelregels button.
     */
    private void createTopStatusBar() {
        this.topBar = new Pane();

        Image spelregelsbg = new Image("Machiavelli/Resources/SpelregelsBorder.png");
        ImageView iv = new ImageView(spelregelsbg);
        iv.setCache(true);
        iv.setFitWidth(205);
        iv.setFitHeight(74);

        Button spelregelsButton = new Button("Spelregels");
        spelregelsButton.setOnAction(event -> new RaadplegenSpelregelsController()
                .cmdWeergeefSpelregels());
        spelregelsButton.setLayoutY(10);
        spelregelsButton.setLayoutX(10);
        spelregelsButton.getStyleClass().add("button-primary");

        Image topStatusImage = new Image("Machiavelli/Resources/top-status.png");
        ImageView topStatus = new ImageView(topStatusImage);
        topStatus.setCache(true);
        topStatus.setFitWidth(307);
        topStatus.setFitHeight(74);
        topStatus.setLayoutX(566.5);

        topBar.getChildren().addAll(iv, spelregelsButton, topStatus, portemonneeView);
    }

    /**
     * Maakt portemonne view aan
     */
    private void createPortemonnee() {
        this.portemonneeView = new Text();
        try {
            String munten = String.valueOf(this.portemonnee.getGoudMunten());
            this.portemonneeView.setText(munten);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.portemonneeView.getStyleClass().add("portemonnee");
    }

    /**
     * Maakt ButtonHolderActionBarView aan.
     */
    private void createButtonHolder() {
        try {
            buttonHolderActionBarView = new ButtonHolderActionBarView(this.speelveldcontroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Maakt HandActionBarView aan.
     */
    private void createKaartHolder() {
        try {
            this.speler.getHand();
            handActionBarView =
                    new HandActionBarView(this.speler.getHand(), this.gebouwKaartController);
        } catch (Exception re) {
            re.printStackTrace();
        }
    }

    /**
     * Maakt KarakterActionBarView aan.
     */
    private void createKarakterHolder() {
        try {
            karakterActionBarView =
                    new KarakterActionBarView(this.speelveld.getSpeler().getKarakter(),
                            this.speelveld.getSpeler());
        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }

    /**
     * Methode om view te tonen.
     */
    public void show() {
        stage.setScene(speelveldscene);
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * Maakt de ActionBarView met daarin:
     *  - KarakterActionBarView
     *  - HandActionBarView
     *  - ButtonHolderActionBarView
     */
    private void createActionBar() {
        actionBar = new AnchorPane();
        actionBar.getChildren().add(this.karakterActionBarView.getPane());
        actionBar.getChildren().add(this.handActionBarView.getPane());
        actionBar.getChildren().add(this.buttonHolderActionBarView.getPane());
        AnchorPane.setBottomAnchor(this.karakterActionBarView.getPane(), 0.0);
        AnchorPane.setLeftAnchor(this.karakterActionBarView.getPane(), 0.0);
        AnchorPane.setBottomAnchor(this.handActionBarView.getPane(), 0.0);
        AnchorPane.setBottomAnchor(this.buttonHolderActionBarView.getPane(), 0.0);
        AnchorPane.setRightAnchor(this.buttonHolderActionBarView.getPane(), 0.0);
        actionBar.getStyleClass().add("action-bar");
    }

    /**
     * Update view als Speelveld wijzigd.
     *
     * @param speelveld model is changed.
     * @throws RemoteException
     */
    @Override
    public void modelChanged(SpeelveldRemote speelveld) throws RemoteException {
        // TODO: update view
    }

    /**
     * Update view als Portemonnee wijzigd.
     *
     * @param portemonnee Nieuw Portemonnee model
     * @throws RemoteException
     */
    @Override
    public void modelChanged(PortemonneeRemote portemonnee) throws RemoteException {
        Platform.runLater(() -> {
            System.out.println("Portemonnee view changed!");
            try {
                this.portemonnee = portemonnee;
                this.portemonneeView.setText(String.valueOf(portemonnee.getGoudMunten()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Update view als Beurt wijzigd.
     *
     * @param beurt Nieuw Beurt model.
     * @throws RemoteException
     */
    @Override
    public void modelChanged(BeurtRemote beurt) throws RemoteException {
        // TODO Auto-generated method stub
        Platform.runLater(() -> {
            System.out.println("Beurt Model changed");
            buttonHolderActionBarView.getBouwButton().setDisable(isDisabled());
            buttonHolderActionBarView.getEigenschapButton().setDisable(isDisabled());
            buttonHolderActionBarView.getGoudbutton().setDisable(isDisabled());
            buttonHolderActionBarView.getEindeBeurtButton().setDisable(isDisabled());
        });
    }

    /**
     * Methode van BeurtObserver
     *
     * @return isDisabled
     */
    public boolean isDisabled() {
        // TODO Auto-generated method stub
        return disabled;
    }

    /**
     * Methode van BeurtObserver
     *
     * @param disabled
     */
    @Override
    public void setDisable(boolean disabled) {
        this.disabled = disabled;

    }

    /**
     * Methode om InkomstenView te tonen.
     */
    public void showInkomsten() {
        Platform.runLater(() -> {
            try {
                InkomstenController inkomstenController = new InkomstenController(this.speler);
                inkomstenController.show();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    /**
     * Methode om KarakterKiezenView te tonen.
     * @throws RemoteException
     */
    @Override
    public void showKarakterMenu() throws RemoteException {
        // TODO Auto-generated method stub
        Platform.runLater(() -> {
            try {
                KarakterController karaktercontroller =
                        new KarakterController(this.speler, "ronde");
                karaktercontroller.show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }
}
