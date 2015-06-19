package Machiavelli.Views;

import java.rmi.RemoteException;
import java.time.Duration;
import java.util.ArrayList;

import com.sun.corba.se.impl.oa.poa.AOMEntry;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
	private ArrayList<Image> karakterImages;
	private Pane pane;
	
	public KiesKarakterView() throws RemoteException
	{
		this.title = new Text();
		this.title.setId("title");
		this.title.setFill(Color.WHITE);
		this.title.setLayoutX(590);
		this.title.setLayoutY(50);
		this.title.setText("Kies een karakter:");
		
		this.karakterButtons = new ArrayList<Button>();
		this.karakterImages = new ArrayList<Image>();
		
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
	
	public void createKarakterView(Karakter karakter) {
		Button newButton = new Button(karakter.getNaam());
		this.karakterImages.add(karakter.getImage());

		newButton.setId("goudButton");
		newButton.setMinWidth(230f);
		newButton.setMinHeight(50f);
		this.karakterButtons.add(newButton);
	}
	
	public void addButtonsToView()
	{
		StackPane buttonLayout = new StackPane();
		buttonLayout.setPrefSize(1440, 900);
		
		int amountTop = (this.karakterButtons.size() / 2);
		int amountBottom = this.karakterButtons.size();
		
		HBox kaartenBoxTop = new HBox();
		kaartenBoxTop.setSpacing(180.0);
		kaartenBoxTop.setPadding(new Insets(1, 1, 1, 1));
		
		HBox kaartenBoxBottom = new HBox();
		kaartenBoxBottom.setSpacing(180.0);
		kaartenBoxBottom.setPadding(new Insets(1, 1, 1, 1));
		
		VBox buttonOrder = new VBox();
		buttonOrder.setSpacing(230.0);
		buttonOrder.setPadding(new Insets(1, 1, 1, 1));
        
		for(int i = 0; i < amountTop; i++)
		{
			Image image = karakterImages.get(i);
			Button button = this.karakterButtons.get(i);
			kaartenBoxTop.getChildren().addAll(this.createGridItem(image, button));
		}
		
		for(int i = amountTop; i < amountBottom; i++)
		{
			Image image = karakterImages.get(i);
			Button button = this.karakterButtons.get(i);
			kaartenBoxBottom.getChildren().add(this.createGridItem(image, button));
		}
        
		buttonLayout.setLayoutY(200);
		buttonLayout.setLayoutX(140);
        
        buttonOrder.getChildren().addAll(kaartenBoxTop, kaartenBoxBottom);
        
        buttonLayout.getChildren().add(buttonOrder);
        
        this.pane.getChildren().add(buttonLayout);
	}
	
	private VBox createGridItem(Image image, Button button)
	{
		double imageWidth = image.getWidth();
		double imageHeight = image.getHeight();
		
		ImageView karakterImage = new ImageView(image);
		Rectangle karakterRect = new Rectangle(imageWidth, imageHeight);
		karakterRect.setArcHeight(imageHeight);
		karakterRect.setArcWidth(imageWidth);
		karakterImage.setClip(karakterRect);
		
		VBox gridItem = new VBox();
		gridItem.setSpacing(30.0);
		gridItem.setPadding(new Insets(-40, -40, -40, -40));
		
		gridItem.setAlignment(Pos.CENTER);
		
		gridItem.getChildren().addAll(karakterImage, button);
		
		return gridItem;
	}
	
	public Pane getPane()
	{
		return this.pane;
	}
}
