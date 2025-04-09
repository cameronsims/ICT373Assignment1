package ICT373Asn1.object;

import ICT373Asn1.exceptions.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public PaymentMethod(final String p_name, final String p_number) throws InvalidNameException, InvalidAccountNumberException {
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
        if (p_name == null || p_name.length() == 0) {
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
    abstract public void setNumber(final String p_number) throws InvalidAccountNumberException;
    
    /**
     * <p>This number gets rid of the styles of numbers</p>
     * 
     * @param p_str The string we're reading from
     * @return The string that is managed by this class.
     */
    public static String s_getNumber(final String p_str) {
        // If null, return nothing.
        String number = p_str.strip();
        String temp  = new String();
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            if ('0' <= c && c <= '9') {
                temp += c;
            }
        }
        
        return temp;
    }
    
    /**
     * <p>This number gets rid of the styles of numbers</p>
     * 
     * @param p_numberSet The number we're checking...
     * @param p_amountOfSets The amount of sets that the number will have
     * @return Boolean representing if the string is valid.
     */
    protected static boolean s_validNumber(final String p_numberSet, final int p_amountOfSets) {
        // Match for numbers.
        String amount = String.valueOf(p_amountOfSets);
        String regex = "^([0-9]{4}(-|\\ )?){" + amount + "}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(p_numberSet);
        return matcher.find();
    }
    
    /**
     * <p>Gets the account number </p>
     *
     * @return Returns the number of the account
     */
    public String getNumber() {
        return this.m_number;
    }
    
    /// The name of the account
    protected String m_name;
    
    /// The number of the account/payment method
    protected String m_number;
}
