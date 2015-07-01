package Machiavelli.Models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Machiavelli.Interfaces.Observers.SpelregelsObserver;
import Machiavelli.Interfaces.Remotes.SpelregelsRemote;

/**
 * @author Jamie Kalloe
 *         De spelregels van Machiavelli zijn opgeslagen in een textdocument, deze
 *         kunnen op elk moment in het spel aangeroepen worden.
 */

public class Spelregels implements SpelregelsRemote, Serializable {
    private ArrayList<SpelregelsObserver> observers = new ArrayList<>();

    /**
     * Retourneerd de spelregels die zijn opgehaald uit de text file.
     *
     * @return spelregels String
     */
    public String getSpelregels() {
        try {
            return this.getSpelregelsFromResource("spelregels.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Deze method haalt de spelregels uit een textfile van het opgegeven pad.
     *
     * @param fileName naam de van de file waar de spelregels in staan.
     */
    private String getSpelregelsFromResource(String fileName) throws Exception {
        String text = null;

        InputStream in = getClass().getResourceAsStream("/Machiavelli/Resources/" + fileName);
        BufferedReader input = new BufferedReader(new InputStreamReader(in));
        try {
            StringBuilder builder = new StringBuilder();
            String aux = "";

            while ((aux = input.readLine()) != null) {
                builder.append(aux + System.getProperty("line.separator"));
            }
            text = builder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        input.close();
        return text;
    }
    /**
     * Voert de SpelregelsObserver toe aan de lijst met observers.
     */
    public void addObserver(SpelregelsObserver observer) throws RemoteException {
        observers.add(observer);
    }

    /**
     * Vertelt de observers dat het model is veranderd.
     */
    public void notifyObservers() throws RemoteException {
        for (SpelregelsObserver observer : observers) {
            observer.modelChanged(this);
        }
    }

}
