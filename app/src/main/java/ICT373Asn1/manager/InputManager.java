package ICT373Asn1.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

import ICT373Asn1.object.*;
import ICT373Asn1.exceptions.*;

/**
 * <p>This is how we deal with inputting things from the system</p>
 * 
 * @author Cameron Sims
 * @since 11/03/2025
 *
 *
 */
public class InputManager extends UserInputManager {
    
    /**
     * <p>Parses array of values</p>
     *
     * @param p_token The argument we're parsing through
     * @throws Exception If input is not valid
     * @throws InvalidNameException Thrown when name is invalid.
     * @throws InvalidEmailException Thrown when not a valid email
     * @throws InvalidCostException Thrown if the cost is not valid.
     * @throws InvalidNameException Thrown when name is invalid.
     * @throws InvalidMonthException If month input is not valid
     * @throws InvalidAccountNumberException If the account number is not valid
     * @throws InvalidSecurityNumberException If the security number is not valid
     */
    private static void m_parseData(String p_token, Map<Long, PaymentMethod> p_paymentMethods, Map<String, Customer> p_customers, Map<String, Magazine> p_magazines) throws InvalidNameException, InvalidEmailException, ManagerException, InvalidCostException, InvalidMonthException, InvalidAccountNumberException, InvalidSecurityNumberException, InvalidBSBException {
        // Input type we're going to use..
        switch (p_token) {
            // If we want to input a magazine.
            case "createmagazine": {
                // Get the magazine name...
                System.out.printf("Magazine Name: ");
                String magazineName = s_input.nextLine();
                
                // Get Magazine Cost...
                System.out.printf("Magazine Cost: ");
                String magazineCost = s_input.nextLine();
                
                String[] tokens = { p_token, magazineName, magazineCost };
                m_createMagazine(tokens, p_magazines);
            } break;
            
            // If we want to add a magazine to a user 
            case "addmagazine": {
                
                // Get the magazine name...
                System.out.printf("Magazine Name: ");
                String magazineName = s_input.nextLine();
                
                // Get Customer email...
                System.out.printf("Customer Email: ");
                String customerEmail = s_input.nextLine();
                
                String[] tokens = { p_token, magazineName, customerEmail };
                m_linkMagazineCustomer(tokens, p_customers, p_magazines);
            } break; 
            
            // If we want to input a supplement
            case "createsupplement": {
                // Get the supplement name...
                System.out.printf("Supplement Name: ");
                String supplementName = s_input.nextLine();
                
                // Get magazine name...
                System.out.printf("Magazine Name: ");
                String magazineName = s_input.nextLine();
                
                // Get supplement cost 
                System.out.printf("Supplement Cost: ");
                String supplementCost = s_input.nextLine();
                
                String[] tokens = { p_token, supplementName, magazineName, supplementCost };
                m_createSupplement(tokens, p_magazines);
            } break;
            
            // If we want to add a supplement to a user 
            case "addsupplement": {
                // Get the supplement name...
                System.out.printf("Supplement Name: ");
                String supplementName = s_input.nextLine();
                
                // Get the magazine name...
                System.out.printf("Magazine Name: ");
                String magazineName = s_input.nextLine();
                
                // Get the user name...
                System.out.printf("Customer Email: ");
                String customerEmail = s_input.nextLine();
                
                String[] tokens = { p_token, supplementName, magazineName, customerEmail };
                m_linkSupplementCustomer(tokens, p_customers, p_magazines);
            } break; 
            
            // If we want to input a credit card.
            case "createcreditcard": {
                // Get the credit card...
                System.out.printf("Name on Card: ");
                String cardName = s_input.nextLine();
                
                // Get the credit card number
                System.out.printf("Card Number: ");
                String cardNumber = s_input.nextLine();
                
                // Get the credit card month of expiry
                System.out.printf("Card Expiry Month: ");
                String cardMonth = s_input.nextLine();
                
                // Get the credit card year of expiry
                System.out.printf("Card Expiry Year: ");
                String cardYear = s_input.nextLine();
                
                // Get the credit card number
                System.out.printf("Card Expiry Security: ");
                String cardSecurity = s_input.nextLine();
                
                String[] tokens = { p_token, cardName, cardNumber, cardMonth, cardYear, cardSecurity};
                m_createCreditCard(tokens, p_paymentMethods);
            } break;
            
            // If we want to input a direct debt
            case "createdirectdebt": {
                // Get the debit name..
                System.out.printf("Name on Account: ");
                String debitName = s_input.nextLine();
                
                // Get the debit number..
                System.out.printf("Account Number: ");
                String debitNumber = s_input.nextLine();
                
                // Get the debit bsb
                System.out.printf("Account BSB: ");
                String debitBSB = s_input.nextLine();
                
                String[] tokens = { p_token, debitName, debitNumber, debitBSB };
                m_createDirectDebit(tokens, p_paymentMethods);
            } break;
            
            // If we want to input a paying customer...
            case "createpayingcustomer": {
                // Get the customer name..
                System.out.printf("Customer Name: ");
                String customerName = s_input.nextLine();
                
                // Get the customer email..
                System.out.printf("Customer Email: ");
                String customerEmail = s_input.nextLine();
                
                // Get the account number we're using..
                System.out.printf("Account Number: ");
                String customerAccountName = s_input.nextLine();
                
                String[] tokens = { p_token, customerName, customerEmail, customerAccountName };
                m_createPayingCustomer(tokens, p_paymentMethods, p_customers);
            } break;
            
            // If we want to input an associate customer...
            case "createassociatecustomer": {
                // Get the customer name..
                System.out.printf("Customer Name: ");
                String customerName = s_input.nextLine();
                
                // Get the customer email..
                System.out.printf("Customer Email: ");
                String customerEmail = s_input.nextLine();
                
                // Get the customer payer.
                System.out.printf("Paying Customer: ");
                String payerEmail = s_input.nextLine();
                
                String[] tokens = { p_token, customerName, customerEmail, payerEmail };
                m_createAssociateCustomer(tokens, p_customers);
            } break;
            
            // If we want to remove a customer...
            case "removecustomer": {
                // Get the email from the user.
                System.out.printf("Customer Email: ");
                String customerEmail = s_input.nextLine();
                
                String[] tokens = { p_token, customerEmail };
                m_removeCustomer(tokens, p_customers);
                
            } break;
            
            case "openfile": {
                System.out.printf("File Path: ");
                String fname = s_input.nextLine();
                //FileManager.openFile(fname, p_paymentMethods, p_customers, p_magazines);
            } break;
            
            case "exit": {
            
            } break;
            
            case "help": {
                System.out.println("Here is a list of Commands:");
                System.out.println(" - Database Commands --------------------------------------------------------------------------------------");
                System.out.println("   - CreateMagazine:          Adds a Magazine to the Database.");
                System.out.println("   - AddMagazine:             Subscribes a Subscriber to a Magazine.");
                System.out.println("   - CreateSupplement:        Adds a Supplement to the Database.");
                System.out.println("   - AddSupplement:           Adds a supplement interest to a customer.");
                System.out.println("   - CreateCreditCard:        Adds a Credit Card to the Database.");
                System.out.println("   - CreateDirectDebt:        Adds a Direct Debit account to the Database.");
                System.out.println("   - CreatePayingCustomer:    Adds a Paying Customer to the Database.");
                System.out.println("   - CreateAssociateCustomer: Adds a non-paying Customer to the Database.");
                System.out.println("   - RemoveCustomer:          Removes the Customer from the Database.");
                System.out.println(" - Terminal Specific Commands -----------------------------------------------------------------------------");
                System.out.println("   - OpenFile:                Opens a file requested by the user, inputs the data into the Database.");
                System.out.println("   - Help:                    Gets a list of all commands.");
                System.out.println("   - Exit:                    Quits the Program, printing out the weekly notifications and monthly billings.");
                System.out.println(" ----------------------------------------------------------------------------------------------------------");
            } break;
            
            default: {
                System.out.printf("Error: Unrecognised input, to see a list of command please type \"help\"!\n");
            } break;
        }
    }
    
