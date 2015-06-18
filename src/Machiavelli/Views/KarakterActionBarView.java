package Machiavelli.Views;

import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Observers.KarakterObserver;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class KarakterActionBarView extends UnicastRemoteObject implements KarakterObserver {

    private Karakter karakter;
    private Pane pane;

    public KarakterActionBarView(Karakter karakter) throws RemoteException {
        this.karakter = karakter;
        this.pane = new Pane();
        this.pane.getChildren().addAll(this.createBackground(), this.createPortrait(), this.createNumber());
    }

    private Rectangle createBackground() {
        Rectangle karakterholder = new Rectangle(0, 0, 250, 250);
        karakterholder.setFill(Color.DIMGRAY);
        return karakterholder;
    }

    private ImageView createPortrait() {
        ImageView portretview = new ImageView();
        try {
             portretview = new ImageView(this.karakter.getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        portretview.setCache(true);
        portretview.setLayoutX(50);
        portretview.setLayoutY(50);
        return portretview;
    }

    private Pane createNumber() {
        StackPane numberPane = new StackPane();

        Circle circle = new Circle(30);
        circle = setKarakterTypeClass(circle);

        Text numberField = new Text();
        try {
            numberField = new Text(String.valueOf(karakter.getNummer()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        numberField.getStyleClass().add("karakter-nummer");

        numberPane.getChildren().addAll(circle, numberField);
        numberPane.setLayoutX(25);
        numberPane.setLayoutY(25);
        return numberPane;
    }

    private Circle setKarakterTypeClass(Circle circle) {
        try {
            switch (karakter.getType()) {
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


    @Override
    public void modelChanged(Karakter karakter) {
        this.karakter = karakter;
        // TODO: update view
    }

    public Pane getPane() {
        return this.pane;
    }
}
