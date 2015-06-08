package Interfaces;

/**
 * Deze interface word gebruikt voor karakters die een
 * bonus ontvangen voor gebouwen van het zelfde type
 * als zij zijn.
 *
 * Voorbeeld: De koning is van het type monument.
 * De speler die het karakter koning is heeft
 * 2 gebouwen van het type monument in zijn stad.
 *
 * Voor elk gebouw in zijn hand van hetzelfde type
 * ontvangt hij 1 goudstuk. In dit geval ontvangt hij
 * dus 2 goudstukken aan bonus goud.
 *
 * Er zijn 4 types die recht hebben op bonus goud:
 * - MONUMENT
 * - KERKELIJK
 * - COMMERCIEL
 * - MILITAIR
 *
 */
public interface Bonusable {

    public void ontvangenBonusGoud();

}
