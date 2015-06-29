package Machiavelli.Models;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

import Machiavelli.Enumerations.Type;
import Machiavelli.Interfaces.Observers.PuntenObserver;
import Machiavelli.Interfaces.Remotes.GebouwKaartRemote;
import Machiavelli.Interfaces.Remotes.PuntenRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Interfaces.Remotes.StadRemote;


/**
 * Hier wordt bepaald wie de winnaar is van het spel.
 *
 * @author Bernd Oostrum, Sander de Jong
 * @version 0.2
 *
 */
public class PuntenModel extends UnicastRemoteObject implements PuntenRemote, Serializable {

	private ArrayList<PuntenObserver> observers = new ArrayList<>();
	private ArrayList<SpelerRemote> scoreLijst = new ArrayList<>();
	private Spel spel;


	// Spel meegeven
	public PuntenModel(Spel spel) throws RemoteException {
		this.spel = spel;
	}

	// Punten berekenen en in spelers zetten
	private void calculateWinner() throws RemoteException {
		int totalScore = 0;
		for (SpelerRemote speler: this.spel.getSpelers())
		{
			totalScore = getStadBonus(speler.getStad()) + getKleurBonus(speler.getStad());
			speler.getStad().setWaardeStad(totalScore);
		}

		ArrayList<SpelerRemote> tempList = sortList(this.spel.getSpelers());
		this.scoreLijst = tempList;

		// Gelijkspel
		if (this.spel.getSpelers().get(0).equals(this.spel.getSpelers().get(1)))
		{
			for (SpelerRemote speler: this.spel.getSpelers())
			{
				speler.getStad().setWaardeStad(getStadBonus(speler.getStad()));
			}
			tempList = sortList(this.spel.getSpelers());
			this.scoreLijst = tempList;
		}
	}

	// De waarde van alle gebouwen in een stad
	private int getStadBonus(StadRemote stad) throws RemoteException {
		int waarde = 0;
		for (GebouwKaartRemote kaart: stad.getGebouwen())
		{
			waarde += kaart.getKosten();
		}
		return waarde;
	}

	// Als je 5 verschillende kleuren in je stad hebt krijg je 3 punten
	private int getKleurBonus(StadRemote stad) throws RemoteException {
		int bonus = 0;
		HashSet<Type> differentTypes = new HashSet<>();
		for (GebouwKaartRemote kaart: stad.getGebouwen())
		{
			differentTypes.add(kaart.getType());
		}

		if(differentTypes.size() == 5)
		{
			bonus += 5;
		}
		return bonus;
	}

	// Lijst sorteren van hoogste punten naar laagste punten
	private ArrayList<SpelerRemote> sortList(ArrayList<SpelerRemote> list)
	{
		ArrayList<SpelerRemote> winnaarsLijst = list;
		Collections.sort(winnaarsLijst, new Comparator<SpelerRemote>() {
			@Override
			public int compare(SpelerRemote o1, SpelerRemote o2) {
				try {
					return o1.getStad().getWaardeStad() + o2.getStad().getWaardeStad();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				return 0;
			}
		});
		return winnaarsLijst;
	}

	// Geeft lijst terug met spelers van hoog naar laag
	public ArrayList<SpelerRemote> scoreLijst() throws RemoteException {
		calculateWinner();
		return this.scoreLijst;
	}

	// RMI
	public void addObserver(PuntenObserver observer) throws RemoteException {
		observers.add(observer);
	}

	public void notifyObservers() throws RemoteException {
		for (PuntenObserver observer: observers) {
			observer.modelChanged(this);
		}
	}
}