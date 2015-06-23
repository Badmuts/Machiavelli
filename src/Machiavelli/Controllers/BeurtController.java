package Machiavelli.Controllers;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Machiavelli.Interfaces.Remotes.BeurtRemote;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Models.Speler;

public class BeurtController extends UnicastRemoteObject {
	private SpelRemote spel;
	private BeurtRemote beurt;

	public BeurtController(BeurtRemote beurt, SpelRemote spel) throws RemoteException {
		this.spel = spel;
		this.beurt = beurt;
	}
	
	public void cmdGeefBeurt() throws RemoteException {
		beurt.geefBeurt(cmdGetSpeler());
	}
	
	public Speler cmdGetSpeler() throws RemoteException {
		return beurt.getSpeler();
	}
		
}
