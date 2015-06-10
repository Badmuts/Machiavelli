package Machiavelli.Models;

import Machiavelli.Interfaces.Remotes.PuntenRemote;

import java.rmi.RemoteException;

/**
 * Hier wordt bepaald wie de winnaar is van het spel.
 *
 * @author Bernd Oostrum
 * @version 0.1
 *
 */
public class PuntenModel implements PuntenRemote {

	private Speler winnaar;
	
	public Speler berekenWinnaar() throws RemoteException {
		return this.winnaar;
	}
}