package ICT373Asn1.object;

import ICT373Asn1.exceptions.*;

/**
 * <p>A PaymentMethod that is used to pay stuff with
 * 
 * @author Cameron Sims
 * @since 21/03/2025
 *
 */
public abstract class PaymentMethod {
    /**
     * <p>Creates a payment method</p>
     *
     * @param p_name The name of the account holder.
     * @param p_number The number of the account
     * @throws InvalidNameException Thrown if the name is not valid.
	 * @throws InvalidAccountNumberException Throws if account number is not valid.
     */
    public PaymentMethod(final String p_name, final long p_number) throws InvalidNameException, InvalidAccountNumberException {
        this.setName(p_name);
        this.setNumber(p_number);
    }
    
    /**
     * <p>Sets the account name</p>
     *
     * @param p_name The new name of the account holder.
     * @throws InvalidNameException Thrown if the name is not valid.
     */
    public void setName(final String p_name) throws InvalidNameException {
        if (p_name != null || p_name.length() < 1) {
            throw new InvalidNameException();
        }
        this.m_name = p_name;
    }
    
    /**
     * <p>Gets the account name</p> 
     *
     * @return Returns the name of the account
     */
    public String getName() {
        return this.m_name;
    }
    
    /**
     * <p>Sets the number of the account</p>
     *
     * @param p_number The number of the payment method
	 * @throws InvalidAccountNumberException Throws if account number is not valid.
     */
    public void setNumber(final long p_number) throws InvalidAccountNumberException {
        if (p_number < 1) {
            throw new InvalidAccountNumberException();
        }
        this.m_number = p_number;
    }
    
    /**
     * <p>Gets the account number </p>
     *
     * @return Returns the number of the account
     */
    public long getNumber() {
        return this.m_number;
    }
    
    /// The name of the account
    private String m_name;
    
    /// The number of the account/payment method
    private long m_number;
}
