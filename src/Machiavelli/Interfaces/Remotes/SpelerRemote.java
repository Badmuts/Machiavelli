package Machiavelli.Interfaces.Remotes;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Machiavelli.Interfaces.Karakter;
import Machiavelli.Models.Bank;
import Machiavelli.Models.GebouwKaart;
import Machiavelli.Models.Hand;
import Machiavelli.Models.Portemonnee;
import Machiavelli.Models.Spel;
import Machiavelli.Models.Stad;

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
