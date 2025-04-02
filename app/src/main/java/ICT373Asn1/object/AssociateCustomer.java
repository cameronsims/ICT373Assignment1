package ICT373Asn1.object;

import ICT373Asn1.exceptions.InvalidEmailException;
import ICT373Asn1.exceptions.InvalidNameException;

/**
 * <p>A Customer who doesn't pay, but has someone pay for them.</p>
 * 
 * @author Cameron Sims
 * @since 11/03/2025
 */
public class AssociateCustomer extends Customer {
    /**
     * <p>Constructor of the Associate Customer</p>
     *
     * @param p_name The name of the associate customer
     * @param p_email The email of the associate customer
     * @param p_payer The customer who pays for this customer
     * @throws InvalidNameException Thrown when name is invalid.
     * @throws InvalidEmailException Thrown when not a valid email
     */
    public AssociateCustomer(final String p_name, final String p_email, final PayingCustomer p_payer) throws InvalidNameException, InvalidEmailException {
        // Call super constructor 
        super(p_name, p_email);
        
        // Set the payer, this is the payer's business now. 
        p_payer.addAssociate(this);
    }
    
    /**
     * <p>Removes their associate customers / paying customer </p>
     */
    @Override
    public void remove() {
        // Remove us from them.
        this.m_payer.remove(this);
        this.m_payer = null;
    }
    
    /**
     * <p>Returns true if the customer has some one who pays for them...</p>
     *
     * @return True if somebody pays for them, false if not.
     */
    private boolean m_isValid() {
        return ( this.m_payer != null );
    }
    
    /**
     * <p>Returns boolean value based on if the payment is valid, implemented by the sub classes.</p>
     *
     * @return True if they are getting the magazines... False if they do not have a valid payment...
     */
    @Override
    protected boolean m_isValidPayment() {
        // If we do not have a payer, return false.
        if (!this.m_isValid()) {
            return false;
        }
        
        // If the payer doesn't have a valid payment return false
        // If all passes succeeded, return true.
        return this.m_payer.m_isValidPayment();
    }
    
    /**
     * <p>Sets the payer of this customer</p>
     *
     * @param p_payer The person who pays for this customer
     */
    public void setPayer(final PayingCustomer p_payer) {
        // If the user 
        if (this.m_isValid()) {
            // Remove it...
            p_payer.removeAssociate(this);
        }
        
        if (p_payer != null) {
            // If we are in it, do not bother adding us...
            if (p_payer.getAssociates().indexOf(this) < 0) {
                // Add to the paying customer
                p_payer.addAssociate(this);
            }
        }
        
        // Add the payer, even if null...
        this.m_payer = p_payer;
    }
    
    /**
     * <p>Gets the payer of this account</p>
     *
     * @return The reference to the class who pays for this.
     */
    public PayingCustomer getPayer() {
        return this.m_payer;
    }
    
    /**
     * <p>Prints out the details of the customer.</p>
     */
    @Override
    public void print() {
        // Only print if we have a paying customer
        if (!this.m_isValid()) {
            return;
        }
        
        System.out.printf("%s (%s) ",  this.getName(), this.getEmail());
        System.out.printf(" [ ");
        for (Supplement s : this.getSupplements()) {
           System.out.printf("(%s, $%f) ", s.getName(), s.getCost());
        }
        System.out.printf("]");
        
        if (this.m_isValid()) {
            System.out.printf(" <%s %s>", this.m_payer.getName(), this.m_payer.getEmail());
        }
        
        System.out.printf("\n");
    }
   
    // The person who pays for this person (assumed one)
    private PayingCustomer m_payer;
    
}
