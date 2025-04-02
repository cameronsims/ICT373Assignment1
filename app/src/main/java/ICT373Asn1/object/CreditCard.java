package ICT373Asn1.object;

import ICT373Asn1.exceptions.*;
import ICT373Asn1.manager.MonthManager;

/**
 * <p>A CreditCard used to pay things with</p>
 *
 * @author Cameron Sims
 * @since 11/03/2025
 */
public class CreditCard extends PaymentMethod {
    /**
     * <p>Creates a credit card</p>
     *
     * @param p_name The name of the account holder.
     * @param p_number The number of the account
     * @param p_month The month that is registered under the credit card
     * @param p_year The year that is registered under the credit card.
     * @param p_security The security code at the back of the card
     * @throws InvalidNameException Thrown if the name is not valid.
     * @throws InvalidAccountNumberException Throws if account number is not valid.
	 * @throws InvalidMonthException Throws if month is not valid.
     * @throws InvalidSecurityNumberException Throws if security is not valid.
     */
    public CreditCard(final String p_name,final  long p_number, final int p_month, final int p_year, final int p_security) throws InvalidNameException, InvalidAccountNumberException, InvalidMonthException, InvalidSecurityNumberException { 
        super(p_name, p_number);
        
        this.setExpiry(p_month, p_year);
        this.setSecurity(p_security);
    }
    
    /**
     * <p>Sets the expiry of the card</p>
     *
     * @param p_month The month that the card will expire
     * @param p_year The year that the card will expire
	 * @throws InvalidMonthException Throws if month is not valid.
     */
    public void setExpiry(final int p_month, final int p_year) throws InvalidMonthException {
        if (!MonthManager.isValid(p_month)) {
            throw new InvalidMonthException();
        }
        
        this.m_month = p_month;
        this.m_year = p_year;
    }
    
    /**
     * <p>Gets the month of the expiry</p>
     *
     * @return Returns the month of expiry
     */
    public int getMonth() {
        return this.m_month;
    }
    
    /**
     * <p>Gets the year of the expiry</p>
     *
     * @return Returns the year of expiry
     */
    public int getYear() {
        return this.m_year;
    }
    
    /**
     * <p>Sets the security of the card</p>
     *
     * @param p_security The security card number
     * @throws InvalidSecurityNumberException Throws if security is not valid.
     */
    public void setSecurity(final int p_security) throws InvalidSecurityNumberException {
        if (p_security < 0 || p_security > 999) {
            throw new InvalidSecurityNumberException();
        }
        
        this.m_security = p_security;
    }
    
    /**
     * <p>Gets the security of the card</p>
     *
     * @return Returns the security card number
     */
    public int getSecurity() {
        return this.m_security;
    }
    
    
    /// The month of expiry
    private int m_month;
    
    /// The year of expiry
    private int m_year;
    
    /// The security number
    private int m_security;
}
