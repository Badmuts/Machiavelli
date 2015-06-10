package Machiavelli.Interfaces.Remotes;

import Machiavelli.Interfaces.Karakter;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by badmuts on 10-6-15.
 */
public interface KarakterFactoryRemote extends Remote {

    public ArrayList<Karakter> getKarakters() throws RemoteException;
    public Karakter getKarakterByNumber(int karakterNummer) throws RemoteException;

}
