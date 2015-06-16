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
 * 
 * 
 * @author Jamie Kalloe
 *
 */

public class Spelregels implements SpelregelsRemote, Serializable {
	private ArrayList<SpelregelsObserver> observers = new ArrayList<>();

	public String getSpelregels() throws IOException {
		return this.getSpelregelsFromResource("spelregels.txt");
	}
	
	private String getSpelregelsFromResource(String fileName) {
		String text = null;

		File file = new File("src" + File.separator + "Machiavelli/Resources" + File.separator + "spelregels.txt");

		String absolutePath = file.getAbsolutePath();
		try {
			text = new Scanner( new File(absolutePath), "UTF-8" ).useDelimiter("\\A").next();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return text;
	}

	@Override
	public void addObserver(SpelregelsObserver observer) throws RemoteException {
		observers.add(observer);
	}

	public void notifyObservers() throws RemoteException {
		for (SpelregelsObserver observer: observers) {
			observer.modelChanged(this);
		}
	}
	
}