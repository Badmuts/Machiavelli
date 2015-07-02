package Machiavelli.Models;

/**
 * @author Jamie Kalloe
 * @author Daan Rosbergen
 * 
 *         Het Melding model wordt gebruikt om de melding (string) bericht in op te slaan en op te
 *         halen.
 * 
 */

public class Melding {

  private String message;

  /**
   * Maakt een nieuw melding model aan, met een nieuwe string message.
   */
  public Melding() {
    this.message = "";
  }

  /**
   * Deze method zet de string message van het melding model naar een specifiek opgegeven message.
   */
  public void setMelding(String melding) {
    this.message = melding;
  }

  /**
   * Retourneert de string message van het model.
   * 
   * @return String message met de melding
   */
  public String getMeldig() {
    return this.message;
  }
}
