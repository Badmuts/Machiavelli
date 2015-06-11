package Machiavelli.Interfaces.Remotes;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Machiavelli.Interfaces.Observers.SpeelveldObserver;
import Machiavelli.Models.Speler;

public interface SpeelveldRemote extends Remote{
	public void setKoning(Speler speler) throws RemoteException;
	public void toonKarakterLijst() throws RemoteException;
	public void addObserver(SpeelveldObserver speelveldObserver) throws RemoteException;
	public void notifyObservers() throws RemoteException;
}
