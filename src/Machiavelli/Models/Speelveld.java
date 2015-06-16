package Machiavelli.Models;

import Machiavelli.Controllers.SpeelveldController;
import Machiavelli.Interfaces.Karakter;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import javafx.stage.Stage;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import Machiavelli.Interfaces.Observers.SpeelveldObserver;
import Machiavelli.Interfaces.Remotes.SpeelveldRemote;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Views.SpeelveldView;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;




public class Speelveld implements SpeelveldRemote, Serializable {
    private SpeelveldView speelveldView;
	private ArrayList<Speler> spelers;
	private SpelRemote spel;
	private Speler koning;
	private Karakter karakter;
	private SpeelveldController speelveldcontroller;
	private Speler speler;
	private ArrayList<SpeelveldObserver> observers = new ArrayList<>();

	public Speelveld(SpelRemote spel, Speler speler) {
		//Spelers koppeln aan speelveld
		//Start spelers is koning
		//Starten karakterkiezenlijst speler 1
		//Doorgeven karakterlijst aan andere spelers
		//this.spelers = spel.getSpelers();
		this.speler = speler;
        this.spel = spel;
        this.speelveldView = speelveldView;
//		try {
//			this.setKoning(spelers.get(0));
//		} catch (RemoteException re) {
//			re.printStackTrace();
//		}
	}

	public void setKoning(Speler spelers) throws RemoteException {
		this.koning = spelers;
        notifyObservers();
	}

	public void toonKarakterLijst() throws RemoteException {
		
	}

	@Override
	public void addObserver(SpeelveldObserver observer) throws RemoteException {
		observers.add(observer);
	}

	public void notifyObservers() throws RemoteException {
		for (SpeelveldObserver observer: observers) {
			observer.modelChanged(this);
		}
	}

	public void registratieView(SpeelveldView speelveldview) {
		this.speelveldView = speelveldview;
	}

    public Speler getSpeler() {
        return this.speler;
    }
}