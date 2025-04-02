package ICT373Asn1.exceptions;

/**
 * <p>Thrown when a name is invalid.</p>
 * 
 * @author Cameron Sims
 * @since 01/04/2025
 */
public class InvalidNameException extends Exception {
	/**
     * <p>Constructs the issue with the exception</p>
     */
    public InvalidNameException() {
        super("Invalid Name of Consumable/Customer.");
    }
}
