package Machiavelli.Models;

import Machiavelli.Interfaces.Observers.SpelregelsObserver;
import Machiavelli.Interfaces.Remotes.SpelregelsRemote;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Jamie Kalloe
 * 
 *         Het Spelregels model wordt gebruikt om de spelregels mee op te halen voor de view.
 * 
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
    String text = null;

    File file =
        new File("src" + File.separator + "Machiavelli/Resources" + File.separator
            + "spelregels.txt");

    String absolutePath = file.getAbsolutePath();
    try {
      text = new Scanner(new File(absolutePath), "UTF-8").useDelimiter("\\A").next();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

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
