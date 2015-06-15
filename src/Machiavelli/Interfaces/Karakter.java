package Machiavelli.Interfaces;

import Machiavelli.Enumerations.Type;
import Machiavelli.Models.Speler;
import javafx.scene.image.Image;

/**
 * Deze interface word geimplementeerd door alle karakters.
 *
 * Elk karakter heeft een unieken eigenschap welke hij kan
 * gebruiken in het spel. Deze interface bied daar een
 * methode voor. De ontwikkelaar van het karakter schrijft
 * hiervoor zijn eigen "behaviour". Op deze manier hoef
 * je niet te weten wie of wat het karakter is maar spreek
 * je tegen een interface.
 *
 * Ook is er een setter aanwezig voor speler omdat het speler
 * object veel word gebruikt binnen de methode gebruikEigenschap.
 */
public interface Karakter {
	
	void setSpeler(Speler speler);
	void getSpeler();
    void gebruikEigenschap();
    String getNaam();
    int getNummer();
    int getBouwLimiet();
    
    Type getType();
    void setTarget(Object target);
    Image getImage();
	void beurtOverslaan();

}
