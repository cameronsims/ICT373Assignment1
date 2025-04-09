package ICT373Asn1.manager;

import ICT373Asn1.exceptions.*;
import ICT373Asn1.object.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * <p>Manages input from files.</p>
 *
 * @author Cameron Sims
 * @since 21/03/2025
 */
public class FileManager extends UserInputManager {
    
    /**
     * <p>Parses array of values</p>
     * 
     * @param p_tokens The arguments we're parsing through
     * @param p_manager The manager singleton we're parsing.
     * 
     * @throws Exception If input is not valid
     * @throws InvalidCostException Thrown if the cost is not valid.
     * @throws InvalidNameException Thrown when name is invalid.
     * @throws InvalidEmailException Thrown when not a valid email
     * @throws InvalidMonthException If month input is not valid
     * @throws InvalidAccountNumberException If the account number is not valid
     * @throws InvalidSecurityNumberException If the security number is not valid
     */
    private static void m_parseData(final String[] p_tokens, final MagazineManager p_manager) throws ManagerException, InvalidNameException, InvalidEmailException, InvalidCostException, InvalidMonthException, InvalidAccountNumberException, InvalidSecurityNumberException, InvalidBSBException {
        // Input type we're going to use..
        String type = p_tokens[0].toLowerCase();
        switch (type) {
            // If we want to input a magazine.
            case "magazine": {
                // We require 3 arguments.
                if (p_tokens.length != 3) {
                    throw new ManagerException("Not enough elements, we require 3.");
                }
                m_createMagazine(p_tokens, p_manager.getMagazines());
            } break;
            
            // If we want to add a magazine to a user 
            case "addmagazine": {
                // We require 3 arguments.
                if (p_tokens.length != 3) {
                    throw new ManagerException("Not enough elements, we require 3.");
                }
                m_linkMagazineCustomer(p_tokens, p_manager.getCustomers(), p_manager.getMagazines());
            } break; 
            
            // If we want to input a supplement
            case "supplement": {
                // We require 4 arguments.
                if (p_tokens.length != 4) {
                    throw new ManagerException("Not enough elements, we require 4.");
                }
                m_createSupplement(p_tokens, p_manager.getMagazines());
            } break;
            
            // If we want to add a supplement to a user 
            case "addsupplement": {
                // We require 4 arguments.
                if (p_tokens.length != 4) {
                    throw new ManagerException("Not enough elements, we require 4.");
                }
                m_linkSupplementCustomer(p_tokens, p_manager.getCustomers(), p_manager.getMagazines());
            } break; 
            
            // If we want to input a credit card.
            case "creditcard": {
                // We require 6 arguments.
                if (p_tokens.length != 6) {
                    throw new ManagerException("Not enough elements, we require 6.");
                }
                m_createCreditCard(p_tokens, p_manager.getPaymentMethods());
            } break;
            
            // If we want to input a direct debt
            case "directdebt": {
                // We require 4 arguments.
                if (p_tokens.length != 4) {
                    throw new ManagerException("Not enough elements, we require 4.");
                }
                m_createDirectDebit(p_tokens, p_manager.getPaymentMethods());
            } break;
            
            // If we want to input a paying customer...
            case "payingcustomer": {
                // We require 4 arguments.
                if (p_tokens.length != 4) {
                    throw new ManagerException("Not enough elements, we require 4.");
                }
                m_createPayingCustomer(p_tokens, p_manager.getPaymentMethods(), p_manager.getCustomers());
            } break;
            
            // If we want to input an associate customer...
            case "associatecustomer": {
                // We require 3 arguments.
                if (p_tokens.length != 4) {
                    throw new ManagerException("Not enough elements, we require 4.");
                }
                m_createAssociateCustomer(p_tokens, p_manager.getCustomers());
            } break;
            
            // If we want to remove a customer 
            case "removecustomer": {
                // We require 2 arguments.
                if (p_tokens.length != 2) {
                    throw new ManagerException("Not enough elements, we require 2.");
                }
                m_removeCustomer(p_tokens, p_manager.getCustomers());
            } break;
            
            // If we want to set the year.
            case "monthlybilling": {
                // We require 3 arguments.
                if (p_tokens.length != 3) {
                    throw new ManagerException("Not enough elements, we require 3.");
                }
                m_monthlyBilling(p_tokens, p_manager);
            } break;
           
            default: {
                // None...
            } break;
        }
    }
    
    /**
     * <p>Runs a file with data.</p>
     *
     * @param p_fname The file name we're parsing...
     * @param p_manager The MagazineManager instance we're passing through
     */
    public static void openFile(final String p_fname, final MagazineManager p_manager) {
        // Read the file...
        String line = "";
        File file = null;
        Scanner input = null;
        try {
            // Open file, open stream
            file = new File(p_fname); 
            input = new Scanner(file);
        } catch (FileNotFoundException e){
            System.out.printf("Error: There was no file \"%s\" found!\n", p_fname);
            return;
        }
            
           
        // Read per line...
        long i = 0;
        while (input.hasNextLine()) {
            try {
                // Increment the line number, first. 
                // This syncs it up to if we throw an error.
                i++; 
                
                // Get new line to read.
                line = input.nextLine();
                
                // Split tokens...
                String[] tokens = line.split(",");
                m_parseData(tokens, p_manager);
            } catch (Exception e) {
                System.out.printf("Error: Direguarding row #%d. \"%s\"\nReason: %s\n", i, line, e.toString());
            }
        } 
        
    }

    /**
     * <p>Runs a file, this being a manifest file, and executes openFile commands on all.</p>
     *
     * @param p_fname The manifest file name we're parsing...
     * @param p_manager The manager singleton we're parsing.
     * @throws FileNotFoundException Thrown if we haven't found a manifest file. 
     */
    public static void openManifest(final String p_fname, final MagazineManager p_manager) throws FileNotFoundException {
        // Check if our manifest is a file 
        File manifest = new File("./data/manifest.txt");
        Scanner input = new Scanner(manifest);
        
        while (input.hasNextLine()) {
            String line = input.nextLine();
            System.out.printf("%s %s\n", System.getProperty("user.dir"), "/data/" + line);
            openFile("./data/" + line, p_manager);
        }
    }
}
