package Machiavelli.Models;

import Machiavelli.Interfaces.Observers.StadObserver;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by daanrosbergen on 03/06/15.
 */
public class Stad implements Serializable {
	private final Speler speler;

	// met deze list checken of er al 8 (of meer)
	// gebouwen zijn geplaatst, zo ja:
	// ronde stoppen en dan scores berekenen.
	private ArrayList<GebouwKaart> gebouwen = new ArrayList<GebouwKaart>();
	private Spel spel;
	private int waardeStad;
	private ArrayList<StadObserver> observers = new ArrayList<>();
	//private StadView stadView;

	public Stad(Speler speler)
	{
		this.speler = speler;
	}

	public ArrayList<GebouwKaart> getGebouwen() throws RemoteException
	{
		return this.gebouwen;
	}

	public void addGebouw(GebouwKaart gebouw) throws RemoteException
	{
		// Limiet moet nog gecheckt worden?
		this.gebouwen.add(gebouw);
        notifyObservers();
	}

	public void removeGebouw(GebouwKaart gebouw) throws RemoteException
	{
		this.gebouwen.remove(gebouw);
		// Plaats gebouwkaart terug op gebouwenstapel (GebouwFactory)
		this.spel.getGebouwFactory().addGebouw(gebouw);
		// Update StadView
		// this.stadView.modelChanged();
        notifyObservers();
	}
	
	public int getWaardeStad() throws RemoteException
	{
		return this.waardeStad;
	}

	public void setWaardeStad(int waarde)
	{
		this.waardeStad = waarde;
	}


	public void addObserver(StadObserver observer) throws RemoteException {
		observers.add(observer);
	}

	public void notifyObservers() throws RemoteException {
		for (StadObserver observer: observers) {
			observer.modelChanged(this);
		}
	}

	public Speler getSpeler() throws RemoteException {
		// TODO Auto-generated method stub
		return speler;
	}

	

}