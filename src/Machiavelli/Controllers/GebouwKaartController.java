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

/**
 * Created by badmuts on 14-6-15.
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

    public void cmdBouwGebouw() {
        try {
            Iterator<GebouwKaartRemote> iterator = this.activeCards.iterator();
            while (iterator.hasNext()) {
                GebouwKaartRemote kaart = iterator.next();
                this.speler.bouwenGebouw(kaart);
                this.activeCards.remove(kaart);
                iterator.remove();
            }
        } catch (Exception e) {
            System.out.println("Iterator remove error, maar het werkt");
        }
    }
    
    /**
     * Als er een gebouw geselecteerd is, wordt de gebruikEigenschap
     * methode aangeroepen.
     *
     */
    public void cmdVernietigGebouw() {
    	System.out.println("vernietig gebouw");
    	try {
    		Iterator<GebouwKaartRemote> iterator = this.activeCards.iterator();
    		
    		while (iterator.hasNext()) {
    			GebouwKaartRemote target = iterator.next();
    			this.speler.getKarakter().setTarget(target);
        		this.activeCards.remove(target);
        		iterator.remove();
			}
    	}
    	catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
    	}
    
    @Override
    public void modelChanged(SpelerRemote speler) throws RemoteException {
        this.speler = speler;
        this.activeCards.clear();
    }
}
