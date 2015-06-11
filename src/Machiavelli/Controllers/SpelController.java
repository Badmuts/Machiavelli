package Machiavelli.Controllers;

import Machiavelli.Machiavelli;
import Machiavelli.Models.Spel;
import Machiavelli.Models.Spelregels;
import Machiavelli.Views.DeelnemenSpelView;
import Machiavelli.Views.HervattenSpelView;
import Machiavelli.Views.InvullenSpelersView;
import Machiavelli.Views.MainMenuView;
import javafx.stage.Stage;

import java.rmi.RemoteException;

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

		Spelregels r = new Spelregels();
		this.mmv = new MainMenuView();


		invullenspeler.getOkButton().setOnAction(event -> cmdNieuwSpel());
		invullenspeler.getTerugButton().setOnAction(event -> mmv.showSelect());
		hervattenspel.getTerugKnop().setOnAction(event -> mmv.showSelect());

		deelnemenview.getTerugKnop().setOnAction(event -> mmv.showSelect());
		
	}
	public void show() {
		mmv.show();
	}

	public void cmdSetAantalSpelers(int spelers) {

	}
}

