package Machiavelli.Controllers;

import Machiavelli.Interfaces.Remotes.GebouwKaartRemote;
import Machiavelli.Models.GebouwKaart;
import Machiavelli.Views.GebouwKaartView;

/**
 * Created by badmuts on 14-6-15.
 */
public class GebouwKaartController {
    private final GebouwKaartView gebouwKaartView;
    private final GebouwKaartRemote gebouwKaart;

    public GebouwKaartController(GebouwKaartView gebouwKaartView, GebouwKaartRemote gebouwKaart) {
        this.gebouwKaartView = gebouwKaartView;
        this.gebouwKaart = gebouwKaart;
    }
}
