package Machiavelli.Interfaces.Remotes;

import Machiavelli.Interfaces.Observers.PortemonneeOberserver;
import Machiavelli.Models.Bank;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PortemonneeRemote extends Remote {

    public void bestedenGoud(Bank bank, int aantal) throws RemoteException;
    public void ontvangenGoud(int aantal) throws RemoteException;
    public int getGoudMunten() throws RemoteException;
    public void addObserver(PortemonneeOberserver portemonneeOberserver) throws RemoteException;
    public void notifyObservers() throws RemoteException;

}
