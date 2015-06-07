package Views;

import javafx.scene.control.Button;
import Controllers.SpelController;
import Models.Spel;

public class DeelnemenSpelView {
	private SpelController sc;
	private Button keuzespel1;
	private Button keuzespel2;
	private Button keuzespel3;
	private Button keuzespel4;
	private Button keuzespel5;
	private Button keuzespel6;
	private Button keuzespel7;
	private Button terugKnop;
	
	public void initButton(Button button,String tekst,int aantalspelers, String id, int posx, int posy, float sizeX, float sizeY){
		button.setText(tekst);
		button.setText(aantalspelers + "/7");
		button.setId(id);
		button.setLayoutX(posx);
		button.setLayoutY(posy);
		button.setMinWidth(sizeX);
		button.setMinHeight(sizeY);
	}
	public DeelnemenSpelView(SpelController sc, Spel sp){
		this.sc = sc;
		
		keuzespel1 = new Button();
		keuzespel2 = new Button();
		keuzespel3 = new Button();
		keuzespel4 = new Button();
		keuzespel5 = new Button();
		keuzespel6 = new Button();
		
		
		
		
	}
	
}
