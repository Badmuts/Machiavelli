package Machiavelli.Views;

import java.rmi.RemoteException;
import java.util.ArrayList;

import Machiavelli.Factories.KarakterFactory;
import Machiavelli.Interfaces.Karakter;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

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
		this.title.setLayoutX(660);
		this.title.setLayoutY(50);
		this.title.setText("Kies een karakter:");
		
		this.karakterButtons = new ArrayList<Button>();
		this.karakters = new ArrayList<Karakter>();
		
		this.pane = new Pane();
		this.pane.setId("kieskarakterview");
		Rectangle rect = new Rectangle(1600, 900);
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

//		this.karakters.add(karakter);

		newButton.setId("goudButton");
		this.karakterButtons.add(newButton);
//		this.pane.getChildren().add(newButton);
	}
	
	public void addButtonsToView()
	{
		HBox hBox = HBoxBuilder.create()
                .spacing(5.0) //In case you are using HBoxBuilder
                .padding(new Insets(1, 1, 1, 1))
                .build();

        hBox.setSpacing(5.0);
        
        for(Button button : this.karakterButtons)
        {
        	hBox.getChildren().add(button);
        }
        
        hBox.setLayoutY(120);
        hBox.setLayoutX(12);
        this.pane.getChildren().add(hBox);
	}
	
	public Pane getPane()
	{
		return this.pane;
	}
}
