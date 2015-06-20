package Machiavelli.Views;

import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Observers.SpelerObserver;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Models.Speler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class KarakterActionBarView extends UnicastRemoteObject implements SpelerObserver {

    private Karakter karakter;
    private Pane pane;
    private SpelerRemote speler;

    public KarakterActionBarView(Karakter karakter, SpelerRemote speler) throws RemoteException {
        this.karakter = karakter;
        this.speler = speler;

        this.speler.addObserver(this);

        this.pane = new Pane();
        this.pane.getChildren().addAll(this.createBackground(), this.createPortrait(), this.createNumber(), this.createNameField());
    }

    private Rectangle createBackground() {
        Rectangle karakterholder = new Rectangle(0, 0, 250, 250);
        karakterholder.setFill(Color.rgb(57, 57, 57));
        return karakterholder;
    }

    private ImageView createPortrait() {
        Rectangle clip = new Rectangle(150, 150);
        clip.setArcHeight(20);
        clip.setArcWidth(20);
        ImageView portretview = new ImageView();
        try {
            portretview = new ImageView(new Image(this.karakter.getImage()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        portretview.setClip(clip);
        portretview.getStyleClass().add("karakter-portrait");
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

    private Pane createNameField() {
        Pane namePane = new Pane();
        try {
            Text name = new Text(this.karakter.getNaam());
            name.getStyleClass().add("karakter-naam");
            name.setWrappingWidth(250);
            name.setLayoutY(185);
            name.setTextAlignment(TextAlignment.CENTER);
            namePane.getChildren().add(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return namePane;
    }

    public Pane getPane() {
        return this.pane;
    }

    @Override
    public void modelChanged(SpelerRemote speler) throws RemoteException {
        this.speler = speler;
        this.karakter = this.speler.getKarakter();
        this.pane.getChildren().clear(); // Leeg het pane (de view)
        // Vul pane met nieuwe waardes
        this.pane.getChildren().addAll(this.createBackground(), this.createPortrait(), this.createNumber(), this.createNameField());
    }
}
