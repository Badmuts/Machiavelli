package Machiavelli.Controllers;

import Machiavelli.Interfaces.Observers.SpelerObserver;
import Machiavelli.Interfaces.Remotes.GebouwKaartRemote;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Views.GebouwKaartView;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by badmuts on 14-6-15.
 * 
 * Deze Controllerklasse maakt de views van alle Gebouwkaarten aan. Ook worden
 * interacties met de Gebouwkaarten afgehandeld. Zowel het bouwen als het 
 * vernietigen van een gebouw wordt via deze Controller afgehandeld.
 */

public class GebouwKaartController extends UnicastRemoteObject implements SpelerObserver {
    private SpelRemote spel;
    private SpelerRemote speler;
    private ArrayList<GebouwKaartRemote> gebouwKaarten = new ArrayList<>();
    private ArrayList<GebouwKaartView> gebouwKaartViews = new ArrayList<>();
    private ArrayList<GebouwKaartRemote> activeCards = new ArrayList<>();

    /**
     * Maakt voor elke GebouwKaart uit de factory een nieuwe
     * GebouwKaartView aan en koppelt deze Controller aan de GebouwKaartView.
     *
     * @param spel
     * @throws RemoteException
     */
    public GebouwKaartController(SpelRemote spel, SpelerRemote spelerRemote) throws RemoteException {
//        super(1099);
        this.spel = spel;
        this.speler = spelerRemote;
        this.speler.addObserver(this);
    }

    public ArrayList<GebouwKaartView> getObservers() {
        return this.gebouwKaartViews;
    }

    public void addView(GebouwKaartView gebouwKaartView) {
        gebouwKaartViews.add(gebouwKaartView);
    }

    public void addModel(GebouwKaartRemote gebouwKaartRemote) {
        this.gebouwKaarten.add(gebouwKaartRemote);
    }

    public void setActiveCard(GebouwKaartRemote gebouwKaartRemote) {
        try {
            this.activeCards.add(gebouwKaartRemote);
            this.speler.getHand().addActiveCard(gebouwKaartRemote);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeActiveCard(GebouwKaartRemote gebouwKaart) {
        try {
            this.activeCards.remove(gebouwKaart);
            this.speler.getHand().removeActiveCard(gebouwKaart);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * In de ButtonHolderActionBarView is er op Bouwen gebouw geklikt.
     * De geselecteerde kaart wordt doorgegeven aan de Speler klasse.
     * Iterator op activeCards bleek niet te werken (ConcurrentModificationException)
     * Hierom wordt een nieuwe lijst als kopie gemaakt van activeCards en wordt de iterator hier op toegepast
     */
    public void cmdBouwGebouw() {
    	List<GebouwKaartRemote> list = new ArrayList<GebouwKaartRemote>(activeCards.size());
    	list.addAll(activeCards);
    	
    	Iterator<GebouwKaartRemote> iter = list.iterator();
    	while(iter.hasNext()) {
    		GebouwKaartRemote kaart = iter.next();
    		try {
    			speler.bouwenGebouw(kaart);
    			ArrayList<GebouwKaartRemote> gebouwdenKaarten = this.speler.getStad().getGebouwen();
    			String gebouwdeKaart = gebouwdenKaarten.get(gebouwdenKaarten.size() -1).getNaam();
    			new MeldingController().build(gebouwdeKaart + " is gebouwd").cmdWeergeefMeldingView();
    		} catch (RemoteException e) {
    			e.printStackTrace();
    		}
    	}
    	activeCards.clear();
    }
    	
    /**
     * Als er een gebouw geselecteerd is, wordt de gebruikEigenschap
     * methode aangeroepen. De geselecteerde kaart wordt doorgegeven aan
     * de karakterinterface. 
     * Iterator op activeCards bleek niet te werken (ConcurrentModificationException)
     * Hierom wordt een nieuwe lijst als kopie gemaakt van activeCards en wordt de iterator hier op toegepast
     */
    public void cmdVernietigGebouw() {
    	List<GebouwKaartRemote> list = new ArrayList<GebouwKaartRemote>(activeCards.size());
    	list.addAll(activeCards);
    	Iterator<GebouwKaartRemote> iter = list.iterator();
    	
    	while(iter.hasNext()) {
    		GebouwKaartRemote kaart = iter.next();
    		try {
    			this.speler.getKarakter().setTarget(kaart);
    			} catch (RemoteException e) {
    				e.printStackTrace();
    		}
    	}
    	activeCards.clear();
    }

    @Override
    public void modelChanged(SpelerRemote speler) throws RemoteException {
        this.speler = speler;
        this.activeCards.clear();
    }
}
