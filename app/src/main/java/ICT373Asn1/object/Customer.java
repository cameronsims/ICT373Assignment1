package ICT373Asn1.object;
import ICT373Asn1.exceptions.InvalidEmailException;
import ICT373Asn1.exceptions.InvalidNameException;
import ICT373Asn1.object.Supplement;
import java.util.ArrayList; // Used to hold children.
import java.lang.Comparable; // This is used to index it in a tree...
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>This is the base for a customer, the customer must be a subclass.
 *
 * @author Cameron Sims
 * @since 11/03/2025
 */
public abstract class Customer implements Comparable<Customer> {
    
    /**
     * <p>Initialises the class</p>
     *
     * @param p_name The name of the customer
     * @param p_email The email of the customer
     * @throws InvalidNameException Thrown when name is invalid.
     * @throws InvalidEmailException Thrown when not a valid email
     */
    public Customer(final String p_name, final String p_email) throws InvalidNameException, InvalidEmailException {
        // Initialised Data Structures.
        this.m_magazines   = new ArrayList<>();
        this.m_supplements = new ArrayList<>();
        
        this.setName(p_name);
        this.setEmail(p_email);
    }
    
    /**
     * <p>Changes the name of the customer</p>
     *
     * @param p_name The new name of the customer
     * @throws InvalidNameException Thrown when name is invalid.
     */
    public void setName(final String p_name) throws InvalidNameException {
        if (p_name == null || p_name.length() == 0) {
            throw new InvalidNameException();
        }
        this.m_name = p_name;
    }
    
    /**
     * <p>Gets the name of the customer</p>
     *
     * @return Returns the name of the customer
     */
    public String getName() {
        return this.m_name;
    }
    
    /**
     * <p>Changes the email of the customer</p>
     *
     * @param p_email The new email of the customer
     * @throws InvalidEmailException Thrown when not a valid email
     */
    public void setEmail(final String p_email) throws InvalidEmailException {
        // Make sure the email fits.
        if (p_email.length() == 0) {
            throw new InvalidEmailException();
        }
        
        // We want to use regex to match it.
        Pattern pattern = Pattern.compile("^[A-z]+@[A-z]+\\.[A-z]+$");
        Matcher matcher = pattern.matcher(p_email);
        if (!matcher.find()) {
            throw new InvalidEmailException();
        }
        
        this.m_email = p_email;
    }
 
    /**
     * <p>Changes the email of the customer</p>
     *
     * @return Returns the email of the customer
     */
    public String getEmail() {
        return this.m_email;
    }
    
    /**
     * <p>Adds a magazine to the list</p>
     *
     * @param p_magazine The magazine that we're adding to the customer.
     */
    public void addMagazine(final Magazine p_magazine) {
        // If we already have the magazine don't even bother.
        if (this.m_magazines.contains(p_magazine)) {
            return;
        }
        
        this.m_magazines.add(p_magazine);
    }
    
    /**
     * <p>Gets the magazines that the subscriber has</p>
     *
     * @return Returns the magazines that the subscriber has
     */
    public ArrayList<Magazine> getMagazines() {
        // Copy and return
        return new ArrayList<>(this.m_magazines);
    }
    
    /**
     * <p>Adds a supplement to the list</p>
     *
     * @param p_supplement The supplement we're adding to the customer
     */
    public void addSupplement(final Supplement p_supplement) {
        // If we already have it don't even bother. 
        if (this.m_supplements.contains(p_supplement)) {
            return;
        }
        
        // Check if it is in our supplements, if the magazine is in our list
        // If it is not, add it to our magazines.
        if (!this.m_magazines.contains(  p_supplement.getMagazine() )) {
            this.m_magazines.add(p_supplement.getMagazine() );
        }
        
        this.m_supplements.add(p_supplement);
    }
 
    /**
     * <p>Gets the supplements that the subscriber has</p>
     *
     * @return Returns the supplements that the subscriber has
     */
    public ArrayList<Supplement> getSupplements() {
        // Copy and return
        return new ArrayList<>(this.m_supplements);
    }
    
    /**
     * <p>Prints out the details of the customer.</p>
     */
    public void print() {
        System.out.printf("%s (%s):\n",  this.m_name, this.m_email);
        System.out.printf("+ Supplements:\n");
        for (Supplement s : this.m_supplements) {
           System.out.printf("  + %s $%f\n", s.getName(), s.getCost());
        }
    }
    
    /**
     * <p>Removes their associate customers / paying customer </p>
     */
    public abstract void remove();
    
    /**
     * <p>Returns boolean value based on if the payment is valid, implemented by the sub classes.</p>
     *
     * @return True if they are getting the magazines... False if they do not have a valid payment...
     */
    abstract boolean m_isValidPayment();
    
    /**
     * <p>Gives notification to a customer about new issues.</p>
     */
    public void notif() {
        // If they are not valid, do not notify them...
        if (!this.m_isValidPayment() || this.m_magazines.isEmpty()) {
            return;
        }
        
        // Print
        System.out.printf("-------------------------------------------------------\n");
        System.out.printf("TO: %s\n", this.getEmail());
        System.out.printf("Hello %s,\n\n", this.getName());
        System.out.printf("There are new issues of the magazines you are subscribed to!\n");
        
        
        // Print magazines that we're subscribed to...
        for (Magazine m : this.getMagazines()) {
            System.out.printf(" - %s\n", m.getName());
            for (Supplement s : m.getSupplements()) {
                // If it exists, print it.
                if (this.m_supplements.indexOf(s) >= 0) {
                    float thisCost = s.getCost();
                    System.out.printf("   + %s\n", s.getName(), thisCost);
                }
            }
        }
        
        System.out.printf("\nSee you next time!\nMagazine Service\n");
        System.out.printf("-------------------------------------------------------\n");
    }
    
    /**
     *
     * <p>Bills a paying customer</p>
     * @param p_weeks The month we're billing for.
     */
    public void bill(final int p_weeks) {
        // Don't do anything, we are not paying for anything...
        // This is for the payingcustomer, and only the paying customer to do.
    }
    
    /**
     * <p>Used to index and hash the class, this should be unique.</p>
     *
     * @param p_customer The customer to compare to.
     */
    @Override
    public int compareTo(final Customer p_customer) {
        // Return the email, this is the unique attribute
        return this.m_email.compareTo(p_customer.m_email);
    }
    
    /// The name of the customer
    protected String m_name;
    
    /// The email that the customer has
    protected String m_email;
    
    /// The magazines that customer is subscribed to.
    protected ArrayList<Magazine> m_magazines;
    
    /// The supplements that the customer has.
    protected ArrayList<Supplement> m_supplements;
}
