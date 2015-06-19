package Machiavelli.Views;

import Machiavelli.Controllers.GebouwKaartController;
import Machiavelli.Controllers.SpeelveldController;
import Machiavelli.Interfaces.Observers.SpeelveldObserver;
import Machiavelli.Machiavelli;
import Machiavelli.Models.Karakters.Koopman;
import Machiavelli.Models.Speelveld;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SpeelveldView extends UnicastRemoteObject implements SpeelveldObserver {

    private GebouwKaartController gebouwKaartController;
    private SpeelveldController speelveldcontroller;
	private Speelveld speelveld;
	private Scene speelveldscene;
	private Stage stage = Machiavelli.getInstance().getStage();
    private HBox actionBar;
    private ButtonHolderActionBarView buttonHolderActionBarView;
    private HandActionBarView handActionBarView;
    private KarakterActionBarView karakterActionBarView;

    public SpeelveldView(SpeelveldController speelveldcontroller, Speelveld speelveld, GebouwKaartController gebouwKaartController) throws RemoteException {
		this.speelveld = speelveld;
		this.speelveldcontroller = speelveldcontroller;
        this.gebouwKaartController = gebouwKaartController;

        this.createKarakterHolder();
        this.createKaartHolder();
        this.createButtonHolder();
        this.createActionBar();

        Pane topBar = new Pane();

        Image spelregelsbg = new Image("Machiavelli/Resources/SpelregelsBorder.png");
        ImageView iv = new ImageView(spelregelsbg);
        iv.setCache(true);
        iv.setFitWidth(205);
        iv.setFitHeight(74);

        Image topStatusImage = new Image("Machiavelli/Resources/top-status.png");
        ImageView topStatus = new ImageView(topStatusImage);
        topStatus.setCache(true);
        topStatus.setFitWidth(307);
        topStatus.setFitHeight(74);
        topStatus.setLayoutX(566.5);

        topBar.getChildren().addAll(iv, topStatus);

        BorderPane speelveldpane = new BorderPane();
        speelveldpane.setBottom(this.actionBar);
        speelveldpane.setTop(topBar);
        speelveldpane.getStylesheets().add("Machiavelli/Resources/style.css");
        speelveldpane.getStyleClass().add("speelveld");
        speelveldscene = new Scene(speelveldpane, 1440, 900);

		this.show();
	}

    private void createButtonHolder() {
        buttonHolderActionBarView = new ButtonHolderActionBarView();
    }

    private void createKaartHolder() {
        try {
            handActionBarView = new HandActionBarView(this.speelveld.getSpeler().getHand(), this.gebouwKaartController);
        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }

    private void createKarakterHolder() {
        try {                                              // TESTING ONLY
            karakterActionBarView = new KarakterActionBarView(new Koopman());
        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }

	public void show(){
		stage.setScene(speelveldscene);
        stage.centerOnScreen();
		stage.show();
	}

	public Button getSpelregels() {
		return buttonHolderActionBarView.getSpelregels();
	}

	public Button getExitButton(){
        return buttonHolderActionBarView.getExitbutton();
	}

	public void modelChanged(Speelveld speelveld) throws RemoteException {
		// Doe iets?
	}

    private void createActionBar() {
        actionBar = new HBox(0);
        actionBar.getChildren().addAll(this.karakterActionBarView, this.handActionBarView, this.buttonHolderActionBarView);
        actionBar.getStyleClass().add("action-bar");
    }
}
