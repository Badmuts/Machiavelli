package Machiavelli.Models;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

import Machiavelli.Machiavelli;
import Machiavelli.Interfaces.Observers.SpelregelsObserver;
import Machiavelli.Interfaces.Remotes.SpelregelsRemote;

/**
 * @author Jamie Kalloe
 * 
 *         <<<<<<< HEAD Het Spelregels model wordt gebruikt om de spelregels mee op te halen voor de
 *         view.
 * 
 *         ======= De spelregels van Machiavelli zijn opgeslagen in een textdocument, deze kunnen op
 *         elk moment in het spel aangeroepen worden.
 *
 *         >>>>>>> 6395149309661da4f9c5b5c341ca69de3c13d1c0
 */

public class Spelregels implements SpelregelsRemote, Serializable {
    private ArrayList<SpelregelsObserver> observers = new ArrayList<>();

    /**
     * Retourneerd de spelregels die zijn opgehaald uit de text file.
     * 
     * @return spelregels String
     */
    public String getSpelregels() throws IOException {
        return this.getSpelregelsFromResource("spelregels.txt");
    }

    /**
     * Deze method haalt de spelregels uit een textfile van het opgegeven pad.
     * 
     * @param fileName naam de van de file waar de spelregels in staan.
     */
    private String getSpelregelsFromResource(String fileName) {

        Scanner scanner =
                new Scanner(Machiavelli.class.getResourceAsStream("Resources/spelregels.txt"),
                        "UTF-8");
        String text = scanner.useDelimiter("\\A").next();
        scanner.close();
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
