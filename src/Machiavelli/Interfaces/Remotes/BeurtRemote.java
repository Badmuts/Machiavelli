package Machiavelli.Interfaces.Remotes;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Machiavelli.Interfaces.Observers.BeurtObserver;


/**
 * Created by badmuts on 18-6-15.
 */
public interface BeurtRemote extends Remote {

  // public void BeginRondeBeurt() throws RemoteException;
  public void geefBeurt() throws RemoteException;

  public SpelerRemote getSpeler() throws RemoteException;

  public void setSpeler(SpelerRemote speler) throws RemoteException;
  
  public void getInkomstenView() throws RemoteException;
  
  public int getObserverIndex() throws RemoteException;
  
  public ArrayList<BeurtObserver> getObserverList() throws RemoteException;
  
  public void setObserverIndex(int observerIndex) throws RemoteException;

  public void addObserver(BeurtObserver beurtObserver) throws RemoteException;

  public void notifyObservers() throws RemoteException;

}
