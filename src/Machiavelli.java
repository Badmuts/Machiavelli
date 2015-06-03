import javafx.application.Application;
import javafx.stage.Stage;
import Controllers.SpelController;
import Models.Spel;

/**
 * Created by daanrosbergen on 28/05/15.
 */
public class Machiavelli extends Application {


	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
    	System.out.println("Machiavelli");
        Spel sp = new Spel(primaryStage);
        SpelController sc = new SpelController(sp);
        sc.show();
	}

    public static void main(String[] args) {
        launch(args);
    }
}
