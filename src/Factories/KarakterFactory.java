package Factories;

import Interfaces.Karakter;
import Models.Karakters.*;

import java.util.ArrayList;
import java.util.Random;

public class KarakterFactory {

    private ArrayList<Karakter> karakters = new ArrayList<>();

    public KarakterFactory() {
        this.karakters.add(new Moordenaar());
        this.karakters.add(new Dief());
        this.karakters.add(new Magier());
        this.karakters.add(new Koning());
        this.karakters.add(new Prediker());
        this.karakters.add(new Koopman());
        this.karakters.add(new Bouwmeester());
        this.karakters.add(new Condotierre());
    }

    public ArrayList<Karakter> getKarakters() {
        return this.karakters;
    }

}
