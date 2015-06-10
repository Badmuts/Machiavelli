package Machiavelli.Interfaces.Remotes;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Machiavelli.Models.Speler;

public interface PuntenRemote extends Remote {
	
	public Speler berekenWinnaar() throws RemoteException;

}
