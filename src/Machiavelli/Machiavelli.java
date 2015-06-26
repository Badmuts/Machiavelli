package Machiavelli;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javafx.application.Application;
import javafx.stage.Stage;
import Machiavelli.Controllers.MenuController;

/**
 * Google Java Style Guide aanhouden
 *
 * @author Daan Rosbergen
 */

public class Machiavelli extends Application {

    private static Machiavelli uniqueInstance;
    private Stage stage;
    private Registry registry;

    /**
     * Machiavelli constructor. Is een aangepaste singleton ivm. JavaFX
     */
    public Machiavelli() {
        super();
        synchronized(Machiavelli.class){
            if(uniqueInstance != null) throw new UnsupportedOperationException(
                    getClass()+" is singleton but constructor called more than once");
            uniqueInstance = this;
        }
    }

    /**
     * Start method van applicatie. Start applicatie, verbind met server
     * en start het menu.
     *
     * @param primaryStage
     * @throws Exception
     */
	@Override
	public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage; // Create stage
        // this.stage.initStyle(StageStyle.UNDECORATED);
        this.stage.setResizable(false); // Make stage unresizable
        this.stage.setTitle("Machiavelli"); // Set title of stage

        try {
            System.out.println("Getting access to the registry");
            // get access to the RMI registry on the remote server
            this.registry = LocateRegistry.getRegistry("145.101.81.59"); // if server on another machine: provide that machine's IP address. Default port  1099
            System.out.println("Done!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        new MenuController(); // Start menu
    }

    /**
     * Singleton instance van applicaitie
     * @return Machiavelli
     */
    public static synchronized Machiavelli getInstance() {
        return uniqueInstance;
    }

    /**
     * Main method, start client applicatie
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Stage van applicatie
     * @return Stage
     */
    public Stage getStage() {
        return this.stage;
    }

    /**
     * Registry van applicatie (verbinding met server)
     * @return Registry
     */
    public Registry getRegistry() {
        return this.registry;
    }
}
