package Machiavelli.Views;

import Machiavelli.Controllers.GebouwKaartController;
import Machiavelli.Interfaces.Observers.GebouwKaartObserver;
import Machiavelli.Models.GebouwKaart;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GebouwKaartView extends UnicastRemoteObject implements GebouwKaartObserver, Serializable {

    private GebouwKaartController gebouwKaartController;
    private GebouwKaart gebouwKaart;
    private StackPane gebouwKaartView;

    public GebouwKaartView(GebouwKaartController gebouwkaartController, GebouwKaart gebouwKaart) throws RemoteException {
//        super();
        this.gebouwKaart = gebouwKaart;
        this.gebouwKaartController = gebouwkaartController;
        this.gebouwKaartView = new StackPane();
        this.gebouwKaartView.getChildren().addAll(createImageView(), createScoreView(), createNameField());
    }

    private ImageView createImageView() {
        ImageView gebouwKaartImage = new ImageView();
        try {
            gebouwKaartImage = new ImageView(new Image(gebouwKaart.getImage()));
            gebouwKaartImage.setFitWidth(200);
            gebouwKaartImage.setFitHeight(300);
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        return gebouwKaartImage;
    }

    private Pane createScoreView() {
        Pane gebouwScoreView = new Pane();
        try {
            Circle circle = new Circle(30);
            circle = setGebouwTypeClass(circle);

            Text gebouwScore = new Text(String.valueOf(gebouwKaart.getKosten()));
            gebouwScore.getStyleClass().add("gebouwkaart-score");

            gebouwScoreView.getChildren().addAll(circle, gebouwScore);
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        return gebouwScoreView;
    }

    private Circle setGebouwTypeClass(Circle circle) {
        try {
            switch (gebouwKaart.getType()) {
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
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        return circle;
    }

    private StackPane createNameField() {
        StackPane gebouwKaartName = new StackPane();
        Rectangle background = new Rectangle(200, 75);
        background.setFill(Color.rgb(0, 0, 0, 0.7));
        try {
            Text name = new Text(gebouwKaart.getNaam());
            name.getStyleClass().add("gebouwkaart-naam");
            gebouwKaartName.getChildren().addAll(background, name);
            StackPane.setAlignment(name, Pos.CENTER);
            StackPane.setAlignment(background, Pos.CENTER);
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        return gebouwKaartName;
    }

    public void modelChanged(GebouwKaart gebouwKaart) throws RemoteException {
        this.gebouwKaart = gebouwKaart;
    }

    public GebouwKaartController gebouwKaartController() {
        return this.gebouwKaartController;
    }

    public GebouwKaart getModel() {
        return this.gebouwKaart;
    }

    public StackPane view() {
        return this.gebouwKaartView;
    }

}
