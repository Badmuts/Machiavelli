package Machiavelli.Views;

import Machiavelli.Controllers.RaadplegenSpelregelsController;
import Machiavelli.Interfaces.Observers.SpelregelsObserver;
import Machiavelli.Interfaces.Remotes.SpelregelsRemote;
import Machiavelli.Machiavelli;
import Machiavelli.Models.Spelregels;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;

public class SpelregelsView implements SpelregelsObserver {

    private RaadplegenSpelregelsController raadplegenSpelregelsController;
    private StackPane stPane;
    private Spelregels spelregels;
	private Stage stage = Machiavelli.getInstance().getStage();
	private Button closeButton;
    private Scene scene;
    private StackPane holder;
    private final Scene oldScene;

    public SpelregelsView(RaadplegenSpelregelsController raadplegenSpelregelsController) throws IOException {
        oldScene = stage.getScene();
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
        this.closeButton.setOnAction(event -> {
            this.raadplegenSpelregelsController.cmdSluitSpelregels();
        });

        /**
         * Om twee Pane's over elkaar heen te plaatsen zou dat via een
         * StackPane moeten voor zover ik heb kunnen lezen:
         *
         * https://docs.oracle.com/javafx/2/layout/builtin_layouts.htm
         * https://docs.oracle.com/javafx/2/api/javafx/scene/layout/StackPane.html
         *
         * Hier moet nog even naar gekeken worden.
         */
        StackPane modal = new StackPane();
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

    public void show() {
        holder = new StackPane();
        holder.getChildren().addAll(stage.getScene().getRoot().getChildrenUnmodifiable());
        holder.getChildren().add(stPane);
        holder.getStylesheets().add("Machiavelli/Resources/style.css");
        this.scene = new Scene(holder, 1440, 900);
        stage.setScene(scene);
		stage.show();
	}

    public void close() {
        holder.getChildren().remove(stPane);
        holder.getStylesheets().add("Machiavelli/Resources/style.css");
        stage.setScene(scene);
        stage.show();
    }

	public Button getCloseButton() {
		return this.closeButton;
	}

	@Override
	public void modelChanged(SpelregelsRemote spelregels) throws RemoteException {
		// TODO: update view
	}
}