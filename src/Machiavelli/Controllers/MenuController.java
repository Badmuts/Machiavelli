package Machiavelli.Controllers;

import Machiavelli.Machiavelli;
import Machiavelli.Models.Spel;
import Machiavelli.Models.Speler;
import Machiavelli.Views.InvullenSpelersView;
import Machiavelli.Views.MainMenuView;

import java.rmi.registry.Registry;

public class MenuController {

    private MainMenuView mainMenuView;
    private InvullenSpelersView invullenspeler;
    private SpelController spelController;
    private Registry registry = Machiavelli.getInstance().getRegistry();

    /**
     * Maakt de MainMenuView aan en koppelt de buttons aan cmd's
     * zodat deze kunnen worden afgehandeld.
     *
     * @param mainMenuView
     */
    public MenuController(MainMenuView mainMenuView) {
        this.mainMenuView = mainMenuView;

        // Start het overzicht met spellen (Nieuw spel, Deelnemen spel en Hervatten spel)
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
    }

    /**
     * Handelt het event af voor de 'Hervatten spel' knop. Start het
     * overzicht met hervatbare spellen.
     */
    public void cmdHervattenSpel() {
        // TODO: Show resumable games
    }

    /**
     * Handelt het event af wanneer de gebruiker een aantal spelers
     * heeft ingevuld.
     */
    public void cmdInvullenSpelersStartNewGame() {
        // TODO: Create new spel instance?
        int maxAantalSpelers = Integer.parseInt(this.invullenspeler.getTextField());

        this.spelController = new SpelController(new Spel(maxAantalSpelers));
        this.spelController.cmdAddSpeler(new Speler());
    }

    /**
     * Start de MainMenuView
     */
    public void cmdMainMenu() {
        mainMenuView.show();
    }

}
