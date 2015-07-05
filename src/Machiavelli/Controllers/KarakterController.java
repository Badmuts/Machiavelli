package Machiavelli.Controllers;


import Machiavelli.Interfaces.Karakter;
import Machiavelli.Interfaces.Remotes.KarakterFactoryRemote;
import Machiavelli.Interfaces.Remotes.SpelerRemote;
import Machiavelli.Views.KiesKarakterView;
import Machiavelli.Views.MagierKeuzeView;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


/**
 * @author Jamie Kalloe
 * @author Daan Rosbergen
 * 
 *         De KarakterController wordt gebruikt voor het kiezen van een karakter voor een speler of
 *         karaktereigeschap. Ook is er de mogelijkheid om een speler te selecteren (die meespelen).
 *         Er zijn een aantal types voor de KarakterController: - KARAKTER: KIES KARAKTER ALS TARGET
 *         - RONDE : KIES KARAKTER VOOR SPELER - SPELER: KIES SPELER ALS TARGET - MAGIER: KIES
 *         SPELER OF STAPEL
 * 
 */
public class KarakterController extends UnicastRemoteObject {

  private String typeView;
  // TODO: krijg speler van beurt..
  private SpelerRemote speler;
  private KiesKarakterView karakterView;
  private MagierKeuzeView magierKeuzeView;

  /**
   * Maakt een nieuwe KiesKarakterView aan voor het opgegeven type.
   * 
   * @param speler remote object
   * @param typeView string type van de controller
   * @throws RemoteException
   */
  public KarakterController(SpelerRemote speler, String typeView) throws RemoteException {
    // TYPE VAN VIEW IN CONSTRUCTOR
    // TYPES:
    // - KARAKTER: KIES KARAKTER ALS TARGET
    // - RONDE : KIES KARAKTER VOOR SPELER
    // - SPELER: KIES SPELER ALS TARGET
    // - MAGIER: KIES SPELER OF STAPEL
    this.typeView = typeView;
    this.speler = speler;
    this.karakterView = new KiesKarakterView(this.speler.getSpel().getKarakterFactory(), this);
  }

