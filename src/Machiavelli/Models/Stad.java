package Machiavelli.Models;

import Machiavelli.Interfaces.Observers.StadObserver;
import Machiavelli.Interfaces.Remotes.StadRemote;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by daanrosbergen on 03/06/15.
 */
public class Stad implements StadRemote {
	private ArrayList<GebouwKaart> gebouwen = new ArrayList<GebouwKaart>();
	private Spel spel;
	private int waardeStad;
	private ArrayList<StadObserver> observers = new ArrayList<>();
	//private StadView stadView;

	public Stad(Spel spel)
	{
		this.spel = spel;
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
		berekenWaarde(gebouwen);
		return this.waardeStad;
	}
	
	private void berekenWaarde(ArrayList<GebouwKaart> lijst) throws RemoteException
	{
		int waarde = 0;
		for(int i = 0; i < lijst.size(); i++)
		{
			waarde += lijst.get(i).getKosten();
		}
		this.waardeStad = waarde;
		notifyObservers();
	}

	@Override
	public void addObserver(StadObserver observer) throws RemoteException {
		observers.add(observer);
	}

	public void notifyObservers() throws RemoteException {
		for (StadObserver observer: observers) {
			observer.modelChanged(this);
		}
	}

}