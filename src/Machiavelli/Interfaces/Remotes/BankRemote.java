package Machiavelli.Interfaces.Remotes;

import java.rmi.Remote;

/**
 * Created by badmuts on 10-6-15.
 */
public interface BankRemote extends Remote {

    public void ontvangenGoud(int aantal);
    public int gevenGoud(int aantal);
    public int getGoudMunten();

}
