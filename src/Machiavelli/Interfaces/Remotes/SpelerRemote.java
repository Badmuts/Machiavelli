package Machiavelli.Interfaces.Remotes;

import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Observers.SpelerObserver;
import Machiavelli.Models.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface SpelerRemote extends Remote{

	public void getGoudVanBank(Bank bank, int aantal) throws RemoteException;
	public void setGoudOpBank(Portemonnee portemonnee, int aantal) throws RemoteException;
	public void bouwenGebouw(GebouwKaart gebouwen) throws RemoteException;
	public ArrayList<GebouwKaart> trekkenKaart() throws RemoteException;
	public ArrayList<GebouwKaart> trekkenKaart(int aantal) throws RemoteException;
	public void selecterenKaart(ArrayList<GebouwKaart> lijst, int index) throws RemoteException;
	public void setKarakter(Karakter karakter) throws RemoteException;
	public Karakter getKarakter() throws RemoteException;
	public SpelRemote getSpel() throws RemoteException;
	public Portemonnee getPortemonnee() throws RemoteException;
	public Hand getHand() throws RemoteException;
	public Stad getStad() throws RemoteException;
	public void addObserver(SpelerObserver spelerObserver) throws RemoteException;
	public void notifyObservers() throws RemoteException;
	public void addSpel(SpelRemote spel) throws RemoteException;

}
