package Machiavelli.Views;

import Machiavelli.Controllers.RaadplegenSpelregelsController;
import Machiavelli.Interfaces.Observers.SpelregelsObserver;
import Machiavelli.Interfaces.Remotes.SpelregelsRemote;
import Machiavelli.Machiavelli;
import Machiavelli.Models.Spelregels;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * De SpelregelsView wordt gebruikt om de spelregels van Machiavelli te raadplegen. De spelregels
 * worden opgehaald vanuit het spelregels model en weergeven in een nieuwe pane in de huidige scene.
 */

public class SpelregelsView implements SpelregelsObserver {

    private final StackPane modal;
    private RaadplegenSpelregelsController raadplegenSpelregelsController;
    private StackPane stPane;
    private Spelregels spelregels;
    private Stage stage = Machiavelli.getInstance().getStage();
    private Button closeButton;
    private Scene scene;
    private StackPane holder;

    /**
     * De spelregelView maakt een nieuwe pane aan met daarin een Text object waarin de spelregels
     * worden ingeladen. De view zet de eventhandler voor de closebutton van de pane.
     *
     * @param raadplegenSpelregelsController
     * @throws IOException
     */
    public SpelregelsView(RaadplegenSpelregelsController raadplegenSpelregelsController)
        throws IOException {
        this.raadplegenSpelregelsController = raadplegenSpelregelsController;
        this.spelregels = new Spelregels();

        Rectangle bg = new Rectangle(1440, 900);
        bg.setFill(Color.rgb(0, 0, 0, 0.7));

        Rectangle modalBg = new Rectangle(950, 800);
        modalBg.setFill(Color.WHITE);
        modalBg.setArcWidth(11);
        modalBg.setArcHeight(11);

        Text title = new Text("Spelregels");
        title.setId("title");
        title.setFill(Color.WHITE);

        Text text = new Text(this.spelregels.getSpelregels());
        text.setId("regels");

        this.closeButton = new Button("X");
        this.closeButton.setId("closeButton");
        this.closeButton
            .setOnAction(event -> this.raadplegenSpelregelsController.cmdSluitSpelregels());

        modal = new StackPane();
        modal.setPrefSize(950, 800);
        modal.setMaxSize(950, 800);
        modal.getChildren().addAll(modalBg, title, text, closeButton);
        StackPane.setAlignment(title, Pos.TOP_CENTER);
        StackPane.setAlignment(closeButton, Pos.TOP_RIGHT);
        StackPane.setAlignment(text, Pos.TOP_CENTER);

        stPane = new StackPane();
        stPane.getChildren().addAll(bg, modal);
        stPane.getStyleClass().add("Machiavelli/Resources/SpelregelsView.css");
        StackPane.setAlignment(modal, Pos.CENTER);
    }

    /**
     * Laat de Spelregels pane zien in de huidige stage. Er wordt een nieuwe StackPane aangemaakt,
     * waarin de nieuwe pane en de huidige pane worden geplaatst. De huidige pane wordt later dan de
     * nieuwe pane (van de SpelregelsView) geplaatst, zodat die onderop ligt. De nieuwe pane krijgt
     * opnieuw de css styling mee. De nieuwe StackPane wordt in de huidige scene geplaatst van de
     * singleton stage.
     */
    public void show() {
        holder = new StackPane();
        holder.getChildren().addAll(stage.getScene().getRoot().getChildrenUnmodifiable());
        holder.getChildren().add(stPane);
        holder.getStylesheets().add("Machiavelli/Resources/style.css");

        FadeTransition ft = new FadeTransition(Duration.millis(300), holder);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();

        TranslateTransition tt = new TranslateTransition(Duration.millis(300), modal);
        tt.setFromY(50);
        tt.setToY(0);
        tt.play();

        this.scene = new Scene(holder, 1440, 900);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sluit de huidig geopende pane. Er wordt door de lijst van nodes gezocht naar de te verwijderen
     * pane, die vervolgens wordt verwijderd. De css styling wordt opnieuw toegepast en de huidige
     * stage krijgt opnieuw een scene zonder de verwijderde pane. De stage wordt daarna weergeven.
     */
    public void close() {
        holder.getChildren().remove(stPane);
        holder.getStylesheets().add("Machiavelli/Resources/style.css");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Retourneerd de gemaakte closebutton (waarmee de view gesloten wordt).
     *
     * @return closeButton button
     */
    public Button getCloseButton() {
        return this.closeButton;
    }

    @Override public void modelChanged(SpelregelsRemote spelregels) throws RemoteException {
        // TODO: update view
    }
}
