package Machiavelli.Views;

import Machiavelli.Controllers.KarakterController;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Remotes.KarakterFactoryRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Machiavelli;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * @author Jamie Kalloe
 * 
 *         De KiesKarakterView wordt gebruikt om een karakter of speler te kiezen. Dit nieuw gekozen
 *         karakter kan worden gebruikt voor een karakterEigenschap, speler. Zo kan het Karakter
 *         Dief bijvoorbeeld het gekozen karakter bestelen, als hij meespeelt. Een speler kan een
 *         karakter kiezen, die hij de volgende ronde wilt zijn. het karakter magier kan een speler
 *         kiezen met behulp van deze view, voor zijn karaktereigenschap.
 */

public class KiesKarakterView {

    private KarakterController karakterController;
    private KarakterFactoryRemote karakterFactory;
    private Text title;
    private StackPane pane;
    private ArrayList<StackPane> karakterViews = new ArrayList<>();
    private StackPane holder;
    private Scene scene;
    private Stage stage = Machiavelli.getInstance().getStage();
    private Pane container;
    private ArrayList<SpelerRemote> spelerLijst;

    /**
     * Zodra er een nieuwe ronde begint of er een eigenschap wordt gebruikt, wordt er een nieuwe
     * KiesKarakterView gemaakt. De view bestaat uit een titel met een uitleg voor de uit te voeren
     * actie (kies karakter). De view bestaat daarnaast ook uit een aantal images (imageviews) en
     * knoppen (buttons) voor elk karakter in de karakterFactory. Voordat de karakter / speler
     * knoppen worden toegevoegd wordt er eerst gekeken naar het type van de karakterController. Als
     * het type: speler, is. Dan wordt er door de lijst met spelende spelers geloopt, voor elke
     * speler wordt een button gemaakt.
     * 
     * @param karakterFactory
     * @param karakterController
     * @throws RemoteException
     */
    public KiesKarakterView(KarakterFactoryRemote karakterFactory,
            KarakterController karakterController) throws RemoteException {
        this.karakterFactory = karakterFactory;
        this.karakterController = karakterController;
        this.title = new Text();
        this.title.setId("title");
        this.title.setFill(Color.WHITE);
        this.title.setLayoutX(590);
        this.title.setLayoutY(50);
        this.title.setText("Kies een karakter:");

        this.spelerLijst = this.karakterController.getSpeler().getSpel().getSpelers();

        this.pane = new StackPane();

        if (String.valueOf(this.karakterController.getTypeView()).equals("speler")) {
            createSpelerViews();
        } else {
            createKarakterViews();
        }
        createKarakterGrid();

        // zet title bovenaan.
        title.getStyleClass().add("title");
        StackPane.setAlignment(title, Pos.TOP_CENTER);
        this.pane.getChildren().add(title);
    }

    /**
     * Maakt een karakterView voor elke speler in de karakterFactory. Elke view bevat een button
     * (met karkater naam) en een image (van karakter). Elke button krijgt een eventhandler die een
     * toegewezen karakter (bijvoorbeeld 0, moordenaar) zet als target voor een eigenschap. Of als
     * karakter voor een speler. De view wordt in een lijst van StackPanes opgeslagen.
     * 
     * @throws RemoteException
     */
    public void createKarakterViews() throws RemoteException {
        for (Karakter karakter : this.karakterFactory.getKarakters()) {
            // Create container
            StackPane karakaterView = new StackPane();
            karakaterView.setPrefSize(360, 360);
            // Create image holder
            ImageView karakterPortrait;
            // Create new Button
            Button newButton = new Button(karakter.getNaam());

            // Create clip for karakterPortait
            Image image = new Image(karakter.getImage());

            Rectangle circle = new Rectangle(image.getHeight(), image.getWidth());
            circle.setArcWidth(image.getWidth());
            circle.setArcHeight(image.getHeight());

            // Create imageView for KarakterPortait
            karakterPortrait = new ImageView(image);
            karakterPortrait.setClip(circle);
            // karakterImage.getChildren().add(karakterPortrait);
            StackPane.setAlignment(karakterPortrait, Pos.CENTER);

            // Set button styling
            newButton.getStyleClass().add("button-primary");
            newButton.setMinWidth(230f);
            newButton.setMinHeight(50f);

            if (String.valueOf(this.karakterController.getTypeView()).equals("karakter")) {
                newButton.setOnAction(event -> this.karakterController.cmdSetTarget(karakter));
            } else if (String.valueOf(this.karakterController.getTypeView()).equals("ronde")) {
                newButton.setOnAction(event -> this.karakterController.cmdSetKarakter(karakter));
            }

            // Fill container
            karakaterView.getChildren().addAll(karakterPortrait, newButton);
            StackPane.setAlignment(newButton, Pos.BOTTOM_CENTER);

            // Add container to karakterViews[]
            this.karakterViews.add(karakaterView);
        }
        if (karakterController.getTypeView() == "karakter") {
            for (SpelerRemote speler : this.spelerLijst) {
                // Create container
                StackPane karakaterView = new StackPane();
                karakaterView.setPrefSize(360, 360);
                // Create image holder
                ImageView karakterPortrait;
                // Create new Button
                Button newButton = new Button(speler.getKarakter().getNaam());

                // Create clip for karakterPortait
                Image image = new Image(speler.getKarakter().getImage());

                Rectangle circle = new Rectangle(image.getHeight(), image.getWidth());
                circle.setArcWidth(image.getWidth());
                circle.setArcHeight(image.getHeight());

                // Create imageView for KarakterPortait
                karakterPortrait = new ImageView(image);
                karakterPortrait.setClip(circle);

                // Set button styling
                newButton.getStyleClass().add("button-primary");
                newButton.setMinWidth(230f);
                newButton.setMinHeight(50f);

                newButton.setOnAction(event -> {
                    this.karakterController.cmdSetSpelerTarget(speler);
                });

                // Fill container
                karakaterView.getChildren().addAll(karakterPortrait, newButton);
                StackPane.setAlignment(newButton, Pos.BOTTOM_CENTER);

                // Add container to karakterViews[]
                this.karakterViews.add(karakaterView);
            }
        }

    }

