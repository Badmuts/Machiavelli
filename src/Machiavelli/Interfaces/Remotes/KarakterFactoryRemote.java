package Machiavelli.Interfaces.Remotes;

import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Observers.KarakterFactoryObserver;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by badmuts on 18-6-15.
 */
public interface KarakterFactoryRemote extends Remote {

    /**
     * Haal de lijst met karakters op die zich
     * op dit moment bevinden in de stapel.
     *
     * @return ArrayList<Karakter>
     */
    public ArrayList<Karakter> getKarakters() throws RemoteException;

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
    public Karakter getKarakterByNumber(int karakterNummer) throws RemoteException;
    public void addObserver(KarakterFactoryObserver karakterFactoryObserver) throws RemoteException;
    public void notifyObservers() throws RemoteException;

}
