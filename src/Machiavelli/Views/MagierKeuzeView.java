package Machiavelli.Views;

import Machiavelli.Controllers.KarakterController;
import Machiavelli.Machiavelli;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MagierKeuzeView {

    private final KarakterController karakterController;
    private StackPane container;
    private HBox keuzeViewSpelerStapel;
    private Rectangle overlay;
    private VBox stapelView;
    private VBox spelerView;
    private StackPane holder;
    private Scene scene;
    private Stage stage = Machiavelli.getInstance().getStage();

    public MagierKeuzeView(KarakterController karakterController) {
        this.karakterController = karakterController;
        container = new StackPane();
        container.setPrefSize(1440, 900);
        keuzeViewSpelerStapel = new HBox(50);

        // Create achtergrond overlay
        overlay = new Rectangle(1440, 900);
        overlay.getStyleClass().add("overlay");

        // Create title
        Text title = new Text("Wilt u ruilen met een speler of met de stapel?");
        title.getStyleClass().add("h1");

        stapelView = createStapelView(); // create stapelView
        spelerView = createSpelerSelectView(); // create spelerView

        // Fill container
        keuzeViewSpelerStapel.getChildren().addAll(stapelView, spelerView);
        keuzeViewSpelerStapel.setAlignment(Pos.CENTER);
        container.getChildren().addAll(overlay, title, keuzeViewSpelerStapel);
        container.getStylesheets().add("Machiavelli/Resources/style.css");
        // TODO: Fix uitlijning
        StackPane.setAlignment(keuzeViewSpelerStapel, Pos.CENTER);
        StackPane.setAlignment(title, Pos.TOP_CENTER);
        StackPane.setAlignment(container, Pos.CENTER);
    }

    /**
     * Returns een Stackpane met een afbeelding voor speler en een button
     * die de eigenschap uitvoert op de speler.
     *
     * @return View met afbeelding van speler en button voor speler.
     */
    private VBox createSpelerSelectView() {
        VBox spelerView = new VBox(20);
        spelerView.setMinSize(400, 400);

        Rectangle circleSpeler = new Rectangle(400, 400);
        circleSpeler.setArcHeight(400);
        circleSpeler.setArcWidth(400);

        ImageView stapelImage = new ImageView(new Image("/Machiavelli/Resources/speler-image.png"));
        stapelImage.setFitWidth(400);
        stapelImage.setFitHeight(400);
        stapelImage.setClip(circleSpeler);

        Button spelerButton = new Button("Ruil met speler");
        spelerButton.getStyleClass().add("button-primary");
//        spelerButton.setOnAction(event -> this.karakterController.cmdKiesSpelerTarget());

        spelerView.getChildren().addAll(stapelImage, spelerButton);
        spelerView.setAlignment(Pos.CENTER);
        return spelerView;
    }

    /**
     * Returns een Stackpane met een afbeelding voor stapel en een button
     * die de eigenschap uitvoert op de stapel.
     *
     * @return View met afbeelding van speler en button voor stapel.
     */
    private VBox createStapelView() {
        VBox stapelView = new VBox(20);
        stapelView.setMinSize(400, 400);

        Rectangle circleStapel = new Rectangle(400, 400);
        circleStapel.setArcHeight(400);
        circleStapel.setArcWidth(400);

        ImageView stapelImage = new ImageView(new Image("/Machiavelli/Resources/kaartenImage.png"));
        stapelImage.setFitWidth(400);
        stapelImage.setFitHeight(400);
        stapelImage.setClip(circleStapel);

        Button stapelButton = new Button("Ruil met stapel");
        stapelButton.getStyleClass().add("button-primary");
        stapelButton.setOnAction(event -> this.karakterController.cmdSetTarget());

        stapelView.getChildren().addAll(stapelImage, stapelButton);
        stapelView.setAlignment(Pos.CENTER);
        // TODO: Fix uitlijning
//        StackPane.setAlignment(stapelImageHolder, Pos.TOP_CENTER);
//        StackPane.setAlignment(stapelView, Pos.CENTER_LEFT);
        return stapelView;
    }

    /**
     * Sluit deze view en laat de vorige view zien.
     */
    public void close() {
        holder.getChildren().remove(container);
        holder.getStylesheets().add("Machiavelli/Resources/style.css");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Laat een keuze menu zien voor de speler die Magier is waar hij moet kiezen
     * tussen het ruilen met de stapel of het ruilen met een speler.
     */
    public void show() {
        holder = new StackPane();
        holder.getChildren().addAll(stage.getScene().getRoot().getChildrenUnmodifiable());
        holder.getChildren().add(container);

        this.scene = new Scene(holder, 1440, 900);
        stage.setScene(scene);
        stage.show();
    }

}
