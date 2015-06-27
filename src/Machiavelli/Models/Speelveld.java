package Machiavelli.Models;

import Machiavelli.Controllers.SpeelveldController;
import Machiavelli.Factories.KarakterFactory;
import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Observers.SpeelveldObserver;
import Machiavelli.Interfaces.Remotes.*;
import Machiavelli.Machiavelli;
import Machiavelli.Views.SpeelveldView;
import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * 
 * Model van speelveld.
 *
 */

public class Speelveld extends UnicastRemoteObject implements SpeelveldRemote, Serializable {
    private SpeelveldView speelveldView;
	private ArrayList<Speler> spelers;
	private SpelRemote spel;
	private SpelerRemote koning;
	private Karakter karakter;
	private SpeelveldController speelveldcontroller;
	private SpelerRemote speler;
	private ArrayList<SpeelveldObserver> observers = new ArrayList<>();

	public Speelveld(SpelRemote spel) throws RemoteException {
        try {
            this.spel = (SpelRemote) Machiavelli.getInstance().getRegistry().lookup("Spel");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public void setKoning(SpelerRemote spelers) throws RemoteException {
		this.koning = spelers;
        notifyObservers();
	}

	public void toonKarakterLijst() throws RemoteException {
		// TODO
	}


	public void opslaanSpel() {
		try
		{
			FileOutputStream fos = new FileOutputStream(createSaveLocation() + "/machiavelli.sav");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this.spel.getSpelers());
			oos.writeObject(this.spel.getBank());
			oos.writeObject(this.spel.getGebouwFactory());
			oos.writeObject(this.spel.getKarakterFactory());
			oos.writeObject(this.spel.getMaxAantalSpelers());
			oos.close();
			fos.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public ArrayList<Object> ladenSpel() {

		try {
			ArrayList<Object> tempList = new ArrayList<>();
			FileInputStream fis = new FileInputStream(System.getProperty("user.home") + "/machiavelli.sav");
			ObjectInputStream ois = new ObjectInputStream(fis);

			tempList.add(((ArrayList<SpelerRemote>)ois.readObject()));
			tempList.add(((BankRemote)ois.readObject()));
			tempList.add(((GebouwFactoryRemote)ois.readObject()));
			tempList.add(((KarakterFactory)ois.readObject()));
			tempList.add(((int)ois.readObject()));

			fis.close();
			ois.close();
			return tempList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public File createSaveLocation()
	{
		File file = new File(System.getProperty("user.home") + "/machiavelli/");
		if (!file.exists()){
			file.mkdir();
		}

		return file;
	}

	public void addObserver(SpeelveldObserver observer) throws RemoteException {
		observers.add(observer);
	}

	public void notifyObservers() throws RemoteException {
		for (SpeelveldObserver observer: observers) {
			observer.modelChanged(this);
		}
	}

		public SpelerRemote getSpeler() {
        return this.speler;
    }

    public void addSpeler(SpelerRemote speler) {
        this.speler = speler;
    }

	public String toString() {
        return "Hoi ik ben een SpeelveldModel";
	}
}
