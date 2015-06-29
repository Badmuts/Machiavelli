package Machiavelli.Views;

import java.rmi.RemoteException;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Machiavelli.Machiavelli;
import Machiavelli.Controllers.SpelController;
import Machiavelli.Models.Spel;

public class HervattenSpelView {
    private SpelController sc;
    private Scene deelnemenscene;
    private Button keuzespel1;
    private Button keuzespel2;
    private Button keuzespel3;
    private Button keuzespel4;
    private Button keuzespel5;
    private Button keuzespel6;
    private Button terugknop;
    private Button spelregels;
	private Stage stage = Machiavelli.getInstance().getStage();
		
	public HervattenSpelView(SpelController sc, Spel sp){
        this.sc = sc;

        keuzespel1 = new Button();
        keuzespel2 = new Button();
        keuzespel3 = new Button();
        keuzespel4 = new Button();
        keuzespel5 = new Button();
        keuzespel6 = new Button();

        spelregels = new Button("Spelregels");
        spelregels.setId("buttonregels");
        spelregels.setLayoutX(15);
        spelregels.setLayoutY(10);
        spelregels.setMinWidth(125);
        spelregels.setMinHeight(50);

        terugknop = new Button("Terug");
        terugknop.setLayoutX(700);
        terugknop.setLayoutY(540);
        terugknop.setMinWidth(200);
        terugknop.setMinHeight(50);
        terugknop.setId("buttonexit");

        Image spelregelsbg = new Image("Machiavelli/Resources/SpelregelsBorder.png");
        ImageView iv = new ImageView(spelregelsbg);
        iv.setCache(true);
        iv.setFitWidth(200);

        Text mainTx = new Text("Machiavelli");
        mainTx.setFill(Color.WHITE);
        DropShadow ds = new DropShadow();
        ds.setOffsetY(2);
        mainTx.setEffect(ds);
        mainTx.setScaleX(7);
        mainTx.setScaleY(6.5);
        mainTx.setLayoutX(780);
        mainTx.setLayoutY(170);

        try {
            initButton(keuzespel1, "Spel 1", sp.getAantalSpelers(), "gamekiezen", 225, 270, 550f, 50f );
            initButton(keuzespel2, "Spel 2", sp.getAantalSpelers(), "gamekiezen", 225, 350, 550f, 50f );
            initButton(keuzespel3, "Spel 3", sp.getAantalSpelers(), "gamekiezen", 225, 430, 550f, 50f );
            initButton(keuzespel4, "Spel 4", sp.getAantalSpelers(), "gamekiezen", 825, 270, 550f, 50f );
            initButton(keuzespel5, "Spel 5", sp.getAantalSpelers(), "gamekiezen", 825, 350, 550f, 50f );
            initButton(keuzespel6, "Spel 6", sp.getAantalSpelers(), "gamekiezen", 825, 430, 550f, 50f );
        } catch (RemoteException re) {
            System.out.print(re);
        }

        Pane deelnemenpane = new Pane();
        deelnemenpane.getChildren().addAll(mainTx, keuzespel1,keuzespel2,keuzespel3,keuzespel4,keuzespel5,keuzespel6,terugknop, iv,spelregels);
        deelnemenscene = new Scene(deelnemenpane, 1600, 900);
        deelnemenpane.getStylesheets().add("Machiavelli/Resources/Menu.css");
    }

    public void initButton(Button button,String tekst,int aantalspelers, String id, int posx, int posy, float sizeX, float sizeY){
        button.setText(tekst + "\t\t\t\t\t" + aantalspelers + "/7");
        button.setId(id);
        button.setLayoutX(posx);
        button.setLayoutY(posy);
        button.setMinWidth(sizeX);
        button.setMinHeight(sizeY);
    }

    public void show(){
        stage.setScene(deelnemenscene);
        stage.show();
    }

    public Button getTerugKnop(){
        return terugknop;
    }

}
