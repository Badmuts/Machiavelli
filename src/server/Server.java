package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

	public void startServer(){
        try {
            Games games = new Games();
            GamesRemote gamesSkeleton = (GamesRemote) UnicastRemoteObject.exportObject(games, 0);
            Registry registry = LocateRegistry.createRegistry(1099); // default port 1099 // run RMI registry on local host
            System.out.println("RMI Registry starter");
            registry.rebind("Games", gamesSkeleton); // bind calculator to RMI registry
            System.out.println("Games skeleton bound");
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