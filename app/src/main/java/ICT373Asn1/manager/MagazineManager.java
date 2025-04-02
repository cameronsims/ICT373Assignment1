package ICT373Asn1.manager;

import java.util.HashMap;
import java.util.Map;

import ICT373Asn1.object.Customer;
import ICT373Asn1.object.PaymentMethod;
import ICT373Asn1.object.Magazine;

/**
 * <p>Manages interactions between classes.</p>
 * 
 * @author Cameron Sims
 * @since 21/03/2025
 */
public class MagazineManager {
    /**
     * <p>Creates the MagazineManager class</p>
     */
    public MagazineManager() {
        this.m_paymentMethods = new HashMap<>();
        this.m_customers = new HashMap<>();
        this.m_magazines = new HashMap<>();
    }
    
    /**
     * <p>Runs a file, inputting it into the manager.</p>
     *
     * @param p_fname The file name we're parsing...
     */
    public void openFile(final String p_fname) {
        // Print out all of our customers...
        FileManager.openFile(p_fname, this);
    }
    
    ///**
    // * <p>Creates a loop of asking from terminal</p>
    // */
    //public void useTerminal() {
    //    // Ask user
    //    InputManager.ask(m_paymentMethods, m_customers, m_magazines);
    //}
    
    /**
     * <p>Gives notifications to all customers about new issues. </p>
     *
     */
    public void weeklyNotifications() {
        // Print out all of our customers...
        for (Customer c : this.m_customers.values()) {
            c.notif();
        }
    }
    
    /**
     * <p>Create monthly billing for a month.
     *
     * @param p_month The month we're billing for.
     * @param p_year The year we're billing for
     */
    public void monthlyBilling(final int p_month, final int p_year) {
        // Get the amount of 
        int noOfWeeks = MonthManager.numberOfWeeks(p_month, p_year);
        
        // Print out all of our customers...
        for (Customer c : this.m_customers.values()) {
            c.bill(noOfWeeks);
        }
    }
    
    /**
     * <p>Gives notifications to all customers about new issues and all billings for the month.
     *
     * @param p_month The month we're checking for
     * @param p_year The year we're checking for.
     */
    public void printForMonth(final int p_month, final int p_year) {
        // Get number of weeks...
        
        // Get the amount of 
        int noOfWeeks = MonthManager.numberOfWeeks(p_month, p_year);
        for (int week = 0; week < noOfWeeks; week++) {
            weeklyNotifications();
        }
        this.monthlyBilling(p_month, p_year);
    }
    
    /**
     * <p>Returns the payment methods that are stored in the program, stored by account number.</p>
     * @return Gets the map representing customers.
     */
    public Map<Long, PaymentMethod> getPaymentMethods() {
        return this.m_paymentMethods;
    }
    
    /**
     * <p>Returns the customers that are stored in the program, stored by email.</p>
     * @return Gets the map representing customers.
     */
    public Map<String, Customer> getCustomers() {
        return this.m_customers;
    }
    
    /**
     * <p>Gets magazines, stored by the name</p>
     * @return Gets the map representing customers.
     */
    public Map<String, Magazine> getMagazines() {
        return this.m_magazines;
    }
    
    /// Used to keep all the entries of payment.
    private Map<Long, PaymentMethod> m_paymentMethods;
    
    /// Used to keep all entries of the current members.
    private Map<String, Customer> m_customers;
    
    /// Used to keep the magazines, the supplements are apart of the magazines...
    private Map<String, Magazine> m_magazines;
}
