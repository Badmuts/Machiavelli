package Machiavelli.Views;

import Machiavelli.Controllers.InkomstenController;
import Machiavelli.Machiavelli;
import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Jamie Kalloe
 * 
 *         De KiesInkomstenView wordt gebruikt om de vorm van inkomsten aan het begin van een beurt
 *         te kiezen. De speler heeft de mogelijkheid om voor goudstukken of kaarten te kiezen. Bij
 *         het kiezen van goudstukken, ontvangt de speler 2 nieuwe goudstukken. Bij het kiezen van
 *         kaarten, wordt er een nieuwe view geopend, waarin de speler moet kiezen tussen 2
 *         willekeurig (door het systeen) getrokken kaarten.
 */

public class KiesInkomstenView extends UnicastRemoteObject {

  // Variables
  private InkomstenController inkomstenController;
  private Button ontvangGoud, ontvangKaarten;
  private ImageView goudImage, kaartenImage;
  private Text title;
  private Pane pane;

  /**
   * Het scherm bestaat uit 2 ronde afbeeldingen, waarvan er 1 goud en er 1 kaarten aanduiden. Onder
   * de afbeeldingen staan 2 knoppen, 1 voor goud en 1 voor kaarten. Bovenaan het scherm staat een
   * titel met daarin een uitleg voor de uit te voeren actie.
   * 
   * @throws RemoteException
   */
  public KiesInkomstenView() throws RemoteException {
    this.title = new Text("Maak je keuze:");
    this.title.setId("title");
    this.title.setFill(Color.WHITE);
    this.title.setLayoutX(580);
    this.title.setLayoutY(50);

    this.goudImage = new ImageView(new Image("Machiavelli/Resources/goudImage.png"));
    this.goudImage.setId("goudImg");
    this.goudImage.setLayoutX(200);
    this.goudImage.setLayoutY(200);
    Rectangle goudRect = new Rectangle(400, 400);
    goudRect.setArcHeight(400);
    goudRect.setArcWidth(400);
    this.goudImage.setClip(goudRect);

    this.kaartenImage = new ImageView(new Image("Machiavelli/Resources/kaartenImage.png"));
    this.kaartenImage.setId("kaartenImg");
    this.kaartenImage.setLayoutX(800);
    this.kaartenImage.setLayoutY(200);
    Rectangle kaartenRect = new Rectangle(400, 400);
    kaartenRect.setArcHeight(400);
    kaartenRect.setArcWidth(400);
    this.kaartenImage.setClip(kaartenRect);

    this.ontvangGoud = new Button("Ontvang goud");
    this.ontvangGoud.setId("goudButton");
    this.ontvangGoud.setLayoutX(200);
    this.ontvangGoud.setLayoutY(700);
    this.ontvangGoud.setMinWidth(400f);
    this.ontvangGoud.setMinHeight(80f);

    this.ontvangKaarten = new Button("Ontvang kaarten");
    this.ontvangKaarten.setId("kaartenButton");
    this.ontvangKaarten.setLayoutX(800);
    this.ontvangKaarten.setLayoutY(700);
    this.ontvangKaarten.setMinWidth(400f);
    this.ontvangKaarten.setMinHeight(80f);

    this.pane = new Pane();
    this.pane.setId("kiesInkomstenPane");
    Rectangle rect = new Rectangle(1600, 900);
    pane.setClip(rect);
    this.pane.getChildren().addAll(this.title, this.goudImage, this.kaartenImage, this.ontvangGoud,
        this.ontvangKaarten);
    this.pane.getStylesheets().add("Machiavelli/Resources/KiesInkomstenView.css");

  }

  /**
   * Laat de KiesInkomstenView pane zien in de huidige stage. Er wordt een nieuwe StackPane
   * aangemaakt, waarin de nieuwe pane en de huidige pane worden geplaatst. De huidige pane wordt
   * later dan de nieuwe pane (van de KiesInkomstenView) geplaatst, zodat die onderop ligt. De
   * nieuwe StackPane wordt in de huidige scene geplaatst van de singleton stage.
   */
  public void weergeefKiesInkomstenView() {
    StackPane pane = new StackPane();

    Pane old = new Pane();
    old.getChildren().add(Machiavelli.getInstance().getStage().getScene().getRoot());
    pane.getChildren().addAll(old, this.pane);

    FadeTransition ft = new FadeTransition(Duration.millis(700), pane);
    ft.setFromValue(0.7);
    ft.setToValue(1.0);
    ft.play();

    Scene scene = new Scene(pane, 1440, 900);
    Machiavelli.getInstance().getStage().setScene(scene);
  }

  /**
   * Sluit de huidig geopende pane. Er wordt door de children (nodes) geloopt van de huidige scene.
   * Er wordt gefilterd op #kiesInkomstenPane (css #id) om de te verwijderen pane te vinden. De pane
   * wordt in een nieuwe StackPane geplaatst en vervolgens op null gezet. Hierdoor de wordt de pane
   * uit de huidige scene verwijderd en niet meer weergeven op het scherm.
   */
  public void cmdSluitKiesInkomstenView() {
    Pane newPane = new Pane();
    Scene currentScene = Machiavelli.getInstance().getStage().getScene();

    System.out.println("\nThe current scene contains the following nodes (panes): ");
    for (Node node : currentScene.getRoot().getChildrenUnmodifiable()) {
      System.out.println(node.idProperty());
      if (currentScene.lookup("#kiesInkomstenPane").equals(node)) {
        newPane.getChildren().add(node);

        System.out.println("\nVerwijderd: " + node.getId());
        break;
      }
    }

    newPane = null;

    // show the nodes in the current list.
    System.out.println("\nThe current scene contains the following nodes (panes): ");
    for (Node node : currentScene.getRoot().getChildrenUnmodifiable()) {
      System.out.println(node.idProperty());
    }
  }

  /**
   * Retourneerd de gemaakte pane voor de KiesInkomstenView.
   * 
   * @return KiesInkomstenView pane
   */
  public Pane getPane() {
    return this.pane;
  }

  /**
   * Retourneerd de gemaakte button voor het ontvangen van goud.
   * 
   * @return ontvangGoud button
   */
  public Button getOntvangGoudButton() {
    return this.ontvangGoud;
  }

  /**
   * Retourneerd de gemaakte button voor het trekken van kaarten.
   * 
   * @return ontvangKaarten button
   */
  public Button getOntvangKaartenButton() {
    return this.ontvangKaarten;
  }
}
