package Machiavelli.Controllers;

import java.rmi.registry.Registry;

import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Machiavelli;
import Machiavelli.Models.Speelveld;
import Machiavelli.Views.InvullenSpelersView;
import Machiavelli.Views.MainMenuView;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Views.InvullenSpelersView;
import Machiavelli.Views.MainMenuView;

public class MenuController {

    private MainMenuView mainMenuView;
    private InvullenSpelersView invullenspeler;
    private SpelController spelController;
    private Registry registry;
    private Speelveld speelveld;

    /**
     * Maakt de MainMenuView aan en koppelt de buttons aan cmd's
     * zodat deze kunnen worden afgehandeld.
     *
     */
    public MenuController() {
        this.mainMenuView = new MainMenuView(this);
        this.registry = Machiavelli.getInstance().getRegistry();
        System.out.println("Loaded Registry");

        // Start het overzicht met spelknoppen (Nieuw spel, Deelnemen spel en Hervatten spel)
        mainMenuView.getStartButton().setOnAction(event -> mainMenuView.showSelect());

        // Sluit applicatie af bij exit buttons
        mainMenuView.getExitButton().setOnAction(event -> System.exit(0));
        mainMenuView.getExitButton2().setOnAction(event -> System.exit(0));

        // Handel spel commands af
        mainMenuView.getNieuwSpelKnop().setOnAction(event -> this.cmdNieuwSpel());
        mainMenuView.getHervattenknop().setOnAction(event -> this.cmdHervattenSpel());
        mainMenuView.getDeelnemenKnop().setOnAction(event -> this.cmdDeelnemenSpel());

        // Raadpleeg de spelregels
        mainMenuView.getSpelregelsButton().setOnAction(event -> new RaadplegenSpelregelsController().cmdWeergeefSpelregels());
        mainMenuView.getSpelregelsButton2().setOnAction(event -> new RaadplegenSpelregelsController().cmdWeergeefSpelregels());

        // Start de MainMenuView
        this.cmdMainMenu();
    }

    /**
     * Handelt het event af voor de 'Nieuw Spel' knop. Start de
     * InvullenSpelersView om te vragen met hoeveel spelers er
     * gespeeld gaat worden.
     */
    public void cmdNieuwSpel() {
        this.invullenspeler = new InvullenSpelersView(this);
        invullenspeler.getTerugButton().setOnAction(event -> this.cmdMainMenu());
        invullenspeler.getOkButton().setOnAction(event -> this.cmdInvullenSpelersStartNewGame());
        this.invullenspeler.show();
    }

    /**
     * Handelt het event af voor de 'Deelnemen spel' knop.
     */
    public void cmdDeelnemenSpel() {
        // TODO: Show new games
    	try{
    		SpelRemote spelStub = (SpelRemote)registry.lookup("Spel");
            if (spelStub.getAantalSpelers() < spelStub.getMaxAantalSpelers()) {
                if (spelStub.getTempSpelers().size() != 0) {
                    spelStub.getSpelers().add(spelStub.getTempSpelers().get(0));
                    spelStub.getTempSpelers().remove(0);
                }
                else {
                    spelStub.createNewSpeler();
                }
                this.spelController = new SpelController(spelStub);
            } else {
                new MeldingController().build("Het maximaal aantal spelers is bereikt").cmdWeergeefMeldingView();
            }
    	} catch(Exception re) {
    		re.printStackTrace();
    	}
    }

    /**
     * Handelt het event af voor de 'Hervatten spel' knop. Start het
     * overzicht met hervatbare spellen.
     */
    public void cmdHervattenSpel() {
        // Spel inladen vanuit default locatie.
        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.home") + "/machiavelli/machiavelli.sav");
            ObjectInputStream ois = new ObjectInputStream(fis);

            SpelRemote loadSpel = (SpelRemote)ois.readObject();
            SpelRemote spelStub = (SpelRemote)this.registry.lookup("Spel");
            spelStub.laadSpel(loadSpel);
            spelStub.setTempSpelers(spelStub.getSpelers());
            spelStub.getSpelers().clear();
            spelStub.getSpelers().add(spelStub.getTempSpelers().get(0));
            spelStub.getTempSpelers().remove(0);
            spelStub.setSpelers(spelStub.getTempSpelers());
            // aantal spelers op grootte van de tempspelerlist zetten

            this.spelController = new SpelController(spelStub);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handelt het event af wanneer de gebruiker een aantal spelers
     * heeft ingevuld.
     */
    public void cmdInvullenSpelersStartNewGame() {
        // TODO: Create new spel instance?
    	try {
    		int maxAantalSpelers = Integer.parseInt(this.invullenspeler.getTextField());
            SpelRemote spelStub = (SpelRemote)this.registry.lookup("Spel");
            spelStub.createNewSpel(maxAantalSpelers);
            spelStub.createNewSpeler();
            this.spelController = new SpelController(spelStub);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    /**
     * Start de MainMenuView
     */
    public void cmdMainMenu() {
        mainMenuView.show();
    }

}
