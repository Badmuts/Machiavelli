package Models;

import Enumerations.Type;

/**
 * 
 * @author Sander
 *
 */
public class GebouwKaart
{
	private short kosten;
	private String naam;
	private Type type;
	private Stad stad;

    public void setStad(Stad stad) {
        this.stad = stad;
    }

    public Stad getStad() {
        return stad;
    }
    
    public short getKosten()
    {
    	return this.kosten;
    }
}
