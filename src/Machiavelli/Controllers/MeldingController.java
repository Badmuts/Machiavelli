package Machiavelli.Controllers;

import java.rmi.RemoteException;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import Machiavelli.Machiavelli;
import Machiavelli.Models.Melding;
import Machiavelli.Views.MeldingView;

/**
 * @author Jamie Kalloe
 * 
 *         De MeldingController wordt gebruikt om een nieuwe melding aan te maken en de
 *         desbetreffende view aan te maken. De melding bestaat uit een view met daarin een text
 *         object en een knop om de view mee af te kunnen sluiten.
 * 
 */

public class MeldingController {

  private Melding melding;
  private MeldingView meldingView;

  /**
   * Maakt een nieuwe MeldingView en Melding model aan. De controller zet en luistert ook naar de
   * event voor de sluiten knop van de MeldingView.
   * 
   */
  public MeldingController() {
    this.melding = new Melding();
    this.meldingView = new MeldingView();
    this.meldingView.getSluitButton().setOnAction((event) -> {
      cmdSluitMeldingView();
    });
  }

  /**
   * Zet een string message in het melding model. Deze message wordt in het text object van de view
   * gezet.
   */
  public void cmdSetMelding(String message) {
    this.melding.setMelding(message);
    this.meldingView.getText().setText(cmdGetMelding());
  }

  /**
   * Deze method retourneerd de controller, op deze manier kan er gemakkelijk een nieuwe message
   * worden gezet in de view, die daarna met kortere / efficientere code kan worden weergeven.
   */
  public MeldingController build(String message) {
    this.melding.setMelding(message);
    this.meldingView.getText().setText(cmdGetMelding());

    return this;
  }

  /**
   * Retourneert de melding (string) uit het melding model.
   * 
   * @return getMelding string melding
   */
  public String cmdGetMelding() {
    return this.melding.getMeldig();
  }

  /**
   * Laat de MeldingView pane zien in de huidige stage. Er wordt een nieuwe StackPane aangemaakt,
   * waarin de nieuwe pane en de huidige pane worden geplaatst. De huidige pane wordt later dan de
   * nieuwe pane (van de MeldingView) geplaatst, zodat die onderop ligt. De nieuwe StackPane wordt
   * in de huidige scene geplaatst van de singleton stage.
   */
  public void cmdWeergeefMeldingView() {
    StackPane pane = new StackPane();

    Pane old = new Pane();
    old.getChildren().add(Machiavelli.getInstance().getStage().getScene().getRoot());
    pane.getChildren().addAll(old, cmdGetPane());

    Scene scene = new Scene(pane, 1440, 900);
    Machiavelli.getInstance().getStage().setScene(scene);
  }

  /**
   * Sluit de huidig geopende pane. Er wordt door de children (nodes) geloopt van de huidige scene.
   * Er wordt gefilterd op #meldingview (css id) om de te verwijderen pane te vinden. De pane wordt
   * in een nieuwe StackPane geplaatst en vervolgens op null gezet. Hierdoor de wordt de pane uit de
   * huidige scene verwijderd en niet meer weergeven op het scherm.
   */
  public void cmdSluitMeldingView() {
    Pane newPane = new Pane();
    Scene currentScene = Machiavelli.getInstance().getStage().getScene();

    System.out.println("\nThe current scene contains the following nodes (panes): ");
    for (Node node : currentScene.getRoot().getChildrenUnmodifiable()) {
      System.out.println(node.idProperty());
      if (currentScene.lookup("#meldingview").equals(node)) {
        // deletes the spelregelview pane, from the nodelist of the scene...
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
   * Retourneert de pane uit de meldingView.
   * 
   * @return getPane pane van MeldingView.
   */
  public Pane cmdGetPane() {
    return this.meldingView.getPane();
  }

  /**
   * Retourneert de sluitbutton (ok button) van de MeldingView.
   * 
   * @return getSluitButton button om de view mee af te sluiten.
   */
  public Button getSluitButton() {
    return this.meldingView.getSluitButton();
  }
}
