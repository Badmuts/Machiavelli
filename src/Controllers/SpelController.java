package Controllers;

import Models.Spel;
import Views.DeelnemenSpelView;
import Views.InvullenSpelersView;
import Views.MainMenuView;

public class SpelController{
	private MainMenuView mmv;
	private Spel spel;
	private RaadplegenSpelregelsController spelregelsController;
	private DeelnemenSpelView deelnemenview;
	private InvullenSpelersView invullenspeler;

	
	public SpelController(Spel sp){
		this.spel = sp;
		this.mmv = new MainMenuView (this,sp);
		this.deelnemenview = new DeelnemenSpelView(this,sp);
		this.invullenspeler = new InvullenSpelersView(this,sp);
		
		mmv.getStartButton().setOnAction(event -> mmv.showSelect(spel.getPrimaryStage()));
		mmv.getExitButton().setOnAction(event -> System.exit(0));
		mmv.getExitButton2().setOnAction(event -> System.exit(0));
		invullenspeler.getOkButton().setOnAction(event -> cmdNieuwSpel());
		invullenspeler.getTerugButton().setOnAction(event -> mmv.showSelect(spel.getPrimaryStage()));
		mmv.getNieuwSpelKnop().setOnAction(event -> invullenspeler.show(spel.getPrimaryStage()));
		mmv.getHervattenknop().setOnAction(event -> System.out.println("spel hervatten"));
		mmv.getDeelnemenKnop().setOnAction(event -> deelnemenview.show(spel.getPrimaryStage()));
		mmv.getSpelregelsButton().setOnAction(event -> this.spelregelsController.cmdWeergeefSpelregels());
		deelnemenview.getTerugKnop().setOnAction(event -> mmv.showSelect(spel.getPrimaryStage()));
		mmv.getDeelnemenKnop().setOnAction(event -> System.out.println("Deelnemen"));
		
	}
	public void show(){
		mmv.show(spel.getPrimaryStage());
	}
	/*public void show2(){
		mmv.show(spel.getPrimaryStage());
	}*/

	public void cmdAantalSpelers(){
	}
	public void cmdNieuwSpel(){
		//Create nieuw spel
		
		//Aantal spelers bepalen
		spel.setAantalSpelers(Integer.parseInt(invullenspeler.getTextField()));
		System.out.println(spel.getAantalSpelers());
		//Speelveld laden
		this.spel.NieuwSpel();
		//Spelers koppeln aan speelveld
		//Start spelers is koning
		//Starten karakterkiezenlijst speler 1
		//Doorgeven karakterlijst aan andere spelers
		mmv.StopStage(spel.getPrimaryStage());
		
	}
}

