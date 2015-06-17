package Machiavelli;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javafx.application.Application;
import javafx.stage.Stage;
import Machiavelli.Controllers.MenuController;
import Machiavelli.Views.MainMenuView;

/**
 * Google Java Style Guide aanhouden
 */
public class Machiavelli extends Application {

    private static Machiavelli uniqueInstance;
    private Stage stage;
    private Registry registry;

    public Machiavelli() {
        super();
        synchronized(Machiavelli.class){
            if(uniqueInstance != null) throw new UnsupportedOperationException(
                    getClass()+" is singleton but constructor called more than once");
            uniqueInstance = this;
        }
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        this.stage = primaryStage;
        // this.stage.initStyle(StageStyle.UNDECORATED);
        this.stage.setResizable(false);
        this.stage.setTitle("Machiavelli");
        MenuController menuController = new MenuController(new MainMenuView());

        try {
            System.out.println("Getting access to the registry");
            // get access to the RMI registry on the remote server
            registry = LocateRegistry.getRegistry("127.0.0.1"); // if server on another machine: provide that machine's IP address. Default port  1099
//            System.out.println("Getting the Games stub from registry");
//            System.out.println("Performing arithmetics");
            System.out.println("Done!");
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }

    public static synchronized Machiavelli getInstance() {
        return uniqueInstance;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Stage getStage() {
        return this.stage;
    }

    public Registry getRegistry() {
        return this.registry;
    }

}
