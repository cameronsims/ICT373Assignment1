package ICT373Asn1.exceptions;
/**
 * <p>Thrown when a month is not a valid input</p>
 * 
 * @author Cameron Sims
 * @since 21/03/2025
 */
public class InvalidMonthException extends ManagerException {
    /**
     * <p>Constructs the issue with the exception</p>
     */
    public InvalidMonthException() {
		super("Month must be a value between 1 and 12.");
	}
}
