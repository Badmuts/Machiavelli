package Machiavelli.Views;

import Machiavelli.Controllers.GebouwKaartController;
import Machiavelli.Interfaces.Observers.GebouwKaartObserver;
import Machiavelli.Interfaces.Remotes.GebouwKaartRemote;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GebouwKaartView extends UnicastRemoteObject implements GebouwKaartObserver {

    private int height;
    private int width;
    private GebouwKaartController gebouwKaartController;
    private GebouwKaartRemote gebouwKaart;
    private StackPane gebouwKaartView;

    public GebouwKaartView(GebouwKaartController gebouwkaartController, GebouwKaartRemote gebouwKaart) throws RemoteException {
        this.gebouwKaart = gebouwKaart;
        this.gebouwKaartController = gebouwkaartController;
        this.gebouwKaartView = new StackPane();
        this.gebouwKaartView.getChildren().addAll(createImageView(), createScoreView(), createNameField());
        this.gebouwKaartView.setPrefSize(150, 250);
        this.addClickHandler();
    }

    private void addClickHandler() {
        this.gebouwKaartView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            StackPane view = (StackPane)event.getSource();
            if (view.getStyleClass().contains("gebouwkaart-active")) {
                view.getStyleClass().remove("gebouwkaart-active");
                this.gebouwKaartController.removeActiveCard(this.gebouwKaart);
            } else {
                view.getStyleClass().add("gebouwkaart-active");
                this.gebouwKaartController.setActiveCard(this.gebouwKaart);
            }
        });
    }

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

    public void modelChanged(GebouwKaartRemote gebouwKaart) throws RemoteException {
        // TODO: UPDATE VIEW
        this.gebouwKaart = gebouwKaart;
    }

    public GebouwKaartController gebouwKaartController() {
        return this.gebouwKaartController;
    }

    public StackPane view() {
        this.gebouwKaartView.getStyleClass().add("gebouwkaart");
        return this.gebouwKaartView;
    }

}
