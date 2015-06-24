package Machiavelli.Controllers;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Machiavelli.Interfaces.Remotes.BeurtRemote;
import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Models.Speler;

public class BeurtController extends UnicastRemoteObject {
	private SpelRemote spel;
	private BeurtRemote beurt;

	public BeurtController(BeurtRemote beurt, SpelRemote spel) throws RemoteException {
		this.spel = spel;
		this.beurt = beurt;
	}
	
	public void cmdBeginRondeBeurt() throws RemoteException {
	  beurt.BeginRondeBeurt();
	}
	
	public SpelerRemote cmdGetSpeler() throws RemoteException {
		return beurt.getSpeler();
	}
	
	public void cmdEindeBeurt() throws RemoteException {
	  beurt.EindeBeurt();
	}
		
}
