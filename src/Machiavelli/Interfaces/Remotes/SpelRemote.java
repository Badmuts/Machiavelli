package Machiavelli.Interfaces.Remotes;

import Machiavelli.Factories.GebouwFactory;
import Machiavelli.Models.Bank;
import Machiavelli.Models.Speelveld;
import Machiavelli.Models.Speler;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by badmuts on 10-6-15.
 */
public interface SpelRemote extends Remote {

    public void NieuwSpel() throws RemoteException;
    public void EindeBeurt() throws RemoteException;
    public Bank getBank() throws RemoteException;
    public GebouwFactory getGebouwFactory() throws RemoteException;
    public void setAantalSpelers(int aantalspelers) throws RemoteException;
    public int getAantalSpelers() throws RemoteException;

}
