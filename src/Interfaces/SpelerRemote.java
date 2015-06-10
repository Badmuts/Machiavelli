package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Models.Bank;
import Models.GebouwKaart;
import Models.Hand;
import Models.Portemonnee;
import Models.Spel;
import Models.Stad;

public interface SpelerRemote extends Remote{

	public void getGoudVanBank(Bank bank, int aantal) throws RemoteException;
	public void setGoudOpBank(Portemonnee portemonnee, int aantal) throws RemoteException;
	public void setKaartInStad(GebouwKaart gebouwen) throws RemoteException;
	public ArrayList<GebouwKaart> trekkenKaart() throws RemoteException;
	public ArrayList<GebouwKaart> trekkenKaart(int aantal) throws RemoteException;
	public void selecterenKaart(ArrayList<GebouwKaart> lijst, int index) throws RemoteException;
	public void setKarakter(Karakter karakter) throws RemoteException;
	public Karakter getKarakter() throws RemoteException;
	public Spel getSpel() throws RemoteException;
	public Portemonnee getPortemonnee() throws RemoteException;
	public Hand getHand() throws RemoteException;
	public Stad getStad() throws RemoteException;

}
