package ICT373Asn1.manager;
import java.util.Map;

import ICT373Asn1.exceptions.*;
import ICT373Asn1.object.*;

/**
 * <p>Manages input from files and console.</p>
 * 
 * @author Cameron Sims
 * @since 21/03/2025
 */
public class UserInputManager {
    /**
     * <p>Creates a magazine instance</p>
	 *
     * @param p_tokens The arguments we're parsing through
     * @param p_magazines The magazines + supplements that are entered in the system
     * @throws InvalidNameException Thrown when name is invalid.
     * @throws InvalidCostException If input is not valid
     */
    protected static void m_createMagazine(final String[] p_tokens, final Map<String, Magazine> p_magazines) throws InvalidNameException, InvalidCostException {
        String name = p_tokens[1];
        float cost = Float.parseFloat(p_tokens[2]);
        Magazine m = new Magazine(name, cost);
        p_magazines.put(name, m);
    }
    
    /**
     * <p>Link Magazine and Customer together</p>
     *
     * @param p_tokens The arguments we're parsing through
     * @param p_customers The customers that we have entered into the system
     * @param p_magazines The magazines + supplements that are entered in the system
     */
    protected static void m_linkMagazineCustomer(final String[] p_tokens, final Map<String, Customer> p_customers, final Map<String, Magazine> p_magazines) {
        String magazineName = p_tokens[1];
        String customerEmail = p_tokens[2];
        
        // Link together...
        Magazine magazine = p_magazines.get(magazineName); 
        Customer customer = p_customers.get(customerEmail);
        customer.addMagazine(magazine);
    }
    
    /**
     * <p>Creates a new supplement</p>
     *
     * @param p_tokens The arguments we're parsing through
     * @param p_magazines The magazines + supplements that are entered in the system
     * @throws InvalidNameException Thrown when name is invalid.
     * @throws InvalidCostException If input is not valid
     */
    protected static void m_createSupplement(final String[] p_tokens, final Map<String, Magazine> p_magazines) throws InvalidNameException, InvalidCostException {
        String magazineName = p_tokens[3];
                Magazine magazine = p_magazines.get(magazineName);
                
                String name = p_tokens[1];
                float cost = Float.parseFloat(p_tokens[2]);
                Supplement s = new Supplement(magazine, name, cost);
    }
    
    /**
     * <p>Link Magazine and Customer together</p>
     *
     * @param p_tokens The arguments we're parsing through
     * @param p_customers The customers that we have entered into the system
     * @param p_magazines The magazines + supplements that are entered in the system
     * @throws ManagerException If input is not valid
     */
    protected static void m_linkSupplementCustomer(final String[] p_tokens, final Map<String, Customer> p_customers, final Map<String, Magazine> p_magazines) throws ManagerException {
        String supplementName = p_tokens[1];
                String magazineName = p_tokens[2];
                String customerEmail = p_tokens[3];
                
                // Link together...
                Magazine magazine = p_magazines.get(magazineName);
                Customer customer = p_customers.get(customerEmail);
                Supplement supplement = null;
                
                // Find the supplement...
                for (Supplement s : magazine.getSupplements()) {
                    if (s.getName().equals(supplementName)) {
                        supplement = s;
                        break;
                    }
                }
                
                // If it doesn't exist, throw
                if (supplement == null) {
                    throw new ManagerException("Supplement not found!");
                }
                
                customer.addSupplement(supplement);
    }
    
    /**
     * <p>Creates a credit card.</p>
     *
     * @param p_tokens The arguments we're parsing through
     * @param p_paymentMethods The methods of payment that we're going to add.
     * @throws InvalidNameException Thrown if the name is not valid.
     * @throws InvalidMonthException If month input is not valid 
     * @throws InvalidAccountNumberException If the account number is not valid
     * @throws InvalidSecurityNumberException If the security number is not valid
     */
    protected static void m_createCreditCard(final String[] p_tokens, final Map<Long, PaymentMethod> p_paymentMethods) throws InvalidNameException, InvalidMonthException, InvalidAccountNumberException, InvalidSecurityNumberException {
        // String p_name, long p_number, int p_month, int p_year, int p_security
                String name = p_tokens[1];
                long number = Long.parseLong(p_tokens[2]);
                int month = Integer.parseInt(p_tokens[3]);
                int year = Integer.parseInt(p_tokens[4]);
                int security = Integer.parseInt(p_tokens[5]);
                PaymentMethod pm = new CreditCard(name, number, month, year, security);
                p_paymentMethods.put(number, pm);
    }
    
