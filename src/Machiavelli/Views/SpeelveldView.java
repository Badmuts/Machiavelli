package Machiavelli.Views;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Machiavelli.Controllers.GebouwKaartController;
import Machiavelli.Controllers.InkomstenController;
import Machiavelli.Controllers.KarakterController;
import Machiavelli.Controllers.MeldingController;
import Machiavelli.Controllers.RaadplegenSpelregelsController;
import Machiavelli.Controllers.SpeelveldController;
import Machiavelli.Interfaces.Observers.PortemonneeOberserver;
import Machiavelli.Interfaces.Observers.SpeelveldObserver;
import Machiavelli.Interfaces.Remotes.PortemonneeRemote;
import Machiavelli.Interfaces.Remotes.SpeelveldRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Machiavelli;
import Machiavelli.Models.Speelveld;
import Machiavelli.Models.Speler;
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
import Machiavelli.Machiavelli;
import Machiavelli.Controllers.SpeelveldController;
import Machiavelli.Interfaces.Observers.SpeelveldObserver;
import Machiavelli.Interfaces.Remotes.SpeelveldRemote;
import Machiavelli.Models.Speelveld;
import Machiavelli.Models.Karakters.Magier;

public class SpeelveldView extends UnicastRemoteObject implements SpeelveldObserver, PortemonneeOberserver {

    private SpelerRemote speler;
    private GebouwKaartController gebouwKaartController;
    private SpeelveldController speelveldcontroller;
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

    public SpeelveldView(SpeelveldController speelveldcontroller, Speelveld speelveld, GebouwKaartController gebouwKaartController, SpelerRemote speler) throws RemoteException {
		this.speelveld = speelveld;
        this.speler = speler;
		this.speelveldcontroller = speelveldcontroller;
        this.gebouwKaartController = gebouwKaartController;
        this.portemonnee = speler.getPortemonnee();

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

		this.show();
	}

    private void createStedenHolder() {
        try {
            this.steden = new StedenGrid(this.speelveldcontroller.getSpel(), this.gebouwKaartController).getPane();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            this.speler.getHand();
            handActionBarView = new HandActionBarView(this.speler.getHand(), this.gebouwKaartController);
        } catch (Exception re) {
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
}
