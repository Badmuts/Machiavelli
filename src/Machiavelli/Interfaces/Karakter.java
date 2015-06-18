package Machiavelli.Interfaces;

import Machiavelli.Enumerations.Type;
import Machiavelli.Interfaces.Observers.KarakterObserver;
import Machiavelli.Models.Speler;
import javafx.scene.image.Image;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Deze interface word geimplementeerd door alle karakters.
 *
 * Elk karakter heeft een unieken eigenschap welke hij kan
 * gebruiken in het spel. Deze interface bied daar een
 * methode voor. De ontwikkelaar van het karakter schrijft
 * hiervoor zijn eigen "behaviour". Op deze manier hoef
 * je niet te weten wie of wat het karakter is maar spreek
 * je tegen een interface.
 *
 * Ook is er een setter aanwezig voor speler omdat het speler
 * object veel word gebruikt binnen de methode gebruikEigenschap.
 */
public interface Karakter extends Remote {
	
	void setSpeler(Speler speler) throws RemoteException;
    void gebruikEigenschap() throws RemoteException;
    void setTarget(Object target) throws RemoteException;
    void beurtOverslaan() throws RemoteException;
    Speler getSpeler() throws RemoteException;
    String getNaam() throws RemoteException;
    int getNummer() throws RemoteException;
    int getBouwLimiet() throws RemoteException;
    Type getType() throws RemoteException;
    Image getImage() throws RemoteException;
    void addObserver(KarakterObserver observer) throws RemoteException;
    void notifyObservers() throws RemoteException;

}
