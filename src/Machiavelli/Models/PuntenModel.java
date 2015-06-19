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
	private Spel spel;


	public PuntenModel(Spel spel) {
		this.spel = spel;
	}

	// Moet nog berekenen wie als eerste 8 gebouwen heeft en wie daarna
	private void berekenWinnaar() throws RemoteException {
		int totalScore = 0;
		for(Speler speler: this.spel.getSpelers())
		{
			totalScore = getStadBonus(speler.getStad()) + getKleurBonus(speler.getStad());
			speler.getStad().setWaardeStad(totalScore);
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
		Set differentTypes = new TreeSet<>();

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

	private void gelijkSpel()
	{
		//TODO: wat als er gelijk spel is?
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