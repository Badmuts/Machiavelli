package Interfaces;

import java.rmi.Remote;
import java.util.ArrayList;

import Models.Bank;
import Models.GebouwKaart;
import Models.Hand;
import Models.Portemonnee;
import Models.Spel;
import Models.Stad;

public interface SpelerRemote extends Remote{

	public void getGoudVanBank(Bank bank, int aantal);
	public void setGoudOpBank(Portemonnee portemonnee, int aantal);
	public void setKaartInStad(GebouwKaart gebouwen);
	public ArrayList<GebouwKaart> trekkenKaart();
	public ArrayList<GebouwKaart> trekkenKaart(int aantal);
	public void selecterenKaart(ArrayList<GebouwKaart> lijst, int index);
	public void setKarakter(Karakter karakter);
	public Karakter getKarakter();
	public Spel getSpel();
	public Portemonnee getPortemonnee();
	public Hand getHand();
	public Stad getStad();

}
