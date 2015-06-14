package Machiavelli.Views;

import Machiavelli.Controllers.GebouwKaartController;
import Machiavelli.Interfaces.Observers.GebouwKaartObserver;
import Machiavelli.Interfaces.Remotes.GebouwKaartRemote;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.rmi.RemoteException;

public class GebouwKaartView extends StackPane implements GebouwKaartObserver {

    private GebouwKaartController gebouwKaartController;
    private GebouwKaartRemote gebouwKaart;
    private ImageView gebouwKaartImage;
    private Pane gebouwScoreView;
    private StackPane gebouwKaartName;

    public GebouwKaartView(GebouwKaartRemote gebouwKaart) {
        super();
        this.gebouwKaart = gebouwKaart;
        this.gebouwKaartController = new GebouwKaartController(this, this.gebouwKaart);
        createImageView();
        createScoreView();
        createNameField();
        this.getChildren().addAll(gebouwKaartImage, gebouwScoreView, gebouwKaartName);
        StackPane.setAlignment(gebouwKaartName, Pos.CENTER);
    }

    private void createImageView() {
        try {
            this.gebouwKaartImage = new ImageView(gebouwKaart.getImage());
            this.gebouwKaartImage.setFitWidth(200);
            this.gebouwKaartImage.setFitHeight(300);
        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }

    private void createScoreView() {
        try {
            gebouwScoreView = new Pane();
            Circle circle = new Circle(30);
            circle = setGebouwTypeClass(circle);

            Text gebouwScore = new Text(String.valueOf(gebouwKaart.getKosten()));
            gebouwScore.getStyleClass().add("gebouwkaart-score");

            gebouwScoreView.getChildren().addAll(circle, gebouwScore);
        } catch (RemoteException re) {
            re.printStackTrace();
        }
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

    private void createNameField() {
        gebouwKaartName = new StackPane();
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
    }

    @Override
    public void modelChanged(GebouwKaartRemote gebouwKaart) throws RemoteException {
        this.gebouwKaart = gebouwKaart;
    }
}
