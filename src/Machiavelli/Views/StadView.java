package Machiavelli.Views;

import Machiavelli.Controllers.GebouwKaartController;
import Machiavelli.Interfaces.Observers.SpelerObserver;
import Machiavelli.Interfaces.Observers.StadObserver;
import Machiavelli.Interfaces.Remotes.GebouwKaartRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Interfaces.Remotes.StadRemote;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class StadView extends UnicastRemoteObject implements StadObserver, SpelerObserver {
    private SpelerRemote speler;
    private GebouwKaartController gebouwKaartController;
    private StadRemote stad;
    private Pane pane = new StackPane();
    private ArrayList<GebouwKaartView> gebouwKaartViews = new ArrayList<>();
    private StackPane numberPane;
    private Pane namePane;
    private FlowPane stadPane;
    private StackPane portretPane;

    public StadView(StadRemote stad, GebouwKaartController gebouwKaartController) throws RemoteException {
//        super(1099);
        this.stad = stad;
        this.speler = stad.getSpeler();
        this.gebouwKaartController = gebouwKaartController;

        this.stad.addObserver(this);
        this.speler.addObserver(this);

        this.pane.setCache(true);
        this.pane.setCacheShape(true);
        this.pane.setCacheHint(CacheHint.SPEED);

        this.buildGebouwKaartViewArray();
        this.createSpelerPortrait();
        this.createSpelerPortraitNumber();
        this.createNameField();
        this.createStad();

        this.pane.getChildren().addAll(portretPane, namePane, stadPane);
        StackPane.setAlignment(portretPane, Pos.TOP_CENTER);
        StackPane.setAlignment(namePane, Pos.CENTER);
        StackPane.setAlignment(stadPane, Pos.BOTTOM_CENTER);
    }

    private void createSpelerPortraitNumber() {
        numberPane = new StackPane();
        numberPane.setPrefSize(40, 40);
        numberPane.setMaxSize(40, 40);

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
        portretPane.getChildren().add(numberPane);
        StackPane.setAlignment(numberPane, Pos.TOP_LEFT);
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
            name.setWrappingWidth(400);
            name.setLayoutY(95);
            name.setTextAlignment(TextAlignment.CENTER);
            namePane.getChildren().add(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createSpelerPortrait() {
        portretPane = new StackPane();
        portretPane.setPrefSize(95, 95);
        portretPane.setMaxSize(95, 95);
        Rectangle clip = new Rectangle(80, 80);
        clip.setArcWidth(80);
        clip.setArcHeight(80);
        ImageView portretview = new ImageView();
        try {
            portretview = new ImageView(new Image(this.speler.getKarakter().getImage()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        clip.setFill(Color.BLACK);
        clip.getStyleClass().add("speler-portait-clip");
        portretview.setFitHeight(80);
        portretview.setFitWidth(80);
        portretview.setClip(clip);
        portretview.getStyleClass().add("speler-portrait");
        portretview.setCache(true);
        portretPane.getChildren().add(portretview);
        StackPane.setAlignment(portretview, Pos.CENTER);
    }

    private void buildGebouwKaartViewArray() throws RemoteException {
        for (GebouwKaartRemote gebouwKaartRemote: this.stad.getGebouwen()) {
            GebouwKaartView gebouwKaartView = new GebouwKaartView(this.gebouwKaartController, gebouwKaartRemote, 65, 90);
            gebouwKaartRemote.addObserver(gebouwKaartView); // Add view (observer) to remote
            this.gebouwKaartController.addView(gebouwKaartView); // Add view to controller
            this.gebouwKaartController.addModel(gebouwKaartRemote); // Add model to controller
            this.gebouwKaartViews.add(gebouwKaartView); // Add view to local gebouwKaartView[]
        }
    }

    private void createStad() {
        stadPane = new FlowPane();
        stadPane.setPadding(new Insets(125, 0, 5, 50));
        stadPane.setVgap(-40);
        stadPane.setHgap(-15);
        stadPane.setPrefWrapLength(360);
        // Loop  door gebouwKaartViews en voeg ze toe aan het FlowPane
        for (GebouwKaartView gebouwKaartView: gebouwKaartViews) {
            stadPane.getChildren().add(gebouwKaartView.view()); // Voeg view to aan Pane
        }
    }

    public Pane getPane() {
        return this.pane;
    }

    @Override
    public void modelChanged(StadRemote stad) throws RemoteException {
        Platform.runLater(() -> {
            try {
                this.stad = stad;
                this.pane.getChildren().clear();
                this.gebouwKaartViews.clear();
                this.buildGebouwKaartViewArray();
                this.createStad();
                this.pane.getChildren().addAll(portretPane, namePane, stadPane);
                StackPane.setAlignment(portretPane, Pos.TOP_CENTER);
                StackPane.setAlignment(namePane, Pos.CENTER);
                StackPane.setAlignment(stadPane, Pos.BOTTOM_CENTER);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void modelChanged(SpelerRemote speler) throws RemoteException {
        Platform.runLater(() -> {
            //if you change the UI, do it here !
            this.speler = speler;
            this.pane.getChildren().clear();
            this.createSpelerPortrait();
            this.createSpelerPortraitNumber();
            this.createNameField();
            this.pane.getChildren().addAll(portretPane, namePane, stadPane);
            StackPane.setAlignment(portretPane, Pos.TOP_CENTER);
            StackPane.setAlignment(namePane, Pos.CENTER);
            StackPane.setAlignment(stadPane, Pos.BOTTOM_CENTER);
        });
    }

//    private void updateView() throws RemoteException {
//        this.pane.getChildren().clear();
//        this.gebouwKaartViews.clear();
//        this.buildGebouwKaartViewArray();
//        this.createSpelerPortrait();
//        this.createSpelerPortraitNumber();
//        this.createNameField();
//        this.createStad();
//        this.pane.getChildren().addAll(portretPane, namePane, stadPane);
//        StackPane.setAlignment(portretPane, Pos.TOP_CENTER);
//        StackPane.setAlignment(namePane, Pos.CENTER);
//        StackPane.setAlignment(stadPane, Pos.BOTTOM_CENTER);
//    }
}
