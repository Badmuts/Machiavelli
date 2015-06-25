package Machiavelli.Views;

import Machiavelli.Controllers.SpeelveldController;
import Machiavelli.Interfaces.Bonusable;
import Machiavelli.Interfaces.Observers.SpelerObserver;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ButtonHolderActionBarView extends UnicastRemoteObject implements SpelerObserver {

    private SpeelveldController speelveldController;
    private GridPane buttonGrid = new GridPane();
    private Button gebruikEigenschap;
    private Button exitbutton;
    private Button spelregels;
    private Button opslaanknop;
    private Button goudbutton;
    private Button bouwbutton;
    private Button eindebeurtbutton;
    private Rectangle buttonholder;
    private SpelerRemote speler;
    private StackPane container;

    public ButtonHolderActionBarView(SpeelveldController speelveldController) throws RemoteException {
        this.speelveldController = speelveldController;
        this.speler = speelveldController.getSpeler();
        this.container = new StackPane();
        gebruikEigenschap = new Button();
        exitbutton = new Button();
        spelregels = new Button();
        opslaanknop = new Button();
        goudbutton = new Button();
        bouwbutton = new Button();
        eindebeurtbutton= new Button();

        this.speler.addObserver(this);

        this.buttonGrid.setHgap(10);
        this.buttonGrid.setVgap(10);
        this.buttonGrid.setPadding(new Insets(22.5, 0, 22.5, 0));

        initButton(gebruikEigenschap, "Eigenschap", "button-primary", 1, 1, 160f, 55f);
        initButton(bouwbutton, "Bouwen", "button-primary", 2, 1, 160f, 55f);
        initButton(goudbutton, "Bonusgoud", "button-primary", 1, 2, 160f, 55f);
        initButton(eindebeurtbutton,"Einde beurt","button-danger", 2, 2, 160f, 55f);
        initButton(opslaanknop,"Opslaan","button-success", 1, 3, 160f, 55f);
        initButton(exitbutton,"Afsluiten","button-danger", 2, 3, 160f, 55f);

        opslaanknop.setOnAction(event1 -> this.speelveldController.cmdOpslaan());
        goudbutton.setOnAction(event -> this.speelveldController.cmdBonusGoud());
        bouwbutton.setOnAction(event -> this.speelveldController.cmdBouwGebouw());
        eindebeurtbutton.setOnAction(event -> this.speelveldController.cmdEindeBeurt());
        exitbutton.setOnAction(event -> System.exit(0));

        isKarakterBonusable();

        buttonholder = new Rectangle(0, 0, 350, 250);
        buttonholder.setFill(Color.rgb(57, 57, 57));
        this.container.getChildren().addAll(buttonholder, buttonGrid);
    }

    /**
     * Controleer of Karakter bonusable is. Wanneer karakter
     * niet bonusable is word de 'Bonusgoud' knop uitgezet.
     */
    private void isKarakterBonusable() {
        try {
            Bonusable bonusable = (Bonusable)speelveldController.getSpeler().getKarakter();
        } catch (Exception e) {
            if (e instanceof ClassCastException) {
                goudbutton.setDisable(true); // Disable button
            } else {
                goudbutton.setDisable(false); // Enable button
            }
        }
    }

    private void initButton(Button button, String tekst, String styleClass, int columnIndex, int rowIndex, float sizeX, float sizeY){
        button.setText(tekst);
        button.getStyleClass().add(styleClass);
        button.setMinWidth(sizeX);
        button.setMinHeight(sizeY);
        this.buttonGrid.add(button, columnIndex, rowIndex);
    }

    public Button getSpelregels() {
        return this.spelregels;
    }

    public Button getExitbutton() {
        return this.exitbutton;
    }
    
    public Button getGoudbutton() {
    	return this.goudbutton;
    }
    
    public Button getEigenschapButton() {
    	return this.gebruikEigenschap;
    }
    
    public Button getBouwButton() {
    	return this.bouwbutton;
    }
    
    public Button getOpslaanButton() {
    	return this.opslaanknop;
    }
    
    public Button getEindeBeurtButton() {
    	return this.eindebeurtbutton;
    }

    @Override
    public void modelChanged(SpelerRemote speler) throws RemoteException {
        Platform.runLater(() -> {
            try {
                this.speler = speler;
                this.container.getChildren().clear();
                isKarakterBonusable();
                isAbleToBuild();
                this.container.getChildren().addAll(buttonholder, buttonGrid);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void isAbleToBuild() throws RemoteException {
        int gebouwdeGebouwen = speler.getGebouwdeGebouwen();
        int bouwLimiet = speler.getKarakter().getBouwLimiet();
        if (gebouwdeGebouwen >= bouwLimiet) {
            bouwbutton.setDisable(true); // Disable button
        } else {
            bouwbutton.setDisable(false); // Enable button
        }
    }

    public StackPane getPane() {
        return this.container;
    }
}
