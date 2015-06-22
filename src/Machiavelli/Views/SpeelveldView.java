package Machiavelli.Views;

import Machiavelli.Controllers.GebouwKaartController;
import Machiavelli.Controllers.RaadplegenSpelregelsController;
import Machiavelli.Controllers.SpeelveldController;
import Machiavelli.Interfaces.Observers.PortemonneeOberserver;
import Machiavelli.Interfaces.Observers.SpeelveldObserver;
import Machiavelli.Interfaces.Remotes.PortemonneeRemote;
import Machiavelli.Interfaces.Remotes.SpeelveldRemote;
import Machiavelli.Machiavelli;
import Machiavelli.Models.Speelveld;
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

public class SpeelveldView extends UnicastRemoteObject implements SpeelveldObserver, PortemonneeOberserver {

    private GebouwKaartController gebouwKaartController;
    private SpeelveldController speelveldcontroller;
	private Speelveld speelveld;
	private Scene speelveldscene;
	private Stage stage = Machiavelli.getInstance().getStage();
    private AnchorPane actionBar;
    private ButtonHolderActionBarView buttonHolderActionBarView;
    private HandActionBarView handActionBarView;
    private KarakterActionBarView karakterActionBarView;
    private PortemonneeRemote portemonnee;
    private Text portemonneeView;
    private Pane topBar;

    public SpeelveldView(SpeelveldController speelveldcontroller, Speelveld speelveld, GebouwKaartController gebouwKaartController) throws RemoteException {
		this.speelveld = speelveld;
		this.speelveldcontroller = speelveldcontroller;
        this.gebouwKaartController = gebouwKaartController;
        this.portemonnee = this.speelveld.getSpeler().getPortemonnee();

        this.portemonnee.addObserver(this);

        this.createKarakterHolder();
        this.createKaartHolder();
        this.createButtonHolder();
        this.createActionBar();
        this.createPortemonnee();
        this.createTopStatusBar();
//        this.createStedenHolder();

        BorderPane speelveldpane = new BorderPane();
        speelveldpane.setBottom(this.actionBar);
        speelveldpane.setTop(topBar);
        speelveldpane.getStylesheets().add("Machiavelli/Resources/style.css");
        speelveldpane.getStyleClass().add("speelveld");
        speelveldscene = new Scene(speelveldpane, 1440, 900);

		this.show();
	}

    private void createTopStatusBar() {
        this.topBar = new Pane();

        Image spelregelsbg = new Image("Machiavelli/Resources/SpelregelsBorder.png");
        ImageView iv = new ImageView(spelregelsbg);
        iv.setCache(true);
        iv.setFitWidth(205);
        iv.setFitHeight(74);

        Button spelregelsButton = new Button("Spelregels");
        spelregelsButton.setOnAction(event -> {
            new RaadplegenSpelregelsController().cmdWeergeefSpelregels();
        });
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

    private void createButtonHolder() {
        try {
            buttonHolderActionBarView = new ButtonHolderActionBarView(this.speelveldcontroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createKaartHolder() {
        try {
            handActionBarView = new HandActionBarView(this.speelveld.getSpeler().getHand(), this.gebouwKaartController);
        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }

    private void createKarakterHolder() {
        try {
            karakterActionBarView = new KarakterActionBarView(this.speelveld.getSpeler().getKarakter(), this.speelveld.getSpeler());
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

    @Override
    public void modelChanged(SpeelveldRemote speelveld) throws RemoteException {
        // TODO: update view
    }

    @Override
    public void modelChanged(PortemonneeRemote portemonnee) throws RemoteException {
        this.portemonnee = portemonnee;
        this.portemonneeView.setText(String.valueOf(portemonnee.getGoudMunten()));
    }
}
