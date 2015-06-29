package Machiavelli.Interfaces.Remotes;

import Machiavelli.Enumerations.Type;
import Machiavelli.Interfaces.Observers.GebouwKaartObserver;
import Machiavelli.Models.Stad;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface GebouwKaartRemote extends Remote {

    public Type getType() throws RemoteException;

    public void setType(Type type) throws RemoteException;

    public String getNaam() throws RemoteException;

    public void setNaam(String naam) throws RemoteException;

    public int getKosten() throws RemoteException;

    public void setKosten(int kosten) throws RemoteException;

    public StadRemote getStad() throws RemoteException;

    public void setStad(StadRemote stad) throws RemoteException;

    public String getImage() throws RemoteException;

    public void setImage(String image) throws RemoteException;

    public void addObserver(GebouwKaartObserver gebouwKaartObserver) throws RemoteException;

    public void notifyObservers() throws RemoteException;

    public ArrayList<GebouwKaartObserver> getObservers() throws RemoteException;

}
