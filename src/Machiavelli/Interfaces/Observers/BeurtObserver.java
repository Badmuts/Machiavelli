package Machiavelli.Interfaces.Observers;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Machiavelli.Interfaces.Remotes.BeurtRemote;

/**
 * Created by badmuts on 10-6-15.
 */
public interface BeurtObserver extends Remote {

    public void modelChanged(BeurtRemote beurt) throws RemoteException;
    
    public boolean isDisabled() throws RemoteException;
    
    public void setDisable(boolean disabled) throws RemoteException;

}
