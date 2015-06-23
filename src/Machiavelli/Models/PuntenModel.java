package Machiavelli.Models;

import Machiavelli.Enumerations.Type;
import Machiavelli.Interfaces.Observers.PuntenObserver;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.sql.Types;
import java.util.*;

/**
 * Hier wordt bepaald wie de winnaar is van het spel.
 *
 * @author Bernd Oostrum, Sander de Jong
 * @version 0.1
 *
 */
public class PuntenModel implements Serializable {

	private ArrayList<PuntenObserver> observers = new ArrayList<>();
	private ArrayList<Speler> scoreLijst = new ArrayList<>();
	private Spel spel;


	// Spel meegeven
	public PuntenModel(Spel spel) {
		this.spel = spel;
	}

	// Punten berekenen en in spelers zetten
	private void calculateWinner() throws RemoteException {
		int totalScore = 0;
		for (Speler speler: this.spel.getSpelers())
		{
			totalScore = getStadBonus(speler.getStad()) + getKleurBonus(speler.getStad());
			speler.getStad().setWaardeStad(totalScore);
		}

		ArrayList<Speler> tempList = sortList(this.spel.getSpelers());
		this.scoreLijst = tempList;

		// Gelijkspel
		if (this.spel.getSpelers().get(0).equals(this.spel.getSpelers().get(1)))
		{
			for (Speler speler: this.spel.getSpelers())
			{
				speler.getStad().setWaardeStad(getStadBonus(speler.getStad()));
			}
			tempList = sortList(this.spel.getSpelers());
			this.scoreLijst = tempList;
		}
	}

	// De waarde van alle gebouwen in een stad
	private int getStadBonus(Stad stad) throws RemoteException {
		int waarde = 0;
		for (GebouwKaart kaart: stad.getGebouwen())
		{
			waarde += kaart.getKosten();
		}
		return waarde;
	}

	// Als je 5 verschillende kleuren in je stad hebt krijg je 3 punten
	private int getKleurBonus(Stad stad) throws RemoteException {
		int bonus = 0;
		HashSet<Type> differentTypes = new HashSet<>();
		for (GebouwKaart kaart: stad.getGebouwen())
		{
			differentTypes.add(kaart.getType());
		}

		if(differentTypes.size() == 5)
		{
			bonus += 5;
		}
		return bonus;
	}

	private ArrayList<Speler> sortList(ArrayList<Speler> list)
	{
		ArrayList<Speler> winnaarsLijst = list;
		Collections.sort(winnaarsLijst, new Comparator<Speler>() {
			@Override
			public int compare(Speler o1, Speler o2) {
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

	public String scoreLijst() throws RemoteException {
		return null;
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