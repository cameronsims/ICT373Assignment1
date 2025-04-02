package ICT373Asn1.exceptions;
/**
 * <p>Thrown a payment number (BSB/Security Number) is invalid.</p>
 *
 * @author Cameron Sims
 * @since 21/03/2025
 */
public class InvalidSecurityNumberException extends InvalidPaymentNumberException {
    /**
     * <p>Constructs the issue with the exception</p>
     */
    public InvalidSecurityNumberException() {
		super("Security Number is not valid.");
	}
}
