package ICT373Asn1.object;

import ICT373Asn1.exceptions.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>This is a debit account.</p>
 * 
 * @author Cameron Sims
 * @since 11/03/2025
 */
public class DirectDebit extends PaymentMethod {
    /**
     * <p>Creates a credit card</p>
     *
     * @param p_name The name of the account holder.
     * @param p_number The number of the account
     * @param p_bsb The Bank State Branch number in a Debit Account
     * @throws InvalidNameException Thrown if the name is not valid.
	 * @throws InvalidAccountNumberException Throws if account number is not valid.
     * @throws InvalidBSBException Throws if BSB is not valid.
     */
    public DirectDebit(final String p_name, final String p_number, final int p_bsb) throws InvalidNameException, InvalidAccountNumberException, InvalidBSBException {
        super(p_name, p_number);
        
        this.setBSB(p_bsb);
    }
    
    /**
     * <p>Sets the number of the account</p>
     *
     * @param p_number The number of the payment method
	 * @throws InvalidAccountNumberException Throws if account number is not valid.
     */
    public void setNumber(final String p_number) throws InvalidAccountNumberException {
        // Throw if not valid.
        if (p_number == null || p_number.length() == 0 || p_number.charAt(0) == '\0') {
            throw new InvalidAccountNumberException(); 
        }
        
        // Match for numbers.
        if (!s_validNumber(p_number, 2)) {
            throw new InvalidAccountNumberException();
        }
        
        // To make sure it fits, ensure its all just the numbers being input.
        this.m_number = s_getNumber(p_number);
    }
    
    /**
     * <p>Changes the BSB number</p>
     *
     * @param p_bsb The Bank State Branch number in a Debit Account
	 * @throws InvalidBSBException Throws if bsb is not valid.
     */
    public void setBSB(final int p_bsb) throws InvalidBSBException {
        if (p_bsb < 0 && p_bsb > 999999) {
            throw new InvalidBSBException();
        } 
        
        this.m_bsb = p_bsb;
    }
    
    /**
     * <p>Returns the BSB value</p>
     *
     * @return Returns the BSB number input in the class
     */
    public int getBSB() {
        return this.m_bsb;
    }
    
    /// The BSB number associated with the account
    private int m_bsb;
}
