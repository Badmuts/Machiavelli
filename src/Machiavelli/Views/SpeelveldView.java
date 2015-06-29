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

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class SpeelveldView extends UnicastRemoteObject implements SpeelveldObserver, PortemonneeOberserver, BeurtObserver, Serializable {

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

    public SpeelveldView(SpeelveldController speelveldcontroller, Speelveld speelveld, GebouwKaartController gebouwKaartController, SpelerRemote speler, BeurtController beurtController, BeurtRemote beurt) throws RemoteException {
//        super(1099);
        this.speelveld = speelveld;
        this.speler = speler;  
        this.beurtController = beurtController;
        this.beurt = beurt;
        
		    this.speelveldcontroller = speelveldcontroller;
        this.gebouwKaartController = gebouwKaartController;
        this.portemonnee = speler.getPortemonnee();   
        
//        this.beurt.addObserver(this);
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
        spelregelsButton.setOnAction(event -> new RaadplegenSpelregelsController().cmdWeergeefSpelregels());
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

    public boolean isDisabled() {
      // TODO Auto-generated method stub
      return disabled;
    }

    @Override
    public void setDisable(boolean disabled){
      this.disabled = disabled;
      
    }
    
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
    
    
    public void showKarakterMenu() {
      Platform.runLater(() -> {
        try {
          KarakterController karaktercontroller = new KarakterController(this.speler, "ronde");
          karaktercontroller.show();
        } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      });
    }
}
