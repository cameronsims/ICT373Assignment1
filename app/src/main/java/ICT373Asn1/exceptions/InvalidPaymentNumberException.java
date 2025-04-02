package ICT373Asn1.exceptions;
/**
 * <p>Thrown a payment number (BSB/Security Number) is invalid.</p>
 * 
 * @author Cameron Sims
 * @since 21/03/2025
 */
public class InvalidPaymentNumberException extends ManagerException {
    /**
     * <p>Constructs the issue with the exception</p>
     */
    public InvalidPaymentNumberException() {
		super("Payment Number (Security#/BSB) is not valid.");
	}
    
    /**
     * <p>Constructs the issue with the exception</p>
     * @param p_what The reason for why the exception was thrown
     */
    public InvalidPaymentNumberException(String p_what) {
		super(p_what);
	}
}
