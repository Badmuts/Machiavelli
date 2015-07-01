package Machiavelli.Views;

import java.rmi.RemoteException;
import java.util.ArrayList;

import Machiavelli.Interfaces.Remotes.GebouwKaartRemote;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Machiavelli.Machiavelli;
import Machiavelli.Controllers.InkomstenController;
import Machiavelli.Models.GebouwKaart;

/**
 * @author Jamie Kalloe
 * 
 *         De TrekkenKaartView wordt gebruikt als een speler heeft gekozen om kaarten te trekken in
 *         de KiesInkomstenView. De speler krijgt 2 willekeurig getrokken kaarten te zien waarom
 *         hij/zij kan klikken om de geselecteerde kaart in de hand te plaatsen.
 */

public class TrekkenKaartView {
  private ArrayList<Button> kaartenButtons;
  private ArrayList<GebouwKaartRemote> gebouwen = new ArrayList<>();
  private Pane pane;

  /**
   * De TrekkenKaartView laat een titel met een korte uitleg voor de uit te voeren actie en 2
   * kaarten zien waarop men kan klikken.
   * 
   * @param inkomstenController
   */
  public TrekkenKaartView(InkomstenController inkomstenController) {

    this.kaartenButtons = new ArrayList<Button>();

    Text title = new Text("Maak je keuze:");
    title.setId("title");
    title.setFill(Color.WHITE);
    title.setLayoutX(660);
    title.setLayoutY(50);

    pane = new Pane();
    pane.setId("ROOTNODE");
    pane.getChildren().add(title);
    System.out.println("Voor loop");

    System.out.println("Na loop");
    Rectangle rect = new Rectangle(1440, 900);
    pane.setClip(rect);
    pane.getStylesheets().add("Machiavelli/Resources/TrekkenKaartView.css");
  }

  /**
   * Retourneerd een lijst met een aantal button voor elk getrokken kaart.
   */
  public ArrayList<Button> getButtonList() {
    System.out.println("Button list");
    return this.kaartenButtons;
  }

  /**
   * Retourneerd een lijst met een aantal GebouwKaarten.
   * 
   * @return gebouwen arrayList van gebouwkaarten
   */
  public ArrayList<GebouwKaartRemote> getGebouwen() {
    return this.gebouwen;
  }

  /**
   * Maakt een nieuwe GebouwView (button) aan voor elk gebouw dat deze method mee krijgt als
   * parameter. De gemaakte button wordt gestijld met css en wordt toegevoegd aan een lijst met
   * buttons. De meegegeven gebouwkaart wordt opegeslagen in een lijst met gebouwkaarten.
   * 
   * @param gebouw
   */
  public void createGebouwView(GebouwKaartRemote gebouw) throws RemoteException {
    Button newButton = new Button();
    newButton.setGraphic(new ImageView(gebouw.getImage()));
    newButton.setLayoutX(Math.random() * 111);
    newButton.setLayoutY(50);

    // for css styling.
    newButton.setId("Kaart");

    this.gebouwen.add(gebouw);
    this.kaartenButtons.add(newButton);
  }

  /**
   * Maakt een nieuwe horizontal box voor elke gemaakte button in de kaartenButtons lijst. De box
   * wordt daarna toegevoegd aan de TrekkenKaartView (holder) pane.
   */
  public void addButtonsToView() {
    HBox hBox = new HBox();
    hBox.setSpacing(30.0);
    hBox.setPadding(new Insets(15, 15, 15, 15));
    hBox.setSpacing(90.0);

    for (Button button : this.kaartenButtons) {
      hBox.getChildren().add(button);
    }

    hBox.setLayoutY(120);
    hBox.setLayoutX(300);
    this.pane.getChildren().add(hBox);
  }

  /**
   * Laat de TrekkenKaartView pane zien in de huidige stage. Er wordt een nieuwe StackPane
   * aangemaakt, waarin de nieuwe pane en de huidige pane worden geplaatst. De huidige pane wordt
   * later dan de nieuwe pane (van de TrekkenKaartView) geplaatst, zodat die onderop ligt. De nieuwe
   * StackPane wordt in de huidige scene geplaatst van de singleton stage.
   */
  public void cmdWeergeefTrekkenKaartView() {
    StackPane pane = new StackPane();

    Pane old = new Pane();
    old.getChildren().add(Machiavelli.getInstance().getStage().getScene().getRoot());
    pane.getChildren().addAll(old, this.getPane());

    Scene scene = new Scene(pane, 1440, 900);
    Machiavelli.getInstance().getStage().setScene(scene);
  }

  /**
   * Sluit de huidig geopende pane. Er wordt door de children (nodes) geloopt van de huidige scene.
   * Er wordt gefilterd op #ROOTNODE (css #id) om de te verwijderen pane te vinden. De pane wordt in
   * een nieuwe StackPane geplaatst en vervolgens op null gezet. Hierdoor de wordt de pane uit de
   * huidige scene verwijderd en niet meer weergeven op het scherm.
   */
  public void cmdSluitTrekkenKaartView() {
    Pane newPane = new Pane();
    Scene currentScene = Machiavelli.getInstance().getStage().getScene();

    System.out.println("\nThe current scene contains the following nodes (panes): ");
    for (Node node : currentScene.getRoot().getChildrenUnmodifiable()) {
      System.out.println(node.idProperty());
      if (currentScene.lookup("#ROOTNODE").equals(node)) {
        newPane.getChildren().add(node);

        System.out.println("\nVerwijderd: " + node.getId());
        break;
      }
    }

    newPane = null;

    // show the nodes in the current list.
    System.out.println("\nThe current scene contains the following  nodes (panes): ");
    for (Node node : currentScene.getRoot().getChildrenUnmodifiable()) {
      System.out.println(node.idProperty());
    }
  }

  /**
   * Retourneerd de gemaakte pane voor de TrekkenKaartView.
   * 
   * @return pane TrekkenKaartView
   */
  public Pane getPane() {
    return this.pane;
  }
}
