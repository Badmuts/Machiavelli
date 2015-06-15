package Machiavelli.Interfaces.Remotes;

import Machiavelli.Enumerations.Type;
import Machiavelli.Interfaces.Observers.GebouwKaartObserver;
import Machiavelli.Models.Stad;
import Machiavelli.Views.GebouwKaartView;
import javafx.scene.image.Image;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by badmuts on 10-6-15.
 */
public interface GebouwKaartRemote extends Remote {

    public Type getType() throws RemoteException;
    public void setType(Type type) throws RemoteException;
    public String getNaam() throws RemoteException;
    public void setNaam(String naam) throws RemoteException;
    public int getKosten() throws RemoteException;
    public void setKosten(int kosten) throws RemoteException;
    public Stad getStad() throws RemoteException;
    public void setStad(Stad stad) throws RemoteException;
    public String getImage() throws RemoteException;
    public void setImage(String image) throws RemoteException;
    public void addObserver(GebouwKaartObserver gebouwKaartObserver) throws RemoteException;
    public void notifyObservers() throws RemoteException;
    public GebouwKaartView getGebouwkaartView() throws RemoteException;

}
