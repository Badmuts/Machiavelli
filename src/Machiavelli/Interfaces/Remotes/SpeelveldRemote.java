package Machiavelli.Interfaces.Remotes;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Machiavelli.Models.Speler;

public interface SpeelveldRemote extends Remote{
	public void setKoning(Speler speler) throws RemoteException;
	public void toonKarakterLijst() throws RemoteException;
}
