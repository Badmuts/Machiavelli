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
     */
    public void cmdBouwGebouw() {
    	
/*    	List<GebouwKaartRemote> list = new ArrayList<GebouwKaartRemote>(activeCards);
    	for(GebouwKaartRemote kaart : this.activeCards) {
    		try {
				speler.bouwenGebouw(kaart);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	activeCards.removeAll(list);*/
    	List<GebouwKaartRemote> list = new ArrayList<GebouwKaartRemote>(activeCards.size());
    	list.addAll(activeCards);
    	
    	Iterator<GebouwKaartRemote> iter = list.iterator();
    	while(iter.hasNext()) {
    		GebouwKaartRemote kaart = iter.next();
    		try {
    			speler.bouwenGebouw(kaart);
    		} catch (RemoteException e) {
    			e.printStackTrace();
    		}
    	}
    	activeCards.clear();
    	
    	
    	/*synchronized(activeCards) {
    		Iterator<GebouwKaartRemote> itr = activeCards.iterator();
    		while(itr.hasNext()) {
    			GebouwKaartRemote kaart = itr.next();
    			try {
					speler.bouwenGebouw(kaart);
					if (itr.equals(kaart)) {
						itr.remove();
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
    		}
    		
    	}*/
    	
       /* try {
            Iterator<GebouwKaartRemote> iterator = this.activeCards.iterator();
            while (iterator.hasNext()) {
                GebouwKaartRemote kaart = iterator.next();
                this.speler.bouwenGebouw(kaart);
                //this.activeCards.remove(kaart);
                //iterator.remove();
            }
            activeCards.clear();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Iterator remove error, maar het werkt");
        }*/
    }
    
    /**
     * Als er een gebouw geselecteerd is, wordt de gebruikEigenschap
     * methode aangeroepen. De geselecteerde kaart wordt doorgegeven aan
     * de karakterinterface. 
     * Iterator op activeCards bleek niet te werken (ConcurrentModificationException)
     * Hierom wordt een nieuwe lijst als kopie gemaakt van activeCards en wordt de iterator hier op toegepast
     */
    public synchronized void cmdVernietigGebouw() {
    	System.out.println("vernietig gebouw");
//    	List<GebouwKaartRemote> list = new ArrayList<GebouwKaartRemote>(activeCards);
//    	for(GebouwKaartRemote kaart : activeCards) {
//    		try {
//				speler.getKarakter().setTarget(kaart);
//			} catch (RemoteException e) {
//				e.printStackTrace();
//			}
//    	}
//    	activeCards.removeAll(list);
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
    	
//    	try {
//    		Iterator<GebouwKaartRemote> iterator = this.activeCards.iterator();
//    		while (iterator.hasNext()) {
//    			GebouwKaartRemote target = iterator.next();
//    			this.speler.getKarakter().setTarget(target);
//        		//this.activeCards.remove(target);
//        		
//        		
//			}
//    		activeCards.clear();
//    	}
//    	catch (RemoteException e) {
//			//e.printStackTrace();
//			System.out.println("Iterator remove error, maar het werkt");
//		}		
//	}
    
    @Override
    public void modelChanged(SpelerRemote speler) throws RemoteException {
        this.speler = speler;
        this.activeCards.clear();
    }
}
