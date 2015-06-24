package Machiavelli.Views;

import Machiavelli.Controllers.KarakterController;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Remotes.KarakterFactoryRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Machiavelli;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class KiesKarakterView {

    private KarakterController karakterController;
    private KarakterFactoryRemote karakterFactory;
    private Text title;
	private ArrayList<Button> karakterButtons;
	private ArrayList<Image> karakterImages;
	private StackPane pane;
    private ArrayList<StackPane> karakterViews = new ArrayList<>();
    private StackPane holder;
    private Scene scene;
    private Stage stage = Machiavelli.getInstance().getStage();
    private Pane container;
    private ArrayList<SpelerRemote> spelerLijst;

    public KiesKarakterView(KarakterFactoryRemote karakterFactory, KarakterController karakterController) throws RemoteException {
        this.karakterFactory = karakterFactory;
        this.karakterController = karakterController;
		this.title = new Text();
		this.title.setId("title");
		this.title.setFill(Color.WHITE);
		this.title.setLayoutX(590);
		this.title.setLayoutY(50);
		this.title.setText("Kies een karakter:");

		this.spelerLijst = this.karakterController.getSpeler().getSpel().getSpelers();
		
        this.pane = new StackPane();
        
        if (String.valueOf(this.karakterController.getTypeView()).equals("speler"))
        {
        	createSpelerViews();
        	createKarakterGrid();
        } else {
        	createKarakterViews();
        	createKarakterGrid();
        }
        this.pane.getChildren().add(title);
	}
    
    //TODO: check in constructor if type == spelers.
    //TODO: create new createKarakterViews for active spelers.

	public void createKarakterViews() throws RemoteException {
        for (Karakter karakter: this.karakterFactory.getKarakters()) {
            // Create container
            StackPane karakaterView = new StackPane();
            karakaterView.setPrefSize(360, 360);
            // Create image holder
            StackPane karakterImage = new StackPane();
            // Create new Button
            Button newButton = new Button(karakter.getNaam());

            // Create clip for karakterPortait
            Rectangle circle = new Rectangle(150, 150);
            circle.setArcWidth(150);
            circle.setArcHeight(150);
            karakterImage.setClip(circle);

            // Create imageView for KarakterPortait
            ImageView karakterPortrait = new ImageView(new Image(karakter.getImage()));
            karakterImage.getChildren().add(karakterPortrait);
            StackPane.setAlignment(karakterPortrait, Pos.CENTER);

            // Set button styling
            newButton.getStyleClass().add("button-primary");
            newButton.setMinWidth(230f);
            newButton.setMinHeight(50f);

            if (String.valueOf(this.karakterController.getTypeView()).equals("karakter")) {
                newButton.setOnAction(event -> this.karakterController.cmdSetTarget(karakter));
            } else if (String.valueOf(this.karakterController.getTypeView()).equals("ronde")) {
                newButton.setOnAction(event -> this.karakterController.cmdSetKarakter(karakter));
            } 
//            else if (String.valueOf(this.karakterController.getTypeView()).equals("speler")) {
//            	newButton.setText(spelerLijst.get(this.karakterFactory.getKarakters().indexOf(karakter)).getKarakter().getNaam());
//            	newButton.setOnAction(event -> {
//                    try {
//                        this.karakterController.cmdSetSpelerTarget(karakter.getSpeler());
//                    } catch (RemoteException e) {
//                        e.printStackTrace();
//                    }
//                });
//            }

            // Fill container
            karakaterView.getChildren().addAll(karakterImage, newButton);
            StackPane.setAlignment(karakterImage, Pos.TOP_CENTER);
            StackPane.setAlignment(newButton, Pos.BOTTOM_CENTER);

            // Add container to karakterViews[]
            this.karakterViews.add(karakaterView);
        }

	}
	
	public void createSpelerViews() throws RemoteException
	{
		for (SpelerRemote speler: this.spelerLijst) {
            // Create container
            StackPane karakaterView = new StackPane();
            karakaterView.setPrefSize(360, 360);
            // Create image holder
            StackPane karakterImage = new StackPane();
            // Create new Button
            Button newButton = new Button(speler.getKarakter().getNaam());

            // Create clip for karakterPortait
            Rectangle circle = new Rectangle(150, 150);
            circle.setArcWidth(150);
            circle.setArcHeight(150);
            karakterImage.setClip(circle);

            // Create imageView for KarakterPortait
            ImageView karakterPortrait = new ImageView(new Image(speler.getKarakter().getImage()));
            karakterImage.getChildren().add(karakterPortrait);
            StackPane.setAlignment(karakterPortrait, Pos.CENTER);

            // Set button styling
            newButton.getStyleClass().add("button-primary");
            newButton.setMinWidth(230f);
            newButton.setMinHeight(50f);

//            if (String.valueOf(this.karakterController.getTypeView()).equals("speler")) {
            	newButton.setOnAction(event -> {
                    this.karakterController.cmdSetSpelerTarget(speler);
                });
//            }

            // Fill container
            karakaterView.getChildren().addAll(karakterImage, newButton);
            StackPane.setAlignment(karakterImage, Pos.TOP_CENTER);
            StackPane.setAlignment(newButton, Pos.BOTTOM_CENTER);

            // Add container to karakterViews[]
            this.karakterViews.add(karakaterView);
        }

	}

    private void createKarakterGrid() {
        // Create background
        Rectangle bg = new Rectangle(1440, 900);
        bg.setFill(Color.rgb(0, 0, 0, 0.7));
        // Create grid
        GridPane grid = new GridPane();
        // Fill grid
        int columnIndex = 1;
        int rowIndex = 1;
        for (StackPane karakterView: karakterViews) {
            grid.add(karakterView, columnIndex, rowIndex);
            if (columnIndex >= 4) {
                columnIndex = 1;
                rowIndex++;
            } else {
                columnIndex++;
            }
        }
        this.pane.getChildren().addAll(bg, grid);
    }

	public Pane getPane() {
		return this.pane;
	}

    public void close() {
        holder.getChildren().remove(container);
        holder.getStylesheets().add("Machiavelli/Resources/style.css");
        stage.setScene(scene);
        stage.show();
    }

    public void show() {
        container = new Pane();
        container.getChildren().add(pane);
        holder = new StackPane();
        holder.getChildren().addAll(stage.getScene().getRoot().getChildrenUnmodifiable());
        holder.getChildren().add(container);
        holder.getStylesheets().add("Machiavelli/Resources/style.css");

        FadeTransition ft = new FadeTransition(Duration.millis(700), holder);
        ft.setFromValue(0.7);
        ft.setToValue(1.0);
        ft.play();

        this.scene = new Scene(holder, 1440, 900);
        stage.setScene(scene);
        stage.show();
    }
}