    /**
     * <p>Gets a valid integer from console</p>
     *
     * @return Returns an int put by the user
     **/
    public static int getInt() {
        boolean failed = true;
        int integer = 0;
        
        while (failed) {
            try {
                integer = s_input.nextInt();
                failed = false;
            } catch (Exception e) { 
                integer = 0;
                failed = true;
            }
        } 
        
        return integer;
    }
    
    /**
     * <p>Gets a valid month from the user via input</p>
     *
     * @return Returns an int from 1-12 (1=Jan, 12=Dec)
     **/
    public static int getMonth() {
        int month = 0;
        while (!MonthManager.isValid(month)) {
            try {
                month = s_input.nextInt();
            } catch (Exception e) { 
                month = 0;
            }
        }
        return month;
    }
    
    /**
     * <p>Continues a asking loop...</p>
     *
     * @param p_paymentMethods The methods of payment that we're going to add.
     * @param p_customers The customers that we have entered into the system
     * @param p_magazines The magazines + supplements that are entered in the system
     */
    public static void ask(Map<Long, PaymentMethod> p_paymentMethods, Map<String, Customer> p_customers, Map<String, Magazine> p_magazines) {
        // Read the file...
        String line = "";
        try {
            // Read perline...
            String input = "";
            while (!input.equals("exit")) {
                // Get the input
                System.out.printf("Input a command:");
                input = s_input.nextLine().toLowerCase();
                
                m_parseData(input, p_paymentMethods, p_customers, p_magazines);
            } 
        } catch (Exception e) {
            System.out.printf("Error: Direguarding row. \"%s\"\nReason:\n", line, e.toString());
        }
    }
    
    private static Scanner s_input = new Scanner(System.in);
}
