package Models;

import java.rmi.RemoteException;

import Interfaces.Karakter;

/**
 * Created by daanrosbergen on 03/06/15.
 */
public class Bouwmeester implements Karakter {
    @Override
    public void gebruikEigenschap() throws RemoteException {
        System.out.println("Bouwmeester eigenschap");
    }
}
