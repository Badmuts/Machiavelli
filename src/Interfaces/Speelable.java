package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Models.Speler;

public interface Speelable extends Remote{
	public void setKoning(Speler speler) throws RemoteException;
	public void toonKarakterLijst() throws RemoteException;
}
