package Machiavelli.Views;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import Machiavelli.Machiavelli;
import Machiavelli.Controllers.PuntenController;

public class PuntenView extends UnicastRemoteObject {
    private PuntenController puntenController;
    private StackPane stackPane;
    private StackPane holder;
    private Stage stage = Machiavelli.getInstance().getStage();
    private Button exitButton;
    private Scene scene;

    public PuntenView(PuntenController puntenController) throws RemoteException {
        this.puntenController = puntenController;

        Rectangle bg = new Rectangle(1440, 900);
        bg.setFill(Color.rgb(0, 0, 0, 0.7));

        stackPane = new StackPane();

        Text title = new Text("Einde van het spel");
        title.setFill(Color.WHITE);
        title.setLayoutX(590);
        title.setLayoutY(50);
        
        Text uitkomst = new Text(getText());
        uitkomst.setFill(Color.WHITE);
        
        this.exitButton.setText("Afsluiten");
        this.exitButton.setMinWidth(150f);
        this.exitButton.setMinHeight(50f);
        this.exitButton
        .setOnAction(event -> System.exit(0));
        
        this.stackPane.getStylesheets().add("Machiavelli/Resources/puntenView.css");
        
        VBox controlContainer = new VBox();
        controlContainer.setSpacing(50.0);
        controlContainer.setPadding(new Insets(1, 1, 1, 1));

        controlContainer.getChildren().addAll(bg, stackPane, exitButton);
        controlContainer.setAlignment(Pos.CENTER);
        this.stackPane.getChildren().addAll(controlContainer);
    }
    public void show() {
        holder = new StackPane();
        holder.getChildren().addAll(stage.getScene().getRoot().getChildrenUnmodifiable());
        holder.getChildren().add(stackPane);
        holder.getStylesheets().add("Machiavelli/Resources/style.css");

        FadeTransition ft = new FadeTransition(Duration.millis(300), holder);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();

        this.scene = new Scene(holder, 1440, 900);
        stage.setScene(scene);
        stage.show();
    }
    public String getText() {
        return null; //moet nog ingevuld worden
    }
}
