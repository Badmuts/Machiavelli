package Models;

import java.util.ArrayList;

/**
 * Created by daanrosbergen on 03/06/15.
 */
public class Stad {

    private ArrayList<GebouwKaart> gebouwen = new ArrayList<GebouwKaart>();
    private GebouwFactory gebouwFactory;
    private StadView stadView;

    public ArrayList<GebouwKaart> getGebouwen() {
        return this.gebouwen;
    }

    public void addGebouw(GebouwKaart gebouw) {
        this.gebouwen.add(gebouw);
    }

    public void removeGebouw(GebouwKaart gebouw) {
        this.gebouwen.remove(gebouw);
        // Plaats gebouwkaart terug op gebouwenstapel (GebouwFactory)
        this.gebouwFactory.addGebouw(gebouw);
        // Update StadView
        this.stadView.modelChanged();
    }

}
