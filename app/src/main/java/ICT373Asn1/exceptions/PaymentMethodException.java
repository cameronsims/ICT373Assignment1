package ICT373Asn1.exceptions;
/**
 * <p>Thrown when a payment method is not valid...
 *
 * @author Cameron Sims
 * @since 21/03/2025
 */
public class PaymentMethodException extends ManagerException {
    /**
     * <p>Constructs the issue with the exception</p>
     */
    public PaymentMethodException() {
		super("Payment Method was not input correctly.");
	}
    
    /**
     * <p>Constructs the issue with the exception</p>
     * @param p_what The reason for why the exception was thrown
     */
    public PaymentMethodException(String p_what) {
		super(p_what);
	}
}