    /**
     * Maakt een spelerview voor elke speler in de spelerlijst. Elke view bevat een button (met
     * karkater naam) en een image (van karakter) vanuit de meespelende speler. Elke button krijgt
     * een eventhandler die een toegewezen karakter (bijvoorbeeld 0, moordenaar) zet als target voor
     * een eigenschap. De view wordt in een lijst van StackPanes opgeslagen.
     * 
     * @throws RemoteException
     */
    public void createSpelerViews() throws RemoteException {
        for (SpelerRemote speler : this.spelerLijst) {
            // Create container
            StackPane karakaterView = new StackPane();
            karakaterView.setPrefSize(360, 360);
            // Create image holder
            ImageView karakterPortrait;
            // Create new Button
            Button newButton = new Button(speler.getKarakter().getNaam());

            // Create clip for karakterPortait
            Image image = new Image(speler.getKarakter().getImage());

            Rectangle circle = new Rectangle(image.getHeight(), image.getWidth());
            circle.setArcWidth(image.getWidth());
            circle.setArcHeight(image.getHeight());

            // Create imageView for KarakterPortait
            karakterPortrait = new ImageView(image);
            karakterPortrait.setClip(circle);

            // Set button styling
            newButton.getStyleClass().add("button-primary");
            newButton.setMinWidth(230f);
            newButton.setMinHeight(50f);

            newButton.setOnAction(event -> {
                this.karakterController.cmdSetSpelerTarget(speler);
            });

            // Fill container
            karakaterView.getChildren().addAll(karakterPortrait, newButton);
            StackPane.setAlignment(newButton, Pos.BOTTOM_CENTER);

            // Add container to karakterViews[]
            this.karakterViews.add(karakaterView);
        }

    }

    /**
     * Maakt een nieuwe GridPane aan voor elk karakterView in de karakterviews lijst. De gemaakte
     * GridPane wordt met een background (rectangle) toegevoegd aan een holder StackPane.
     * 
     * @throws RemoteException
     */
    private void createKarakterGrid() {
        // Create background
        Rectangle bg = new Rectangle(1440, 900);
        bg.setFill(Color.rgb(0, 0, 0, 0.7));
        // Create grid
        GridPane grid = new GridPane();
        // Fill grid
        int columnIndex = 1;
        int rowIndex = 1;
        for (StackPane karakterView : karakterViews) {
            grid.add(karakterView, columnIndex, rowIndex);
            if (columnIndex >= 4) {
                columnIndex = 1;
                rowIndex++;
            } else {
                columnIndex++;
            }
        }
        this.pane.setId("kieskarakterpane");
        this.pane.getChildren().addAll(bg, grid);
    }

    /**
     * Retourneerd de gemaakte pane voor de KiesKarakterView.
     * 
     * @return pane
     */
    public Pane getPane() {
        return this.pane;
    }

    /**
     * Laat de KiesKarakterView pane zien in de huidige stage. Er wordt een nieuwe StackPane
     * aangemaakt, waarin de nieuwe pane en de huidige pane worden geplaatst. De huidige pane wordt
     * later dan de nieuwe pane (van de KiesKarakterView) geplaatst, zodat die onderop ligt. De
     * nieuwe StackPane wordt in de huidige scene geplaatst van de singleton stage.
     */
    public void show() {
        StackPane pane = new StackPane();

        Pane old = new Pane();
        old.getChildren().add(Machiavelli.getInstance().getStage().getScene().getRoot());
        pane.getChildren().addAll(old, this.getPane());

        pane.getStylesheets().add("Machiavelli/Resources/style.css");
        Scene scene = new Scene(pane, 1440, 900);
        Machiavelli.getInstance().getStage().setScene(scene);
    }

    /**
     * Sluit de huidig geopende pane. Er wordt door de children (nodes) geloopt van de huidige
     * scene. Er wordt gefilterd op #kieskarakterpane (css id) om de te verwijderen pane te vinden.
     * De pane wordt in een nieuwe StackPane geplaatst en vervolgens op null gezet. Hierdoor de
     * wordt de pane uit de huidige scene verwijderd en niet meer weergeven op het scherm.
     */
    public void close() {
        Pane newPane = new Pane();
        Scene currentScene = Machiavelli.getInstance().getStage().getScene();

        for (Node node : currentScene.getRoot().getChildrenUnmodifiable()) {
            if (currentScene.lookup("#kieskarakterpane").equals(node)) {
                newPane.getChildren().add(node);
                break;
            }
        }

        newPane = null;
    }
}
