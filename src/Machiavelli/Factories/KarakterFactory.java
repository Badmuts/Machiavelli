package Machiavelli.Factories;

import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Observers.KarakterFactoryObserver;
import Machiavelli.Interfaces.Remotes.KarakterFactoryRemote;
import Machiavelli.Models.Karakters.*;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class KarakterFactory implements KarakterFactoryRemote, Serializable {
    /**
     * ArrayList om alle karakters op te slaan
     */
    private ArrayList<Karakter> karakters = new ArrayList<Karakter>();
    private ArrayList<KarakterFactoryObserver> observers = new ArrayList<>();

    /**
     * Maakt alle karakters aan (in dit geval 8)
     */
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

    /**
     * Haal de lijst met karakters op die zich
     * op dit moment bevinden in de stapel.
     *
     * @return ArrayList<Karakter>
     */
    public ArrayList<Karakter> getKarakters() throws RemoteException {
        return this.karakters;
    }

    /**
     * Trek een karakter van de karakter stapel met het
     * nummer van de karakter als parameter. Elk karakter heeft zijn
     * eigen nummer. Deze bevind zich in de Karakter klasse.
     *
     * Ga uit van het Karakternummer zoals is aangegeven op de
     * karakterkaart. Moodernaar = 1, Dief = 2 etc.
     *
     * Deze methode geeft als response een Karakter. Dat karakter
     * word ook verwijderd uit de lijst.
     *
     * @param karakterNummer
     * @return
     */
    public Karakter getKarakterByNumber(int karakterNummer) throws RemoteException {
        Karakter tmpKarakter = null;
        for (Karakter karakter: karakters) {
            if (karakter.getNummer() == karakterNummer)
                karakters.remove(karakter);
                tmpKarakter = karakter;
        }
        return tmpKarakter;
    }

    public void addObserver(KarakterFactoryObserver karakterFactoryObserver) throws RemoteException {
        observers.add(karakterFactoryObserver);
    }

    public void notifyObservers() throws RemoteException {
        for (KarakterFactoryObserver observer: observers) {
            observer.modelChanged(this);
        }
    }

}
