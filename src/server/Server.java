package server;

import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Models.Spel;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Server class voor de Machiavelli applicatie. Maakt een nieuw Registry en
 * plaats een Spel skeleton in het registry.
 *
 * @author Daan Rosbergen
 * @version 1.0
 */
public class Server {

    /**
     * Maakt een nieuw Registry aan en plaatst een Spel skeleton in het registry
     */
	public void startServer(){
        try {
            // Hardcode ipadres van server, zet deze uit voor lokale tests.
            System.setProperty("java.rmi.server.hostname", "145.97.16.203"); // Hardcode ipadres van server
            Registry registry = LocateRegistry.createRegistry(1099); // default port 1099 // run RMI registry on local host
            Spel spel = new Spel();
            // Maak van spel een skeleton
            SpelRemote spelSkeleton = (SpelRemote) UnicastRemoteObject.exportObject(spel, 1099);
            System.out.println("RMI Registry starter");
            registry.rebind("Spel", spelSkeleton); // bind Spel to RMI registry
            System.out.println("Spel skeleton bound");
            System.out.println("Server running...");
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
        }
	}

    /**
     * Maakt een nieuw Server object en roept startServer() aan.
     *
     * @param args
     */
	public static void main(String args[]){
		Server server = new Server();
        server.startServer();
	}

}
