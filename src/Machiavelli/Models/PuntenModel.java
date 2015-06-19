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


	public PuntenModel(Spel spel) {
		this.spel = spel;
	}

	// Berekenen winnaar en checken op gelijkspel
	// Moet nog berekenen wie als eerste 8 gebouwen heeft en wie daarna
	private void berekenWinnaar() throws RemoteException {
		int totalScore = 0;
		for(Speler speler: this.spel.getSpelers())
		{
			totalScore = getStadBonus(speler.getStad()) + getKleurBonus(speler.getStad());
			speler.getStad().setWaardeStad(totalScore);
		}

		scoreLijst = this.spel.getSpelers();
		sortList(scoreLijst);
		checkDraw(scoreLijst);
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

	// Als er gelijjkspel is worden alleen de gebouwkaarten geteld.
	private void checkDraw(ArrayList<Speler> lijst) throws RemoteException {
		if (lijst.get(0).equals(lijst.get(1)))
		{
			for(Speler speler: this.spel.getSpelers())
			{
				speler.getStad().setWaardeStad(getStadBonus(speler.getStad()));
			}
		}
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
		berekenWinnaar();
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