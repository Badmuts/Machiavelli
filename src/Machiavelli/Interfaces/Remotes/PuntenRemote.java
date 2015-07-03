package Machiavelli.Interfaces.Remotes;

import Machiavelli.Interfaces.Observers.PuntenObserver;
import Machiavelli.Models.Speler;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by badmuts on 18-6-15.
 */
public interface PuntenRemote extends Remote {

  public void addObserver(PuntenObserver observer) throws RemoteException;

  public void notifyObservers() throws RemoteException;
  
  public ArrayList<SpelerRemote> berekenScorelijst() throws RemoteException;
  
  

}
