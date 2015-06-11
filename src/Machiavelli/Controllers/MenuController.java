package Machiavelli.Controllers;

import Machiavelli.Views.InvullenSpelersView;
import Machiavelli.Views.MainMenuView;

public class MenuController {

    private MainMenuView mainMenuView;
    private InvullenSpelersView invullenspeler;

    public MenuController(MainMenuView mainMenuView) {
        this.mainMenuView = mainMenuView;

//        this.deelnemenview = new DeelnemenSpelView(this,sp);
        this.invullenspeler = new InvullenSpelersView();
//        this.hervattenspel = new HervattenSpelView(this,sp);
//        this.spelregelsController = new RaadplegenSpelregelsController();

        mainMenuView.getStartButton().setOnAction(event -> mainMenuView.showSelect());
        mainMenuView.getExitButton().setOnAction(event -> System.exit(0));
        mainMenuView.getExitButton2().setOnAction(event -> System.exit(0));
        mainMenuView.getNieuwSpelKnop().setOnAction(event -> invullenspeler.show());
//        mainMenuView.getHervattenknop().setOnAction(event -> hervattenspel.show());
//        mainMenuView.getDeelnemenKnop().setOnAction(event -> deelnemenview.show());
//        mainMenuView.getSpelregelsButton().setOnAction(event -> this.spelregelsController.cmdWeergeefSpelregels());
    }

}
