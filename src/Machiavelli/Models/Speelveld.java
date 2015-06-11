package Machiavelli.Models;

import Machiavelli.Controllers.SpeelveldController;
import Machiavelli.Interfaces.Karakter;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import javafx.stage.Stage;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "Speelveld")
public class Speelveld {
	private Stage secondaryStage;
	private ArrayList<Speler> spelers;
	private Speler koning;
	private Karakter karakter;
	@XStreamOmitField
	private SpeelveldController speelveldcontroller;

	public Speelveld(ArrayList<Speler> spelers){
		//Spelers koppeln aan speelveld
		//Start spelers is koning
		//Starten karakterkiezenlijst speler 1
		//Doorgeven karakterlijst aan andere spelers
		this.spelers = spelers;
		this.setKoning(spelers.get(0));		
		//speelveldview = new SpeelveldView(new SpeelveldController(this),this,new KarakterController());
		speelveldcontroller = new SpeelveldController(this);

	}

	public void setKoning(Speler spelers) {
		this.koning = spelers;
	}

	public void toonKarakterLijst() {
		
	}
	
	
}

