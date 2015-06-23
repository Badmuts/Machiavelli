package Machiavelli.Interfaces.Remotes;

import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Observers.SpelerObserver;
import Machiavelli.Models.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by badmuts on 18-6-15.
 */
public interface SpelerRemote extends Remote {

    // Haalt goud van de bank en zet het in de portemonnee
    public void getGoudVanBank(Bank bank, int aantal) throws RemoteException;
    // Haalt goud uit de portemonnee en geeft dit aan de bank
    public void setGoudOpBank(Portemonnee portemonnee, int aantal) throws RemoteException;
    // Plaats een gebouwkaart in de stad van de speler
    public void bouwenGebouw(GebouwKaart gebouw) throws RemoteException;
    // Selecteren van een kaart aan de hand van de getrokken kaarten
    public void selecterenKaart(ArrayList<GebouwKaart> lijst, int index) throws RemoteException;
    public void addObserver(SpelerObserver observer) throws RemoteException;
    public void notifyObservers() throws RemoteException;
    public void addSpel(SpelRemote spel) throws RemoteException;
    public void setKarakter(Karakter karakter) throws RemoteException;
    public Karakter getKarakter() throws RemoteException;
    public SpelRemote getSpel() throws RemoteException;
    public Portemonnee getPortemonnee() throws RemoteException;
    public Hand getHand() throws RemoteException;
    public Stad getStad() throws RemoteException;
    // Trekken van een x aantal kaarten van de stapel
    public ArrayList<GebouwKaart> trekkenKaart(int aantal) throws RemoteException;
    // Trekken van twee kaarten uit de stapel
    public ArrayList<GebouwKaart> trekkenKaart() throws RemoteException;

}
