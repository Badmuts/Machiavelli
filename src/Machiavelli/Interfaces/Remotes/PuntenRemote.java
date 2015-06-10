package Machiavelli.Interfaces.Remotes;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Machiavelli.Interfaces.Observers.PuntenObserver;
import Machiavelli.Models.Speler;

public interface PuntenRemote extends Remote {
	
	public Speler berekenWinnaar() throws RemoteException;
    public void addObserver(PuntenObserver puntenObserver) throws RemoteException;

}
