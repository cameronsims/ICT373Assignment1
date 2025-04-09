package ICT373Asn1.object;
import ICT373Asn1.exceptions.InvalidEmailException;
import ICT373Asn1.exceptions.InvalidNameException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * <p>A Customer who pays, may pay for other customers.</p>
 * 
 * @author Cameron Sims
 * @since 11/03/2025
 *
 *
 */
public class PayingCustomer extends Customer {
    /**
     * <p>Constructor of the Paying Customer</p>
     *
     * @param p_name The name of the customer
     * @param p_email The email of the customer
     * @param p_method The method of payment
     * @throws InvalidNameException Thrown when name is invalid.
     * @throws InvalidEmailException Thrown when not a valid email
     */
    public PayingCustomer(String p_name, String p_email, PaymentMethod p_method) throws InvalidNameException, InvalidEmailException {
        // Construct the superclass
        super(p_name, p_email);
        
        this.setPaymentMethod(p_method);
        this.m_associates = new ArrayList<>();
    }
    
    /**
     * <p>Sets the method of payment</p>
     *
     * @param p_method The way that we are paying
     */
    public void setPaymentMethod(PaymentMethod p_method) {
        this.m_paymentMethod = p_method;
    }
    
    /**
     * <p>Gets the method of payment</p>
     *
     * @return Returns the method of payment
     */
    public PaymentMethod getPaymentMethod() {
        return this.m_paymentMethod;
    }
    
    /**
     * <p>Adds a new customer</p>
     *
     * @param p_customer The customer we're adding.
     */
    public void addAssociate(AssociateCustomer p_customer) {
        // Add to our array, and reference this in the sub class.
        this.m_associates.add(p_customer);
        p_customer.setPayer(this);
    }
    
    /**
     * <p>Removes an associate...</p>
     *
     * @param p_customer The customer we're removing.
     */
    public void removeAssociate(AssociateCustomer p_customer) {
        // Add to our array, and reference this in the sub class.
        this.m_associates.remove(p_customer);
        p_customer.setPayer(null);
    }
    
    /**
     * <p>Gets the associate customers</p>
     *
     * @return Returns the list of associate customers
     */
    public ArrayList<AssociateCustomer> getAssociates() {
        return this.m_associates;
    }
    
    /**
     * <p>Removes their associate customers / paying customer </p>
     */
    @Override
    public void remove() {
        // Remove all our associates.
        for (AssociateCustomer ac : this.m_associates) {
            // Remove us from them.
            ac.remove();
        }
        
        // Delete all customers
        this.m_associates = new ArrayList<>();
    }
    
    /**
     * <p>Removes their associate customers / paying customer </p>
     *
     * @param p_customer The associate we need to remove.
     */
    public void remove(AssociateCustomer p_customer) {
        // Remove all our associates.
        this.m_associates.remove(p_customer);
    }
    
    
    /**
     * <p>Returns boolean value based on if the payment is valid, implemented by the sub classes.</p>
     * 
     * @param p_year The year that we're billing 
     * @param p_month The month that we're billing
     *
     * @return True if they are getting the magazines... False if they do not have a valid payment...
     */
    @Override
    protected boolean m_isValidPayment(final int p_year, final int p_month) {
        // Check if we have a payment method.
        if (this.m_paymentMethod == null) {
            return false;
        }
        
        // If it is a Credit Card...
        if (this.m_paymentMethod.getClass().getSimpleName().equals("CreditCard")) {
            CreditCard c = (CreditCard)this.m_paymentMethod;
            int month = c.getMonth();
            int year = c.getYear();
            
            // Check if the year is equal
            if (year != p_year) {
                // Check if the year is higher, if it is not. 
                // IT IS NOT VALID.
                return (year > p_year);
            }
            
            // If the year is equal, check if the month is bigger.
            // If the month is the same, or lower. It is valid.
            return (month > p_month);
        }
        
        return true;
    }
    
    /**
     * <p>Prints out the details of the customer.</p>
     */
    @Override
    public void print() {
        // Print their details...
        super.print();
        
        // Print out their payment method and their associates...
        System.out.printf("+ Payment Method: %s %d\n", this.m_paymentMethod.getName(), this.m_paymentMethod.getNumber());
        if (!this.m_associates.isEmpty()) {
            System.out.printf("+ Associate Customers:\n");
            for (AssociateCustomer ac : m_associates) {
                // Set some indentation 
                System.out.printf("  + ");
                ac.print();
            }
        }
    }
    
    /**
     * <p>Bills a paying customer</p>
     *
     * @param p_year The year that we're billing 
     * @param p_month The month that we're billing
     * @param p_weeks The weeks we're billing for.
     */
    @Override
    public void bill(final int p_year, final int p_month, final int p_weeks) {
        // If we are not billing, get out of here!
        if (!this.m_isValidPayment(p_year, p_month)) {
            return;
        }
        
        // Create a new structure full of the magazines and supplements which our associates + us are subscribed to.
        HashSet<Magazine> mags = new HashSet<>();
        HashSet<Supplement> sups = new HashSet<>();
        
        // Add all of our magazines and supplements.
        for (Magazine m : this.m_magazines) {
            mags.add(m);
        }
        for (Supplement s : this.m_supplements) {
            sups.add(s);
        }
        
        // Once we've added all of ours, we will add all of our associates...
        for (AssociateCustomer ac : this.m_associates) {
            // Add magazines and supplements...
            for (Magazine m : ac.m_magazines) {
                mags.add(m);
            }
            for (Supplement s : ac.m_supplements) {
                sups.add(s);
            }
        }
        
        // Then we want to print all of the magazine, IF we have things.
        if (mags.isEmpty()) {
            return;
        }
        
        // The total cost
        float total = 0;
        
        // Print the header.
        System.out.printf("-------------------------------------------------------\n");
        System.out.printf("TO: %s\n", this.getEmail());
        System.out.printf("Hello %s,\n\n", this.getName());
        System.out.printf("We have charged your account for the month (%d weeks)!\nHere are new issues of the magazines you are subscribed to!\n", p_weeks);
        
        
        // Print for all magazines...
        for (Magazine m : mags) {
            // For all supplements this has...
            float monthlyMagCost = p_weeks*m.getCost();
            System.out.printf(" + %s : $%f\n", m.getName(), monthlyMagCost);
            total += monthlyMagCost;
            
            // Check if they exist in the hashset.
            for (Supplement s : m.getSupplements()) {
                // If it exists...
                // Add to total.
                if (sups.contains(s)) {
                    float monthlySupCost = p_weeks*s.getCost();
                    System.out.printf("  + %s : $%f\n", s.getName(), monthlySupCost);
                    total += monthlySupCost;
                }
            }
            
            System.out.printf(" + Total: $%f\n", total);
        }
        
        System.out.printf("Grand Sum: $%f\n", total);
        System.out.printf("\nSee you next time!\nMagazine Service\n");
        System.out.printf("-------------------------------------------------------\n");
    }
    
   
    /// The payment method that the customer pays with 
    private PaymentMethod m_paymentMethod;
    
    /// The associates that the customer has
    private ArrayList<AssociateCustomer> m_associates;
}
