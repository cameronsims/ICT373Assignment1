package ICT373Asn1.exceptions;
/**
 * <p>Thrown when a consumable doesn't fit cost range.</p>
 * 
 * @author Cameron Sims
 * @since 21/03/2025
 */
public class InvalidCostException extends ManagerException {
    /**
     * <p>Constructs the issue with the exception</p>
     */
    public InvalidCostException() {
		super("Cost must be higher than or equal to 0.");
	}
}
