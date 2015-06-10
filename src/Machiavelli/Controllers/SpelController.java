package Machiavelli.Controllers;

import Machiavelli.Machiavelli;
import Machiavelli.Models.Spel;
import Machiavelli.Models.Spelregels;
import Machiavelli.Views.DeelnemenSpelView;
import Machiavelli.Views.HervattenSpelView;
import Machiavelli.Views.InvullenSpelersView;
import Machiavelli.Views.MainMenuView;
import javafx.stage.Stage;
 
/**
 * 
 * Deze klasse bestuurt de view en het model van het spel( met name het hoofdmenu en het opstarten van het spel )
 *
 */

public class SpelController{
	private MainMenuView mmv;
	private Spel spel;
	private RaadplegenSpelregelsController spelregelsController;
	private DeelnemenSpelView deelnemenview;
	private InvullenSpelersView invullenspeler;
	private HervattenSpelView hervattenspel;
	private Stage stage = Machiavelli.getInstance().getStage();
	
	public SpelController(Spel sp){
		this.spel = sp;
		this.spelregelsController = new RaadplegenSpelregelsController();
		Spelregels r = new Spelregels();
		this.mmv = new MainMenuView (this,sp);
		this.deelnemenview = new DeelnemenSpelView(this,sp);
		this.invullenspeler = new InvullenSpelersView(this,sp);
		this.hervattenspel = new HervattenSpelView(this,sp);
		
		mmv.getStartButton().setOnAction(event -> mmv.showSelect());
		mmv.getExitButton().setOnAction(event -> System.exit(0));
		mmv.getExitButton2().setOnAction(event -> System.exit(0));
		invullenspeler.getOkButton().setOnAction(event -> cmdNieuwSpel());
		invullenspeler.getTerugButton().setOnAction(event -> mmv.showSelect());
		hervattenspel.getTerugKnop().setOnAction(event -> mmv.showSelect());
		mmv.getNieuwSpelKnop().setOnAction(event -> invullenspeler.show());
		mmv.getHervattenknop().setOnAction(event -> hervattenspel.show());
		mmv.getDeelnemenKnop().setOnAction(event -> deelnemenview.show());
		mmv.getSpelregelsButton().setOnAction(event -> this.spelregelsController.cmdWeergeefSpelregels());
		deelnemenview.getTerugKnop().setOnAction(event -> mmv.showSelect());
		
	}
	public void show() {
		mmv.show();
	}

	public void cmdAantalSpelers() {
	}

	public void cmdNieuwSpel() {
		//Create nieuw spel
		//Aantal spelers bepalen
		//Nieuw spel starten
		try {
			spel.setAantalSpelers(Integer.parseInt(invullenspeler.getTextField()));
		} catch(Exception e) {
			System.out.println("Vul een getal in tussen de 2 en de 7.(Geen letters of speciale tekens");
		}
		//testen hoeveel spelers er zijn
		System.out.println(spel.getAantalSpelers());
		spel.NieuwSpel();
		//Spelers koppeln aan speelveld
		//Start spelers is koning
		//Starten karakterkiezenlijst speler 1
		//Doorgeven karakterlijst aan andere spelers
	}
}

