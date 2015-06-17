package Machiavelli.Views;

import java.rmi.RemoteException;
import java.util.ArrayList;

import Machiavelli.Factories.KarakterFactory;
import Machiavelli.Interfaces.Karakter;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class KiesKarakterView {
	
	private Text title;
	private ArrayList<Button> karakterButtons;
	private ArrayList<Karakter> karakters;
	private KarakterFactory factory;
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
		this.factory = new KarakterFactory();
		this.karakters = factory.getKarakters();
		
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
		newButton.setLayoutX(Math.random() * 111);
		newButton.setLayoutY(50);
		this.karakters.add(karakter);

		newButton.setId("goudButton");
		this.karakterButtons.add(newButton);
		this.pane.getChildren().add(newButton);
	}
	
	public Pane getPane()
	{
		return this.pane;
	}
}
