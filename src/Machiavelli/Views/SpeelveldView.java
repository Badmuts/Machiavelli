package Machiavelli.Views;

import Machiavelli.Controllers.SpeelveldController;
import Machiavelli.Interfaces.Observers.SpeelveldObserver;
import Machiavelli.Interfaces.Remotes.SpeelveldRemote;
import Machiavelli.Machiavelli;
import Machiavelli.Models.Karakters.Magier;
import Machiavelli.Models.Speelveld;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SpeelveldView extends UnicastRemoteObject implements SpeelveldObserver {

	private SpeelveldController speelveldcontroller;
	private Speelveld speelveld;
	private Scene speelveldscene;
	private Stage stage = Machiavelli.getInstance().getStage();
    private HBox actionBar;
    private ButtonHolderActionBarView buttonHolderActionBarView;
    private HandActionBarView handActionBarView;
    private KarakterActionBarView karakterActionBarView;

    public SpeelveldView(SpeelveldController speelveldcontroller, Speelveld speelveld) throws RemoteException {
		this.speelveld = speelveld;
        System.out.println(this.speelveld);
		this.speelveldcontroller = speelveldcontroller;

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
            handActionBarView = new HandActionBarView(this.speelveld.getSpeler().getHand());
        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }

    private void createKarakterHolder() {
        karakterActionBarView = new KarakterActionBarView(new Magier());
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

	@Override
	public void modelChanged(SpeelveldRemote speelveld) throws RemoteException {
		// Doe iets?
	}

    private void createActionBar() {
        actionBar = new HBox(0);
        actionBar.getChildren().addAll(this.karakterActionBarView, this.handActionBarView, this.buttonHolderActionBarView);
        actionBar.getStyleClass().add("action-bar");
    }
}
