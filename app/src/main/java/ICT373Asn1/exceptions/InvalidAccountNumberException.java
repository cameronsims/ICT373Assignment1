package ICT373Asn1.exceptions;
/**
 * <p>Thrown an account number is invalid.</p>
 * 
 * @author Cameron Sims
 * @since 21/03/2025
 */
public class InvalidAccountNumberException extends PaymentMethodException {
    /**
     * <p>Constructs the issue with the exception</p>
     */
    public InvalidAccountNumberException() {
		super("Account number is not a valid input.");
	}
}
