package Machiavelli.Interfaces.Observers;

import Machiavelli.Interfaces.Remotes.PortemonneeRemote;
import Machiavelli.Models.Portemonnee;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by badmuts on 10-6-15.
 */
public interface PortemonneeOberserver extends Remote {

    void modelChanged(PortemonneeRemote portemonnee) throws RemoteException;

}
