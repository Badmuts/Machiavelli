package Machiavelli.Views;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Machiavelli.Controllers.InkomstenController;
import Machiavelli.Models.Spel;
import Machiavelli.Models.Speler;

public class KiesInkomstenView {
	
	//Variables
	private InkomstenController inkomstenController;
	//comment out the new stage
//	private Stage stage = Machiavelli.getInstance().getStage();
	private Stage stage;
	private Button ontvangGoud, ontvangKaarten;
	private ImageView goudImage, kaartenImage;
	private Text title;
	private Scene scene;
	private Pane pane;
	
	public KiesInkomstenView()
	{
		//comment out new stage.
		this.stage = new Stage();
		//TODO: inkomstencontroller moet de speler krijgen van beurt!
		this.inkomstenController = new InkomstenController(new Speler(new Spel()));
		
		this.title = new Text("Maak je keuze:");
		this.title.setId("title");
		this.title.setFill(Color.WHITE);
		this.title.setLayoutX(660);
		this.title.setLayoutY(50);
		
		this.goudImage = new ImageView(new Image("Machiavelli/Resources/placeholderimg.gif"));
		this.goudImage.setId("goudImg");
		this.goudImage.setLayoutX(300);
		this.goudImage.setLayoutY(200);
		Rectangle goudRect = new Rectangle(400, 400);
		goudRect.setArcHeight(400);
		goudRect.setArcWidth(400);
		this.goudImage.setClip(goudRect);
		
		this.kaartenImage = new ImageView(new Image("Machiavelli/Resources/placeholderimg.gif"));
		this.kaartenImage.setId("kaartenImg");
		this.kaartenImage.setLayoutX(900);
		this.kaartenImage.setLayoutY(200);
		Rectangle kaartenRect = new Rectangle(400, 400);
		kaartenRect.setArcHeight(400);
		kaartenRect.setArcWidth(400);
		this.kaartenImage.setClip(kaartenRect);
		
		this.ontvangGoud = new Button("Ontvang goud");
		this.ontvangGoud.setId("goudButton");
		this.ontvangGoud.setLayoutX(300);
		this.ontvangGoud.setLayoutY(700);
		this.ontvangGoud.setMinWidth(400f);
		this.ontvangGoud.setMinHeight(80f);
		
		this.ontvangKaarten = new Button("Ontvang kaarten");
		this.ontvangKaarten.setId("kaartenButton");
		this.ontvangKaarten.setLayoutX(900);
		this.ontvangKaarten.setLayoutY(700);
		this.ontvangKaarten.setMinWidth(400f);
		this.ontvangKaarten.setMinHeight(80f);

		
		//kiezen goud doet nog niks
		this.ontvangGoud.setOnAction(event -> inkomstenController.cmdKiezenGoud());
		this.ontvangKaarten.setOnAction((event) -> {
			this.inkomstenController.weergeefTrekkenKaartView();
			//sluit het kiesinkomsten venster om het andere venster te laten zien.
			this.stage.close();
		});
		
		this.pane = new Pane();
		this.pane.setId("kiesInkomstenPane");
		Rectangle rect = new Rectangle(1600, 900);
		pane.setClip(rect);
		this.pane.getChildren().addAll(this.title, this.goudImage, this.kaartenImage, this.ontvangGoud, this.ontvangKaarten);
		this.pane.getStylesheets().add("Machiavelli/Resources/KiesInkomstenView.css");
		
		//delete this line later, only give the pane to other classes, so they can add it to the scene.
		this.scene = new Scene(pane, 1600, 900);
        this.scene.setFill(Color.TRANSPARENT);
        this.stage.initStyle(StageStyle.TRANSPARENT);
	}
	
	public void show()
	{
		//main scene gets overridden, new scene is placed in the main/current stage.
		stage.setScene(this.scene);
		stage.centerOnScreen();
		stage.setAlwaysOnTop(true);
//		stage.isFocused();
		stage.show();
	}
	
	public void close()
	{
		stage.close();
	}
	
	public Pane getPane()
	{
		return this.pane;
	}

}
