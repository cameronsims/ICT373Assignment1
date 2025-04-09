package ICT373Asn1.test;

import ICT373Asn1.object.*;
import ICT373Asn1.exceptions.*;

import java.util.ArrayList;
import java.util.Date;
import java.time.YearMonth;

public class PaymentMethodTest {
    
    public static boolean test_name() {
        try {
            // Tests the name on the card / account
            
            // These are valid names we're testing...
            String[] valid = { "Jeremy", "Joshua", "Jason", "Jackson", "Jimmy", "Johan", "Jeffery", "Jake", "Johannes", "Jane", "John", "Jill" };
            for (int i = 0; i < valid.length; i++) {
                PaymentMethod c = new CreditCard(valid[i], "1111-1111-1111-1111", i + 1, i, i);
                PaymentMethod d = new DirectDebit(valid[i], "1111 1111", i);
                
                if (!c.getName().equals(d.getName()) || !c.getName().equals(valid[i])) {
                    return false;
                }
            }
            
            // These are invalid names 
            String[] invalid = { "", null };
            for (int i = 0; i < invalid.length; i++) {
                try { 
                    PaymentMethod c = new CreditCard(invalid[i], "1111-1111-1111-1111", 1, 1, 1); 
                    return false; 
                } catch (InvalidNameException e) {}
                try { 
                    PaymentMethod d = new DirectDebit(invalid[i], "1111 1111", 1); 
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
            String[] valid_credit = { "1111-1111-1111-1111", "1111111111111111", "1111 1111 1111 1111", "1111-1111 1111 1111", "11111111 1111-1111" };
            String[] valid_debit =  { "1111-1111", "11111111", "1111 1111" };
            try {
                for (String num : valid_credit) {
                    new CreditCard("Jeffery Bezos", num, 1, 1, 1);
                }
                for (String num : valid_debit) {
                    new DirectDebit("Jeffery Bezos", num, 1);
                }
            } catch (InvalidAccountNumberException e) {
                return false;
            }
            
            // Test invalid inputs.
            String[] invalid = { "", null, "\0", "o4ktoktokgr", "00464663-6-64-64-4-53-", "34EE-4564-4656-5754" };
            for (String num : valid_debit) {
                try {
                    new CreditCard("Name", num, 1, 1, 1);
                    System.out.println("1");
                    return false;
                } catch (InvalidAccountNumberException e) { }
            }
            for (String num : valid_credit) {
                try {
                    new DirectDebit("Name", num, 1);
                    System.out.println("2");
                    return false;
                } catch (InvalidAccountNumberException e) {  }
            }
            for (String num : invalid) {
                try {
                    new CreditCard("Name", num, 1, 1, 1);
                    System.out.println("3");
                    return false;
                } catch (InvalidAccountNumberException e) {  }
                try {
                    new DirectDebit("Name", num, 1);
                    System.out.println("4");
                    return false;
                } catch (InvalidAccountNumberException e) {  }
            }
            
            return true;
            
            
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    public static boolean test_bsb() {
        // Since BSB is an integral data type. 
        // It is significantly easier to test it.
        for (int i = -100000; i < 10000; i++) {
            try {
                new DirectDebit("Name", "1111-1111", i);
            } catch (InvalidBSBException e) {
                if (0 <= i && i <= 999999) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        
        return true;
    }
    
    public static boolean test_monthYear() {
        // All these datas should work.
        for (int i = -1000; i < 1000; i++)
        for (int j = 1; j < 13; j++) {
            try {
                new CreditCard("Cameron", "1111-1111-1111-1111", j, i, 1);
            } catch (Exception e) {
                return false;
            }
        }
        
        // All these should fail
        for (int i = -1000; i < 1000; i++) {
            for (int j = -10; j < 1; j++) {
                try {
                    new CreditCard("Cameron", "1111-1111-1111-1111", j, i, 1);
                    return false;
                } catch (Exception e) {}
            }
            for (int j = 13; j < 20; j++) {
                try {
                    new CreditCard("Cameron", "1111-1111-1111-1111", j, i, 1);
                    return false;
                } catch (Exception e) {}
            }
        }
            
        return true;
    
    }
    
    public static boolean test_security() { 
        // Since BSB is an integral data type. 
        // It is significantly easier to test it.
        for (int i = -100000; i < 10000; i++) {
            try {
                new CreditCard("Name", "1111-1111-1111-1111", 1, 1, i);
            } catch (InvalidSecurityNumberException e) {
                if (0 <= i && i <= 999) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        
        return true;
    }
    
}
