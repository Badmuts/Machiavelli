package Machiavelli.Views;

import Machiavelli.Controllers.GebouwKaartController;
import Machiavelli.Controllers.SpeelveldController;
import Machiavelli.Interfaces.Observers.SpeelveldObserver;
import Machiavelli.Interfaces.Remotes.SpeelveldRemote;
import Machiavelli.Machiavelli;
import Machiavelli.Models.Karakters.Magier;
import Machiavelli.Models.Speelveld;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SpeelveldView extends UnicastRemoteObject implements SpeelveldObserver {

    private GebouwKaartController gebouwKaartController;
    private SpeelveldController speelveldcontroller;
	private Speelveld speelveld;
	private Scene speelveldscene;
	private Stage stage = Machiavelli.getInstance().getStage();
    private GridPane actionBar;
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

        Image spelregelsbg = new Image("Machiavelli/Resources/SpelregelsBorder.png");
        ImageView iv = new ImageView(spelregelsbg);
        iv.setCache(true);
        iv.setFitWidth(200);

        BorderPane speelveldpane = new BorderPane();
        speelveldpane.setBottom(this.actionBar);
        speelveldpane.setTop(iv);
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
            karakterActionBarView = new KarakterActionBarView(new Magier());
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

	public Button getExitButton() {
        return buttonHolderActionBarView.getExitbutton();
	}
	
	public Button getEindeButton() {
		return buttonHolderActionBarView.getEindeBeurtButton();
	}
	
	public Button getBouwButton() {
		return buttonHolderActionBarView.getBouwButton();
	}
	
	public Button getOpslaanButton() {
		return buttonHolderActionBarView.getOpslaanButton();
	}
	
	public Button getEigenschapButton() {
		return buttonHolderActionBarView.getEigenschapButton();
	}
	
	public Button getGoudButton() {
		return buttonHolderActionBarView.getGoudbutton();
	}

    private void createActionBar() {
        actionBar = new GridPane();
        actionBar.add(this.karakterActionBarView.getPane(), 1, 1);
        actionBar.add(this.handActionBarView.getPane(), 2, 1);
        actionBar.add(this.buttonHolderActionBarView, 3, 1);
        actionBar.getStyleClass().add("action-bar");
    }

    @Override
    public void modelChanged(SpeelveldRemote speelveld) throws RemoteException {
        // TODO: update view
    }
}
