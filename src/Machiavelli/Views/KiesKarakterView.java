package Machiavelli.Views;

import Machiavelli.Controllers.KarakterController;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Remotes.KarakterFactoryRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Machiavelli;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
        } else {
        	createKarakterViews();
        }
        createKarakterGrid();
        this.pane.getChildren().add(title);
	}
    
	public void createKarakterViews() throws RemoteException {
        for (Karakter karakter: this.karakterFactory.getKarakters()) {
            // Create container
            StackPane karakaterView = new StackPane();
            karakaterView.setPrefSize(360, 360);
            // Create image holder
            ImageView karakterPortrait;
            // Create new Button
            Button newButton = new Button(karakter.getNaam());

            // Create clip for karakterPortait
            Image image = new Image(karakter.getImage());
            
            Rectangle circle = new Rectangle(image.getHeight(), image.getWidth());
            circle.setArcWidth(image.getWidth());
            circle.setArcHeight(image.getHeight());

            // Create imageView for KarakterPortait
            karakterPortrait = new ImageView(image);
            karakterPortrait.setClip(circle);
//            karakterImage.getChildren().add(karakterPortrait);
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

            // Fill container
            karakaterView.getChildren().addAll(karakterPortrait, newButton);
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
            ImageView karakterPortrait;
            // Create new Button
            Button newButton = new Button(speler.getKarakter().getNaam());

            // Create clip for karakterPortait
            Image image = new Image(speler.getKarakter().getImage());
            
            Rectangle circle = new Rectangle(image.getHeight(), image.getWidth());
            circle.setArcWidth(image.getWidth());
            circle.setArcHeight(image.getHeight());

            // Create imageView for KarakterPortait
            karakterPortrait = new ImageView(image);
            karakterPortrait.setClip(circle);

            // Set button styling
            newButton.getStyleClass().add("button-primary");
            newButton.setMinWidth(230f);
            newButton.setMinHeight(50f);

        	newButton.setOnAction(event -> {
                this.karakterController.cmdSetSpelerTarget(speler);
            });

            // Fill container
            karakaterView.getChildren().addAll(karakterPortrait, newButton);
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
        this.pane.setId("kieskarakterpane");
        this.pane.getChildren().addAll(bg, grid);
    }

	public Pane getPane() {
		return this.pane;
	}

    public void show() {
    	StackPane pane = new StackPane();
    	
    	Pane old = new Pane();
    	old.getChildren().add(Machiavelli.getInstance().getStage().getScene().getRoot());
    	pane.getChildren().addAll(old, this.getPane());

    	pane.getStylesheets().add("Machiavelli/Resources/style.css");
    	Scene scene = new Scene(pane, 1440, 900);
		Machiavelli.getInstance().getStage().setScene(scene);
	}
    
    public void close()
	{
		Pane newPane = new Pane();
    	Scene currentScene = Machiavelli.getInstance().getStage().getScene();

    	for(Node node : currentScene.getRoot().getChildrenUnmodifiable())
    	{
    		if(currentScene.lookup("#kieskarakterpane").equals(node))
    		{
    			newPane.getChildren().add(node);
    			break;
    		}
    	}

    	newPane = null;
	}
    
}
