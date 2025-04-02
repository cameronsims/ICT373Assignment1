package ICT373Asn1.exceptions;

/**
 * <p>Thrown when some issue is thrown by the program</p>
 *
 * @author Cameron Sims
 * @since 21/03/2025
 */
public class ManagerException extends Exception {
    /**
     * <p>Constructs the issue with the exception</p>
     */
    public ManagerException() {
		super("MagazineManager has had an exception thrown.");
	}
    
    /**
     * <p>Constructs the issue with the exception</p>
     * @param p_what The reason for why the error was thrown
     */
    public ManagerException(final String p_what) {
		super(p_what);
	}
}
