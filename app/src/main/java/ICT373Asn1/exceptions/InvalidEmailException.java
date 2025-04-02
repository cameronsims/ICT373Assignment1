package ICT373Asn1.exceptions;

/**
 * <p>Thrown when an email is invalid.</p>
 * 
 * @author Cameron Sims
 * @since 01/04/2025
 */
public class InvalidEmailException extends Exception {
	/**
     * <p>Constructs the issue with the exception</p>
     */
    public InvalidEmailException() {
        super("Invalid Email String.");
    }
}
