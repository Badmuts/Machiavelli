package Machiavelli.Views;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ButtonHolderActionBarView extends StackPane {

    private GridPane buttonGrid = new GridPane();
    private Button gebruikEigenschap;
    private Button exitbutton;
    private Button spelregels;
    private Button opslaanknop;
    private Button placeholderbutton1;
    private Button placeholderbutton2;
    private Button placeholderbutton3;
    private Rectangle buttonholder;

    public ButtonHolderActionBarView() {
        gebruikEigenschap = new Button();
        exitbutton = new Button();
        spelregels = new Button();
        opslaanknop = new Button();
        placeholderbutton1 = new Button();
        placeholderbutton2 = new Button();
        placeholderbutton3 = new Button();

        this.buttonGrid.setHgap(10);
        this.buttonGrid.setVgap(10);
        this.buttonGrid.setPadding(new Insets(22.5, 0, 22.5, 0));

        initButton(placeholderbutton3, "Eigenschap", "button-primary", 1, 1, 160f, 55f);
        initButton(placeholderbutton2, "Bouwen", "button-primary", 2, 1, 160f, 55f);
        initButton(placeholderbutton1, "Bonusgoud", "button-primary", 1, 2, 160f, 55f);
        initButton(gebruikEigenschap,"Einde beurt","button-danger", 2, 2, 160f, 55f);
        initButton(opslaanknop,"Opslaan","button-success", 1, 3, 160f, 55f);
        initButton(exitbutton,"Afsluiten","button-danger", 2, 3, 160f, 55f);

        buttonholder = new Rectangle(0, 0, 350, 250);
        buttonholder.setFill(Color.DIMGRAY);
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


}
