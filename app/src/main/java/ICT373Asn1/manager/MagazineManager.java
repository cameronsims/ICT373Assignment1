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
        ms_paymentMethods = new HashMap<>();
        ms_customers = new HashMap<>();
        ms_magazines = new HashMap<>();
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
    
    /**
     * <p>Runs a file, this being a manifest file, and executes openFile commands on all.</p>
     *
     * @param p_fname The manifest file name we're parsing...
     */
    public void openManifest(final String p_fname) {
        // Print out all of our customers...
        try {
            FileManager.openManifest(p_fname, this);
        } catch (Exception e) {
            // Read from our default file.
            System.out.printf("Error: Cannot find \"manifest.txt\"... Finding \"default.csv\"...\n");
            FileManager.openFile("./data/default.csv", this);
        }
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
     * @param p_year The year that we're billing 
     * @param p_month The month that we're billing
     */
    public void weeklyNotifications(final int p_year, final int p_month) {
        // Print out all of our customers...
        for (Customer c : ms_customers.values()) {
            c.notif(p_year, p_month);
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
        for (Customer c : ms_customers.values()) {
            // Check if the customer's payment method is valid.
            
            // Bill the customer.
            c.bill(p_year, p_month, noOfWeeks);
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
        int noOfWeeks = MonthManager.numberOfWeeks(p_month, p_year);
        
         // Print in console
        System.out.printf("#\n#\n# Performing monthly billing for %d/%d (%d weeks)\n#\n#\n", p_month, p_year, noOfWeeks);
        
        // Give weekly notifs
        for (int week = 0; week < noOfWeeks; week++) {
            weeklyNotifications(p_month, p_year);
        }
        
        // Bill the people
        this.monthlyBilling(p_month, p_year);
    }
    
    /**
     * <p>Returns the payment methods that are stored in the program, stored by account number.</p>
     * @return Gets the map representing customers.
     */
    public Map<String, PaymentMethod> getPaymentMethods() {
        return ms_paymentMethods;
    }
    
    /**
     * <p>Returns the customers that are stored in the program, stored by email.</p>
     * @return Gets the map representing customers.
     */
    public Map<String, Customer> getCustomers() {
        return ms_customers;
    }
    
    /**
     * <p>Gets magazines, stored by the name</p>
     * @return Gets the map representing customers.
     */
    public Map<String, Magazine> getMagazines() {
        return ms_magazines;
    }
    
    /// Used to keep all the entries of payment.
    private static Map<String, PaymentMethod> ms_paymentMethods;
    
    /// Used to keep all entries of the current members.
    private static Map<String, Customer> ms_customers;
    
    /// Used to keep the magazines, the supplements are apart of the magazines...
    private static Map<String, Magazine> ms_magazines;
}
