package Machiavelli.Interfaces.Remotes;

import Machiavelli.Interfaces.Observers.PortemonneeOberserver;
import Machiavelli.Models.Bank;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by badmuts on 18-6-15.
 */
public interface PortemonneeRemote extends Remote {

    // Goud aan de bank betalen
    public boolean bestedenGoud(BankRemote bank, int aantal) throws RemoteException;
    // Ontvangen van een x aantal goud
    public void ontvangenGoud(int aantal) throws RemoteException;
    public int getGoudMunten() throws RemoteException;
    public void addObserver(PortemonneeOberserver observer) throws RemoteException;
    public void notifyObservers() throws RemoteException;

}
