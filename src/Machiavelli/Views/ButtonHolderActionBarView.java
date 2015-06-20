package Machiavelli.Views;

import Machiavelli.Controllers.SpeelveldController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ButtonHolderActionBarView extends StackPane {

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

    public ButtonHolderActionBarView(SpeelveldController speelveldController) {
        this.speelveldController = speelveldController;
        gebruikEigenschap = new Button();
        exitbutton = new Button();
        spelregels = new Button();
        opslaanknop = new Button();
        goudbutton = new Button();
        bouwbutton = new Button();
        eindebeurtbutton= new Button();

        this.buttonGrid.setHgap(10);
        this.buttonGrid.setVgap(10);
        this.buttonGrid.setPadding(new Insets(22.5, 0, 22.5, 0));

        initButton(gebruikEigenschap, "Eigenschap", "button-primary", 1, 1, 160f, 55f);
        initButton(bouwbutton, "Bouwen", "button-primary", 2, 1, 160f, 55f);
        initButton(goudbutton, "Bonusgoud", "button-primary", 1, 2, 160f, 55f);
        initButton(eindebeurtbutton,"Einde beurt","button-danger", 2, 2, 160f, 55f);
        initButton(opslaanknop,"Opslaan","button-success", 1, 3, 160f, 55f);
        initButton(exitbutton,"Afsluiten","button-danger", 2, 3, 160f, 55f);

        goudbutton.setOnAction(event -> this.speelveldController.cmdBonusGoud());

        buttonholder = new Rectangle(0, 0, 350, 250);
        buttonholder.setFill(Color.rgb(57, 57, 57));
        this.getChildren().addAll(buttonholder, buttonGrid);
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

}
