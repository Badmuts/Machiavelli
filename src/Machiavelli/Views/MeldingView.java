package Machiavelli.Views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import Machiavelli.Controllers.MeldingController;
import Machiavelli.Models.Melding;

public class MeldingView {

	private MeldingController meldingController;
	private Button sluitButton;
	private Text melding;
	private StackPane meldingPane;

	public MeldingView() {
		Melding m = new Melding();

		this.melding = new Text();
		this.melding.setId("title");
		this.melding.setFill(Color.WHITE);
		this.melding.setLayoutX(590);
		this.melding.setLayoutY(50);
		this.melding.setText(m.getMeldig());

		this.sluitButton = new Button("Ok");
		this.sluitButton.setId("okButton");
		this.sluitButton.setMinWidth(150f);
		this.sluitButton.setMinHeight(50f);

		this.meldingPane = new StackPane();
		this.meldingPane.setId("meldingview");
		Rectangle rect = new Rectangle(1440, 900);
		this.meldingPane.setClip(rect);
		this.meldingPane.getStylesheets().add(
				"Machiavelli/Resources/MeldingView.css");

		VBox controlContainer = new VBox();
		controlContainer.setSpacing(50.0);
		controlContainer.setPadding(new Insets(1, 1, 1, 1));

		controlContainer.getChildren().addAll(melding, sluitButton);
		controlContainer.setAlignment(Pos.CENTER);
		this.meldingPane.getChildren().addAll(controlContainer);
	}

	public Button getSluitButton() {
		return this.sluitButton;
	}

	public Pane getPane() {
		return this.meldingPane;
	}

	public Text getText() {
		return this.melding;
	}
}