  /**
   * Deze method zet het gekozen karakter (vanuit de kiesKarkaterView) als target voor het
   * karakter/speler die zijn eigenschap wilt gebruiken op de target. Dit kunnen de karakters,
   * moordenaar, dief zijn (bijvoorbeeld).
   */
  public void cmdSetTarget(Karakter karakter) {
    try {
      // Speler (dief0 heeft op de knop gelikt en de controller krijgt de gekozen karakter mee.
      // Checken of er een speler zit aan het karakter.
      // Op basis daarvan, actie ondernemen
      // Of een melding laten zien!
      ArrayList<Karakter> spelendeKarakters = new ArrayList<Karakter>();
      for (SpelerRemote splr : this.speler.getSpel().getSpelers()) {
        if (splr.getKarakter() != null) {
          spelendeKarakters.add(splr.getKarakter());
        }
      }

      boolean speeltMee = spelendeKarakters.contains(karakter);

      System.out.println("De volgende karakters spelen mee:");
      for (Karakter krktr : spelendeKarakters) {
        System.out.println(krktr.getNummer() + " " + krktr.getNaam());
        if (krktr.equals(karakter)) {
          System.out.println("De " + karakter.getNummer() + " " + karakter.getNaam()
              + " speelt mee");
          break;
        }
      }

      // check of het gekozen karakter (knop) meedoet aan het spel
      // for(Karakter krktr : spelendeKarakters)
      // {
      if (speeltMee) {
        // Speler zet de target voor zijn karakter.
        this.speler.getKarakter().setTarget(karakter);

        // Het karakter van de speler moet de initierende speler meekrijgen.
        this.speler.getKarakter().setSpeler(this.speler);

        // Speler gebruik de eigenschap van zijn karakter.
        this.speler.getKarakter().gebruikEigenschap();
        this.karakterView.close();
        new MeldingController().build(
            "Je hebt je karaktereigenschap gebruikt op de " + karakter.getNaam())
            .cmdWeergeefMeldingView();
        // break;
      } else {
        System.out.println("het gekozen karakter " + karakter.getNaam() + " speelt NIET mee");
        this.karakterView.close();
        this.speler.setEigenschapGebruikt(true);
        new MeldingController().build("De " + karakter.getNaam() + " speelt niet mee!")
            .cmdWeergeefMeldingView();
        // break;
      }
      // }
      spelendeKarakters.clear();
      spelendeKarakters = null;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Retourneert het type van de view.
   * 
   * @return typeView string type van de view
   */
  public String getTypeView() {
    return this.typeView;
  }

  /**
   * Deze method zet het gekozen karakter als karakter voor de volgende ronde voor de speler. Als er
   * gekozen is wordt de KiesKarakterView afgesloten en wordt er een melding weergeven die de naam
   * van het gekozen karakter weergeeft.
   * 
   */
  public void cmdSetKarakter(Karakter karakter) {
    try {
      KarakterFactoryRemote karakterFactory = this.speler.getSpel().getKarakterFactory();

      Karakter gekozenKarakter = karakterFactory.getKarakterByNumber(karakter.getNummer());

      this.speler.setKarakter(gekozenKarakter);
      gekozenKarakter.setSpeler(this.speler);

      this.karakterView.close();
      new MeldingController().build("Je bent deze ronde een " + gekozenKarakter.getNaam())
          .cmdWeergeefMeldingView();

    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  /**
   * Deze method zorgt ervoor dat de magier zijn target kan zetten. Er wordt een object meegegeven
   * omdat de magier of een speler/karakter moet doorgeven, of een factory.
   * 
   */
  public void cmdSetTarget(Object target) {
    try {
      this.getSpeler().getKarakter().setTarget(target);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Weergeeft de MagierKeuzeView, deze view bestaat uit 2 knoppen. 1 knop wordt gebruikt om de
   * stapel te selecteren, de andere knop om een speler te selecteren. Als er voor een speler is
   * gekozen, dan wordt er een KiesKarakterView weergeven met meespelende spelers.
   * 
   */
  public void show() {
    if (String.valueOf(this.typeView).equals("magier")) {
      magierKeuzeView = new MagierKeuzeView(this);
      magierKeuzeView.show();
    } else {
      this.karakterView.show();
    }
  }

  /**
   * Als er een MagierKeuzeView is geopend dan wordt deze met deze method gesloten. Anders wordt de
   * KiesKarakterView weergeven.
   * 
   */
  public void close() {
    if (String.valueOf(this.typeView).equals("magier")) {
      magierKeuzeView.close();
    } else {
      this.karakterView.close();
    }
  }

  /**
   * Retourneerd de speler die een karakter wilt selecteren / geselecteerd heeft.
   * 
   * @return speler remote object
   */
  public SpelerRemote getSpeler() {
    return this.speler;
  }

  /**
   * Deze method zorgt ervoor dat de gekozen speler als target wordt gezet voor een karakter die
   * zijn karaktereigenschap wilt gebruiken.
   * 
   */
  public void cmdSetSpelerTarget(SpelerRemote speler) {
    try {
      this.speler.getKarakter().setTarget(speler);
      // execute magier eigenschap ?
      this.speler.getKarakter().gebruikEigenschap();
      this.karakterView.close();
      new MeldingController().build(
          "Je hebt je karaktereigenschap op de " + speler.getKarakter().getNaam() + " gebruikt")
          .cmdWeergeefMeldingView();
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  /**
   * Deze method zorgt ervoor dat de speler de eigenschap van zijn karakter kan uitvoeren.
   * 
   */
  public void cmdGebruikEigenschap() {
    try {
      this.speler.getKarakter().gebruikEigenschap();
      this.close();
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  /**
   * Deze method zorgt ervoor dat het type van de controller gezet kan worden.
   * 
   */
  public void setTypeView(String type) {
    this.typeView = type;
  }
}
