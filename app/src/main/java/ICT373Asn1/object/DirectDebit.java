package ICT373Asn1.object;

import ICT373Asn1.exceptions.*;

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
    public DirectDebit(final String p_name, final long p_number, final int p_bsb) throws InvalidNameException, InvalidAccountNumberException, InvalidBSBException {
        super(p_name, p_number);
        
        this.setBSB(p_bsb);
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
