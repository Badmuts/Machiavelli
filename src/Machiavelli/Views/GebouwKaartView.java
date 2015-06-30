package Machiavelli.Views;

import Machiavelli.Controllers.GebouwKaartController;
import Machiavelli.Interfaces.Observers.GebouwKaartObserver;
import Machiavelli.Interfaces.Remotes.GebouwKaartRemote;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.CacheHint;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Deze view maakt een StackPane aan en plaatst daar de afbeelding van de
 * gebouwkaart in, plaatst de waarde van de kaart over de afbeelding en
 * plaatst de naam van de kaart over de afbeelding.
 *
 * Deze klasse update wanneer Gebouwkaart wijzigd en schakelt buttons
 * naar behoren uit.
 *
 * Deze klasse extends UnicastRemoteObject en kan op die manier ontvangen van
 * andere Remote objecten.
 *
 * @author Daan Rosbergen
 * @version 1.0
 */
public class GebouwKaartView extends UnicastRemoteObject implements GebouwKaartObserver {

    private int height;
    private int width;
    private GebouwKaartController gebouwKaartController;
    private GebouwKaartRemote gebouwKaart;
    private StackPane gebouwKaartView;

    /**
     *
     * @param gebouwkaartController Controller voor GebouwKaart
     * @param gebouwKaart           Model van GebouwKaart
     * @throws RemoteException
     */
    public GebouwKaartView(GebouwKaartController gebouwkaartController, GebouwKaartRemote gebouwKaart) throws RemoteException {
        this.gebouwKaart = gebouwKaart;
        this.gebouwKaartController = gebouwkaartController;
        this.gebouwKaartView = new StackPane();
        this.gebouwKaartView.getChildren().addAll(createImageView(), createScoreView(), createNameField());
        this.gebouwKaartView.setPrefSize(150, 250);
        this.gebouwKaartView.setCache(true);
        this.gebouwKaartView.setCacheShape(true);
        this.gebouwKaartView.setCacheHint(CacheHint.SPEED);
        this.addClickHandler();
    }

    /**
     *
     * @param gebouwkaartController Controller voor GebouwKaart
     * @param gebouwKaart           Model van GebouwKaart
     * @param width                 Breedte van view
     * @param height                Hoogte van view
     * @throws RemoteException
     */
    public GebouwKaartView(GebouwKaartController gebouwkaartController, GebouwKaartRemote gebouwKaart, int width, int height) throws RemoteException {
        this.gebouwKaart = gebouwKaart;
        this.width = width;
        this.height = height;
        this.gebouwKaartController = gebouwkaartController;
        this.gebouwKaartView = new StackPane();
        this.gebouwKaartView.getChildren().addAll(createImageView(), createScoreView(), createNameField());
        this.gebouwKaartView.setPrefSize(width, height);
        this.addClickHandler();
    }

    /**
     * Voeg een klik event toe aan de GebouwKaartView. Wanneer deze actief is word
     * deze op actief gezet in de GebouwKaartController.
     */
    private void addClickHandler() {
        this.gebouwKaartView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            StackPane view = (StackPane) event.getSource();
            if (view.getStyleClass().contains("gebouwkaart-active")) {
                // Gebouwkaart is gedeselecteerd
                view.getStyleClass().remove("gebouwkaart-active");
                this.gebouwKaartController.removeActiveCard(this.gebouwKaart);
                final Timeline timeline = new Timeline();
                timeline.setAutoReverse(true);
                final KeyValue kv = new KeyValue(view.layoutYProperty(), 0, Interpolator.EASE_IN);
                final KeyFrame kf = new KeyFrame(Duration.millis(125), kv);
                timeline.getKeyFrames().add(kf);
                timeline.play();
            } else {
                // Gebouwkaart is geselecteerd
                view.getStyleClass().add("gebouwkaart-active");
                this.gebouwKaartController.setActiveCard(this.gebouwKaart);
                final Timeline timeline = new Timeline();
                timeline.setAutoReverse(true);
                final KeyValue kv = new KeyValue(view.layoutYProperty(), -75, Interpolator.EASE_IN);
                final KeyFrame kf = new KeyFrame(Duration.millis(125), kv);
                timeline.getKeyFrames().add(kf);
                timeline.play();
            }
        });
    }

    /**
     * Haalt afbeelding op en maakt een ImageView aan.
     * @return
     */
    private ImageView createImageView() {
        ImageView gebouwKaartImage = new ImageView();
        try {
            gebouwKaartImage = new ImageView(new Image(gebouwKaart.getImage()));
            if (width > 1 && height > 1) {
                gebouwKaartImage.setFitWidth(width);
                gebouwKaartImage.setFitHeight(height);
            } else {
                gebouwKaartImage.setFitWidth(200);
                gebouwKaartImage.setFitHeight(300);
            }
            gebouwKaartImage.getStyleClass().add("gebouwkaart-image");
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        return gebouwKaartImage;
    }

    /**
     * Maak een cirkel met de kleur van het type kaart en de score van de kaart.
     *
     * @return Gebouwscore view icoon
     */
    private Pane createScoreView() {
        Pane gebouwScoreView = new Pane();
        try {
            Circle circle = new Circle();
            if (width > 1 && height > 1) {
                circle.setRadius(width/4);
            } else {
                circle.setRadius(30);
            }

            circle = setGebouwTypeClass(circle);
            circle.getStyleClass().add("gebouwkaart-circle");

            Text gebouwScore = new Text(String.valueOf(gebouwKaart.getKosten()));
            gebouwScore.getStyleClass().add("gebouwkaart-score");

            gebouwScoreView.getChildren().addAll(circle, gebouwScore);
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        return gebouwScoreView;
    }

    /**
     * Voegt juist CSS class toe voor de juiste type kleur.
     *
     * @param circle ScoreView circkel
     * @return
     */
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

    /**
     * Maakt een StackPane aan met daarin een transparte achtergrond en
     * de naam van de GebouwKaart.
     *
     *
     * @return Transparent paneel met naam van GebouwKaart
     */
    private StackPane createNameField() {
        StackPane gebouwKaartName = new StackPane();
        Rectangle background = new Rectangle();
        if (width > 1 && height > 1) {
            background.setWidth(width);
            background.setHeight(40);
        } else {
            background.setWidth(200);
            background.setHeight(50);
        }
        background.setFill(Color.rgb(0, 0, 0, 0.7));
        try {
            Text name = new Text(gebouwKaart.getNaam());
            name.getStyleClass().add("gebouwkaart-naam");
            gebouwKaartName.getChildren().addAll(background, name);
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        return gebouwKaartName;
    }

    /**
     * GebouwKaartObserver method. Update de view met nieuwe gegevens.
     *
     * @param gebouwKaart
     * @throws RemoteException
     */
    public void modelChanged(GebouwKaartRemote gebouwKaart) throws RemoteException {
        // TODO: UPDATE VIEW
        this.gebouwKaart = gebouwKaart;
    }

    /**
     *
     * @return Controller van GebouwKaartView
     */
    public GebouwKaartController gebouwKaartController() {
        return this.gebouwKaartController;
    }

    /**
     * Methode om opgebouwde view op te halen.
     *
     * @return StackPane met de uiteindelijke GebouwKaartView
     */
    public StackPane view() {
        this.gebouwKaartView.getStyleClass().add("gebouwkaart");
        return this.gebouwKaartView;
    }

}
