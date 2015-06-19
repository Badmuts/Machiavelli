package Machiavelli.Views;

import java.rmi.RemoteException;
import java.util.ArrayList;

import com.sun.corba.se.impl.oa.poa.AOMEntry;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import Machiavelli.Interfaces.Karakter;

public class KiesKarakterView {
	
	private Text title;
	private ArrayList<Button> karakterButtons;
	private ArrayList<Karakter> karakters;
	private Pane pane;
	
	public KiesKarakterView() throws RemoteException
	{
		this.title = new Text();
		this.title.setId("title");
		this.title.setFill(Color.WHITE);
		this.title.setLayoutX(640);
		this.title.setLayoutY(50);
		this.title.setText("Kies een karakter:");
		
		this.karakterButtons = new ArrayList<Button>();
		this.karakters = new ArrayList<Karakter>();
		
		this.pane = new Pane();
		this.pane.setId("kieskarakterview");
		Rectangle rect = new Rectangle(1440, 900);
		pane.setClip(rect);
		this.pane.getStylesheets().add("Machiavelli/Resources/KiesKarakterView.css");
		
		this.pane.getChildren().add(title);
	}
	
	public ArrayList<Button> getButtonList()
	{
		return this.karakterButtons;
	}
	
	public ArrayList<Karakter> getKarakters()
	{
		return this.karakters;
	}
	
	public void createKarakterView(Karakter karakter) {
		Button newButton = new Button(karakter.getNaam());
//		newButton.setGraphic(new ImageView(karakter.getImage())); //TODO: set graphic later!

		newButton.setId("goudButton");
		newButton.setMinWidth(215f);
		newButton.setMinHeight(50f);
		this.karakterButtons.add(newButton);
	}
	
	public void addButtonsToView()
	{
		StackPane buttonLayout = new StackPane();
		buttonLayout.setPrefSize(1440, 900);
		
		int amountTop = (this.karakterButtons.size() / 2);
		int amountBottom = this.karakterButtons.size();
		
//		ImageView karakterImage = new ImageView(new Image("Machiavelli/Resources/placeholderimg.gif"));
////		karakterImage.setId("goudImg");
////		karakterImage.setLayoutX(300);
////		karakterImage.setLayoutY(200);
//		Rectangle karakterRect = new Rectangle(200, 200);
//		karakterRect.setArcHeight(200);
//		karakterRect.setArcWidth(200);
//		karakterImage.setClip(karakterRect);
		
		HBox kaartenBoxTop = new HBox();
//		kaartenBoxTop.setSpacing(1.0);
//		kaartenBoxTop.setPadding(new Insets(1, 1, 1, 1));
		
		HBox kaartenBoxBottom = new HBox();
//		kaartenBoxBottom.setSpacing(1.0);
//		kaartenBoxBottom.setPadding(new Insets(1, 1, 1, 1));
		
		VBox buttonOrder = new VBox();
		buttonOrder.setSpacing(160.0);
		buttonOrder.setPadding(new Insets(1, 1, 1, 1));
		
        
		for(int i = 0; i < amountTop; i++)
		{
			//TODO: get image from karakter (list) and place it in this image.
			Image image = new Image("Machiavelli/Resources/placeholderimg.gif");
			Button button = this.karakterButtons.get(i);
			kaartenBoxTop.getChildren().addAll(this.createGridItem(image, button));
		}
		
		for(int i = amountTop; i < amountBottom; i++)
		{
			//TODO: get image from karakter (list) and place it in this image.
			Image image = new Image("Machiavelli/Resources/placeholderimg.gif");
			Button button = this.karakterButtons.get(i);
			kaartenBoxBottom.getChildren().add(this.createGridItem(image, button));
		}
        
		buttonLayout.setLayoutY(160);
		buttonLayout.setLayoutX(160);
        
        buttonOrder.getChildren().addAll(kaartenBoxTop, kaartenBoxBottom);
        
        buttonLayout.getChildren().add(buttonOrder);
        
        this.pane.getChildren().add(buttonLayout);
	}
	
	private VBox createGridItem(Image image, Button button)
	{
//		ImageView karakterImage = new ImageView(new Image("Machiavelli/Resources/placeholderimg.gif"));
//		image = new ImageView("Machiavelli/Resources/placeholderimg.gif");
		ImageView karakterImage = new ImageView(image);
		Rectangle karakterRect = new Rectangle(214, 214);
		karakterRect.setArcHeight(214);
		karakterRect.setArcWidth(214);
		karakterImage.setClip(karakterRect);
		
		VBox gridItem = new VBox();
		gridItem.setSpacing(-150.0);
		gridItem.setPadding(new Insets(-40, -40, -40, -40));
		
		gridItem.getChildren().addAll(karakterImage, button);
		
		return gridItem;
	}
	
	public Pane getPane()
	{
		return this.pane;
	}
}
