package Machiavelli.Views;

import Machiavelli.Controllers.GebouwKaartController;
import Machiavelli.Interfaces.Observers.SpelerObserver;
import Machiavelli.Interfaces.Observers.StadObserver;
import Machiavelli.Interfaces.Remotes.GebouwKaartRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Interfaces.Remotes.StadRemote;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class StadView extends UnicastRemoteObject implements StadObserver, SpelerObserver {
    private SpelerRemote speler;
    private GebouwKaartController gebouwKaartController;
    private StadRemote stad;
    private Pane pane;
    private ArrayList<GebouwKaartView> gebouwKaartViews = new ArrayList<>();
    private ImageView portretview;
    private StackPane numberPane;
    private Pane namePane;
    private Pane stadPane;

    public StadView(StadRemote stad, GebouwKaartController gebouwKaartController) throws RemoteException{
        this.stad = stad;
        this.speler = stad.getSpeler();
        this.gebouwKaartController = gebouwKaartController;
        this.pane = new StackPane();
        this.pane.setMaxSize(321, 312);

        this.stad.addObserver(this);
        this.speler.addObserver(this);

        this.buildGebouwKaartViewArray();
        this.createSpelerPortrait();
        this.createSpelerPortraitNumber();
        this.createNameField();
        this.createStad();

        this.pane.getChildren().addAll(portretview, numberPane, namePane, stadPane);
        StackPane.setAlignment(stadPane, Pos.TOP_CENTER);
    }

    private void createSpelerPortraitNumber() {
       numberPane = new StackPane();

        Circle circle = new Circle(20);
        circle = setKarakterTypeClass(circle);

        Text numberField = new Text();
        try {
            numberField = new Text(String.valueOf(this.speler.getKarakter().getNummer()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        numberField.getStyleClass().add("speler-nummer");

        numberPane.getChildren().addAll(circle, numberField);
        numberPane.setLayoutX(0);
        numberPane.setLayoutY(0);
    }

    private Circle setKarakterTypeClass(Circle circle) {
        try {
            switch (this.speler.getKarakter().getType()) {
                case COMMERCIEL:
                    circle.getStyleClass().add("type-commerciel");
                    break;
                case KERKELIJK:
                    circle.getStyleClass().add("type-kerkelijk");
                    break;
                case MILITAIR:
                    circle.getStyleClass().add("type-militair");
                    break;
                case MONUMENT:
                    circle.getStyleClass().add("type-monument");
                    break;
                default:
                    circle.getStyleClass().add("type-normaal");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return circle;
    }

    private void createNameField() {
        namePane = new Pane();
        try {
            Text name = new Text(this.speler.getKarakter().getNaam());
            name.getStyleClass().add("speler-naam");
            name.setWrappingWidth(250);
            name.setLayoutY(185);
            name.setTextAlignment(TextAlignment.CENTER);
            namePane.getChildren().add(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createSpelerPortrait() {
        Circle clip = new Circle(40);
        portretview = new ImageView();
        try {
            portretview = new ImageView(new Image(this.speler.getKarakter().getImage()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        portretview.setFitHeight(80);
        portretview.setFitWidth(80);
        portretview.setClip(clip);
        portretview.getStyleClass().add("speler-portrait");
        portretview.setCache(true);
    }

    private void buildGebouwKaartViewArray() throws RemoteException {
        for (GebouwKaartRemote gebouwKaartRemote: this.stad.getGebouwen()) {
            GebouwKaartView gebouwKaartView = new GebouwKaartView(this.gebouwKaartController, gebouwKaartRemote);
            gebouwKaartRemote.addObserver(gebouwKaartView); // Add view (observer) to remote
            this.gebouwKaartController.addView(gebouwKaartView); // Add view to controller
            this.gebouwKaartController.addModel(gebouwKaartRemote); // Add model to controller
            gebouwKaartView.view().setMaxSize(75, 100);
            gebouwKaartView.view().setPrefSize(75, 100);
            this.gebouwKaartViews.add(gebouwKaartView); // Add view to local gebouwKaartView[]
        }
    }

    private void createStad() {
        stadPane = new Pane();
        int x = 0; // X coordinaat (voor uitlijning)
        int totalWidth = 0;
        int index = 0;
        // Loop  door gebouwKaartViews en wijzig de X coordinaat.
        for (GebouwKaartView gebouwKaartView: gebouwKaartViews) {
            gebouwKaartView.view().setLayoutX(x); // Zet X coordinaat
            stadPane.getChildren().add(gebouwKaartView.view()); // Voeg view to aan Pane
            x += 130; // Verhoog X coordinaat met 100
            totalWidth += gebouwKaartView.view().getPrefWidth();
            index++;
        }
        stadPane.setMaxWidth(totalWidth);
    }

    public Pane getPane() {
        return this.pane;
    }

    @Override
    public void modelChanged(StadRemote stad) throws RemoteException {
        this.stad = stad;
        this.pane.getChildren().clear();
        this.pane.getChildren().addAll(portretview, numberPane, namePane, stadPane);
        StackPane.setAlignment(stadPane, Pos.TOP_CENTER);
    }

    @Override
    public void modelChanged(SpelerRemote speler) throws RemoteException {
        Platform.runLater(() -> {
            //if you change the UI, do it here !
            this.speler = speler;
            this.pane.getChildren().clear();
            this.pane.getChildren().addAll(portretview, numberPane, namePane, stadPane);
            StackPane.setAlignment(stadPane, Pos.TOP_CENTER);
        });
    }
}
