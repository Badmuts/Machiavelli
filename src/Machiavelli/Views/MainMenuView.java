package Machiavelli.Views;

import Machiavelli.Controllers.MenuController;
import Machiavelli.Machiavelli;
import javafx.scene.CacheHint;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenuView {
    private Button startbutton;
    private Button exitbutton;
    private Button spelregels;
    private Button deelnemenknop;
    private Button hervattenknop;
    private Button nieuwspelknop;
    private Button exitbutton2;
    private Button spelregels2;
    // private SpelController sc;
    // private Stage primaryStage;
    private Scene mainMenu;
    private Scene mainSelect;
    private Stage stage = Machiavelli.getInstance().getStage();
    private MenuController menuController;
    
    /**
     * @author Jimmy
     * Edited by: Daan & Jamie
     * 
     * 
     * @param menuController
     */

    public MainMenuView(MenuController menuController) {

        // Versienummer, voorkomt het glitchen van het achtergrond bij het openen van een nieuwe
        // pane, in de scene.
        Text versieNummer = new Text();
        versieNummer.setText("V. 1.0");
        versieNummer.setFill(Color.WHITE);
        versieNummer.setLayoutX(1420);
        versieNummer.setLayoutY(910);

        Text versieNummer2 = new Text();
        versieNummer2.setText("V. 1.0");
        versieNummer2.setFill(Color.WHITE);
        versieNummer2.setLayoutX(1420);
        versieNummer2.setLayoutY(910);

        Pane mainMenuPane = new Pane();
        Pane mainSelectPane = new Pane();

        nieuwspelknop = new Button();
        hervattenknop = new Button();
        deelnemenknop = new Button();
        startbutton = new Button();
        exitbutton = new Button();
        spelregels = new Button();
        exitbutton2 = new Button();
        spelregels2 = new Button();

        // Machiavelli tekst layout
        Text mainTx = new Text("Machiavelli");
        mainTx.setFill(Color.WHITE);
        DropShadow ds = new DropShadow();
        ds.setOffsetY(2);
        mainTx.setEffect(ds);
        mainTx.setScaleX(7);
        mainTx.setScaleY(6.5);
        mainTx.setLayoutX(780);
        mainTx.setLayoutY(320);

        Text mainTx2 = new Text("Machiavelli");
        mainTx2.setFill(Color.WHITE);
        mainTx2.setEffect(ds);
        mainTx2.setScaleX(7);
        mainTx2.setScaleY(6.5);
        mainTx2.setLayoutX(780);
        mainTx2.setLayoutY(170);

        // Knoppen definieren
        initButton(startbutton, "Kies spel", "buttonstart", 700, 450, 200f, 75f, "button-success");
        initButton(exitbutton, "Afsluiten", "buttonexit", 700, 530, 200f, 75f, "button-danger");
        initButton(spelregels, "Spelregels", "buttonregels", 15, 10, 125f, 50f, "button-primary");
        initButton(nieuwspelknop, "Nieuw spel", "gamekiezen", 700, 290, 200f, 75f, "button-primary");
        initButton(hervattenknop, "Hervatten", "gamekiezen", 700, 370, 200f, 75f, "button-primary");
        initButton(deelnemenknop, "Deelnemen", "gamekiezen", 700, 450, 200f, 75f, "button-primary");
        initButton(exitbutton2, "Afsluiten", "buttonexit", 700, 530, 200f, 75f, "button-danger");
        initButton(spelregels2, "Spelregels", "buttonregels", 15, 10, 125f, 50f, "button-primary");

        Image spelregelsbg = new Image("Machiavelli/Resources/SpelregelsBorder.png");
        ImageView iv = new ImageView(spelregelsbg);
        ImageView iv2 = new ImageView(spelregelsbg);
        iv.setCache(true);
        iv.setFitWidth(205);
        iv.setFitHeight(74);
        iv2.setCache(true);
        iv2.setFitWidth(205);
        iv2.setFitHeight(74);

        // toevoegen van elementen aan het frame
        mainMenuPane.getChildren().addAll(iv, startbutton, exitbutton, spelregels, mainTx);
        mainMenuPane.getStyleClass().add("menu");
        mainMenuPane.setPrefSize(1440, 900);
        mainSelectPane.getChildren().addAll(iv2, nieuwspelknop, hervattenknop, deelnemenknop,
                exitbutton2, spelregels2, mainTx2);
        mainSelectPane.getStyleClass().add("menu");
        mainSelectPane.setPrefSize(1440, 900);

        Pane container1 = new Pane();
        container1.setPrefSize(1440, 900);
        container1.getChildren().add(mainMenuPane);

        Pane container2 = new Pane();
        container2.setPrefSize(1440, 900);
        container2.getChildren().add(mainSelectPane);

        container1.getStylesheets().add("Machiavelli/Resources/style.css");
        container2.getStylesheets().add("Machiavelli/Resources/style.css");

        container1.setCache(true);
        container1.setCacheShape(true);
        container1.setCacheHint(CacheHint.SPEED);

        container2.setCache(true);
        container2.setCacheShape(true);
        container2.setCacheHint(CacheHint.SPEED);

        // Instellen wat er weergeven moet worden
        mainSelect = new Scene(container2, 1440, 900);
        mainMenu = new Scene(container1, 1440, 900);
        this.menuController = menuController;
    }

    public void initButton(Button button, String tekst, String id, int posx, int posy, float sizeX,
            float sizeY, String className) {
        button.setText(tekst);
        button.setId(id);
        button.setLayoutX(posx);
        button.setLayoutY(posy);
        button.setMinWidth(sizeX);
        button.setMinHeight(sizeY);
        button.getStyleClass().add(className);
    }

    public void show() {
        stage.setScene(mainMenu);
        stage.centerOnScreen();
        stage.show();
    }

    public void showSelect() {
        stage.setScene(mainSelect);
        stage.centerOnScreen();
        stage.show();
    }

    public Button getStartButton() {
        return startbutton;
    }

    public Button getExitButton() {
        return exitbutton;
    }


    public Button getSpelregelsButton() {
        return this.spelregels;
    }

    public Button getSpelregelsButton2() {
        return spelregels2;
    }

    public Button getExitButton2() {
        return exitbutton2;
    }

    public Button getNieuwSpelKnop() {
        return nieuwspelknop;
    }

    public Button getDeelnemenKnop() {
        return deelnemenknop;
    }

    public Button getHervattenknop() {
        return hervattenknop;
    }

    public void StopStage() {
        this.stage.hide();
    }
}
