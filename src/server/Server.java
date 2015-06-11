/*package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import Models.Spel;
import Models.Speler;

public class Server {
	
	public void Server(){	
	}
	
	public void startServer(){
		try {
			
			Speler speler = new Speler();// create speelveld
			Interfaces.SpeelveldRemote speelSkeleton = 	(Interfaces.SpeelveldRemote) UnicastRemoteObject.exportObject(speelSkeleton, 0); // cast to remote object
			System.out.println("skeleton created");
			Registry registry = LocateRegistry.createRegistry(1099); // default port 1099 // run RMI registry on local host
			System.out.println("RMI Registry starter");
			registry.rebind("Speelveld", speelSkeleton); // bind calculator to RMI registry
            System.out.println("speelskeleton skeleton bound");
            System.out.println("Server running...");
            
		} catch (Exception e) {
			System.out.println("EXCEPTION: " + e);
		}
	
	}
	public static void main(String args[]){
		
	}
	
}
*/