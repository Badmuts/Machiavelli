package Machiavelli.Views;

import Machiavelli.Interfaces.Karakter;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class KarakterActionBarView extends Pane {

    private Karakter karakter;

    public KarakterActionBarView(Karakter karakter) {
        super();
        this.karakter = karakter;
        this.getChildren().addAll(this.createBackground(), this.createPortrait(), this.createNumber());
    }

    private Rectangle createBackground() {
        Rectangle karakterholder = new Rectangle(0, 0, 250, 250);
        karakterholder.setFill(Color.DIMGRAY);
        return karakterholder;
    }

    private ImageView createPortrait() {
        ImageView portretview = new ImageView(this.karakter.getImage());
        portretview.setCache(true);
        portretview.setLayoutX(50);
        portretview.setLayoutY(50);
        return portretview;
    }

    private Pane createNumber() {
        StackPane numberPane = new StackPane();

        Circle circle = new Circle(30);
        circle = setKarakterTypeClass(circle);

        Text numberField = new Text(String.valueOf(karakter.getNummer()));
        numberField.getStyleClass().add("karakter-nummer");

        numberPane.getChildren().addAll(circle, numberField);
        numberPane.setLayoutX(25);
        numberPane.setLayoutY(25);
        return numberPane;
    }

    private Circle setKarakterTypeClass(Circle circle) {
        switch (karakter.getType()) {
            case COMMERCIEL:
                circle.getStyleClass().add("karakter-commerciel");
                break;
            case KERKELIJK:
                circle.getStyleClass().add("karakter-kerkelijk");
                break;
            case MILITAIR:
                circle.getStyleClass().add("karakter-militair");
                break;
            case MONUMENT:
                circle.getStyleClass().add("karakter-monument");
                break;
            default:
                circle.getStyleClass().add("karakter-normaal");
        }
        return circle;
    }


}