    /**
     * <p>Create a direct debit account</p>
     *
     * @param p_tokens The arguments we're parsing through
     * @param p_paymentMethods The methods of payment that we're going to add.
     * @throws InvalidNameException Thrown if the name is not valid.
     * @throws InvalidAccountNumberException If the account number is not valid
     * @throws InvalidBSBException If the bsb number is not valid
     */
    protected static void m_createDirectDebit(final String[] p_tokens, final Map<Long, PaymentMethod> p_paymentMethods) throws InvalidNameException, InvalidAccountNumberException, InvalidBSBException {
        // String p_name, long p_number, int p_bsb
                String name = p_tokens[1];
                long number = Long.parseLong(p_tokens[2]);
                int bsb = Integer.parseInt(p_tokens[3]);
                PaymentMethod pm = new DirectDebit(name, number, bsb);
                p_paymentMethods.put(number, pm);
    }
    
    /**
     * <p>Create a paying customer.</p>
     *
     * @param p_tokens The arguments we're parsing through
     * @param p_paymentMethods The methods of payment that we're going to add.
     * @param p_customers The customers that we have entered into the system
     * @throws InvalidNameException Thrown when name is invalid.
     * @throws InvalidEmailException Thrown when not a valid email
     */
    protected static void m_createPayingCustomer(final String[] p_tokens, final Map<Long, PaymentMethod> p_paymentMethods, final Map<String, Customer> p_customers) throws InvalidNameException, InvalidEmailException {
        String name = p_tokens[1];
        String email = p_tokens[2];
        long number = Long.parseLong(p_tokens[3]);
                
        PaymentMethod method = p_paymentMethods.get(number);
                
        Customer c = new PayingCustomer(name, email, method);
        p_customers.put(email, c);
    }
    
    /**
     * <p>Create an associate customer.</p>
     *
     * @param p_tokens The arguments we're parsing through
     * @param p_customers The customers that we have entered into the system
     * @throws InvalidNameException Thrown when name is invalid.
     * @throws InvalidEmailException Thrown when not a valid email
     */
    protected static void m_createAssociateCustomer(final String[] p_tokens, final Map<String, Customer> p_customers) throws InvalidNameException, InvalidEmailException {
        String name = p_tokens[1];
                String email = p_tokens[2];
                String payerEmail = p_tokens[3];
                
                // This is a dummy customer who is used for their email, we will assume they are a paying customer...
                PayingCustomer payer = (PayingCustomer)p_customers.get(payerEmail);
                Customer c = new AssociateCustomer(name, email, payer);
                p_customers.put(email, c);
    }
    
    /**
     * <p>Removes an existing customer.</p>
     *
     * @param p_tokens The arguments we're parsing through
     * @param p_customers The customers that we have entered into the system
     */
    protected static void m_removeCustomer(final String[] p_tokens, final Map<String, Customer> p_customers) {
        // Get the email.
        String email = p_tokens[1];
        
        // Get the customer in the customer.
        Customer c = p_customers.get(email);
        
        // Remove all their associate / remove from their payer.
        c.remove();
        p_customers.remove(email);
    }
    
    /**
     * <p>Prints out the worth of the month into the console.</p>
     *
     * @param p_tokens The token's we're accepting
     * @param p_manager The singleton we're retrieving data from.
     **/
    protected static void m_monthlyBilling(final String[] p_tokens, final MagazineManager p_manager) {
        // Get month and year.
        int month = Integer.parseInt(p_tokens[1]);
        int year  = Integer.parseInt(p_tokens[2]);
        p_manager.printForMonth(month, year);
    }
    
    
}
