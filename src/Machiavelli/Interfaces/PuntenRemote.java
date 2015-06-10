package Interfaces;

import java.rmi.Remote;

import Machiavelli.Models.Speler;

public interface PuntenRemote extends Remote {
	
	public Speler berekenWinnaar();
}
