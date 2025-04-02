package ICT373Asn1.test;

import ICT373Asn1.object.*;
import ICT373Asn1.exceptions.*;

import java.util.ArrayList;

public class PaymentMethodTest {
    
    public static boolean test_name() {
        try {
            // Tests the name on the card / account
            
            // These are valid names we're testing...
            String[] valid = { "Jeremy", "Joshua", "Jason", "Jackson", "Jimmy", "Johan", "Jeffery", "Jake", "Johannes", "Jane", "John", "Jill" };
            for (int i = 0; i < valid.length; i++) {
                PaymentMethod c = new CreditCard(valid[i], i, i + 1, i, i);
                PaymentMethod d = new DirectDebit(valid[i], i, i);
                
                if (!c.getName().equals(d.getName()) || c.getName().equals(valid[i])) {
                    return false;
                }
            }
            
            // These are invalid names 
            String[] invalid = { "", null };
            for (int i = 0; i < invalid.length; i++) {
                try { 
                    PaymentMethod c = new CreditCard(invalid[i], 1, 1, 1, 1); 
                    return false; 
                } catch (InvalidNameException e) {}
                try { 
                    PaymentMethod d = new DirectDebit(invalid[i], 1, 1); 
                    return false; 
                } catch (InvalidNameException e) {}
            }
            
            // If both above passed, we win.
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean test_number() {
        try {
            // Test valid and invalid inputs.
            long[] valid = { 1, 10, 100, 1000, 10000, 100000,  1000000, 10000000, 10000000, 100000000, 1000000000, Long.MAX_VALUE };
            try {
                for (long num : valid) {
                    new CreditCard("Jeffery Bezos", num, 1, 1, 1);
                    new DirectDebit("Jeffery Bezos", num, 1);
                }
            } catch (InvalidAccountNumberException e) {
                return false;
            }
            
            // Test invalid inputs.
            long[] invalid = { -1, 0, -1000, Long.MIN_VALUE };
            for (long num : valid) {
                try {
                    new CreditCard("Name", num, 1, 1, 1);
                    return false;
                } catch (InvalidAccountNumberException e) {
                    
                }
                try {
                    new DirectDebit("Name", num, 1);
                    return false;
                } catch (InvalidAccountNumberException e) {
                    
                }
            }
            
            return true;
            
            
        } catch (Exception e) {
            return false;
        }
    }
    
}
