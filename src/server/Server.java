package server;

import Machiavelli.Interfaces.Remotes.SpelRemote;
import Machiavelli.Models.Spel;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    private Spel spel;

	public void startServer(){
        try {
            //System.setProperty("java.rmi.server.hostname", "145.97.16.203"); // Zet uit voor lokale tests
            Registry registry = LocateRegistry.createRegistry(1099); // default port 1099 // run RMI registry on local host
            Spel spel = new Spel();
            SpelRemote spelSkeleton = (SpelRemote) UnicastRemoteObject.exportObject(spel, 1099);
            System.out.println("RMI Registry starter");
            registry.rebind("Spel", spelSkeleton); // bind calculator to RMI registry
            System.out.println("Spel skeleton bound");
            System.out.println("Server running...");
            // if you'd like to run rmiregistry from the command line
            //	run it from the project's bin directory, so rmiregistry can find the necessary classes

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
        }
	}

	public static void main(String args[]){
		Server server = new Server();
        server.startServer();
	}

}
