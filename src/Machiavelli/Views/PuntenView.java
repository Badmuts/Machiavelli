package Machiavelli.Views;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import Machiavelli.Machiavelli;
import Machiavelli.Controllers.PuntenController;

public class PuntenView extends UnicastRemoteObject {
    private PuntenController puntenController;
    private Pane pane;
    private StackPane holder;
    private Stage stage = Machiavelli.getInstance().getStage();
    private Button exitButton;
    private Scene scene;

    public PuntenView(PuntenController puntenController) throws RemoteException {
        this.puntenController = puntenController;

        Rectangle bg = new Rectangle(1440, 900);
        bg.setFill(Color.rgb(0, 0, 0, 0.7));

        pane = new Pane();

        Text title = new Text("Einde van het spel");
        title.setId("titel");
        title.setFill(Color.WHITE);
        title.setLayoutX(670);
        title.setLayoutY(100);
        
        Text uitkomst = new Text(getText());
        uitkomst.setId("tekst");
        uitkomst.setFill(Color.WHITE);
        uitkomst.setLayoutX(370);
        uitkomst.setLayoutY(350);
        
        exitButton = new Button();
        exitButton.setId("closeButton");
        this.exitButton.setText("Afsluiten");
        this.exitButton.setMinWidth(150f);
        this.exitButton.setMinHeight(50f);
        this.exitButton.setLayoutY(400);
        this.exitButton.setLayoutX(650);
        this.exitButton
        .setOnAction(event -> System.exit(0));
                
        pane.getChildren().addAll(bg, title, uitkomst, exitButton);
        this.pane.getStylesheets().add("Machiavelli/Resources/puntenView.css");
    }
    
    public void show() {
        holder = new StackPane();
        holder.getChildren().addAll(stage.getScene().getRoot().getChildrenUnmodifiable());
        holder.getChildren().add(pane);
        holder.getStylesheets().add("Machiavelli/Resources/style.css");

        FadeTransition ft = new FadeTransition(Duration.millis(300), holder);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();

        this.scene = new Scene(holder, 1440, 900);
        stage.setScene(scene);
        stage.show();
    }
    
    public String getText() throws RemoteException {
        return puntenController.cmdBerekenScorelijst();
    }
    
    
}
