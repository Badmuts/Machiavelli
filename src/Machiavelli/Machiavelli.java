package Machiavelli;

import Machiavelli.Controllers.MenuController;
import Machiavelli.Views.MainMenuView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Google Java Style Guide aanhouden
 */
public class Machiavelli extends Application {

    private static Machiavelli uniqueInstance;
    private Stage stage;

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

}
