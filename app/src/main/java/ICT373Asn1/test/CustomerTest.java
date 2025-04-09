package ICT373Asn1.test;

import ICT373Asn1.object.*;
import ICT373Asn1.exceptions.*;
    
import java.util.ArrayList;
import java.util.HashSet;

public class CustomerTest {
    
    public static boolean test_name() {
        try {
            // Attempt valid names 
            PaymentMethod pc_method = new DirectDebit("Cameron", "11111111", 1);
            PayingCustomer pc = new PayingCustomer("Cameron", "cameronissacsims@gmail.com", pc_method);

            AssociateCustomer ac = new AssociateCustomer("Goblin 1", "goblin@gmail.com", pc);
            
            // This is now an invalid name.
            try {
                AssociateCustomer c = new AssociateCustomer("", "goblin2@gmail.com", pc);
                return false;
            } catch (InvalidNameException e) {
                
            }
            
            
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }
    
    public static boolean test_email() {
        // Valid Emails
        String[] valid = { "cameronissacsims@gmail.com", "first.last@hotmail.com", "a@b.c", "34829454@student.murdoch.edu.au" };
        
        // Invalid Emails
        String[] invalid = { "@", ".", "@.", "a@b", "a@", "a@.c", "@b.c", "@.c", "", null, " ", "Idon'treallyknowwhatelsetotest" };
    
        try {
            PaymentMethod method = new CreditCard("Name", "1111-1111-1111-1111", 1, 1, 1);
            for (int i = 0; i < valid.length; i++) {
                PayingCustomer c1 = new PayingCustomer("Name", valid[i], method);
                AssociateCustomer c2 = new AssociateCustomer("Name", valid[i], c1);
            }
        } catch (Exception e) {
            return false;
        }
        
        for (int i = 0; i < valid.length; i++) {
            try { 
                Customer c1 = new PayingCustomer("Name", invalid[i], null);
                return false;
            } catch (Exception e) {
                
            }
            try { 
                Customer c2 = new AssociateCustomer("Name", invalid[i], null); 
                return false;
            } catch (Exception e) {
                
            }
        }
        
        
        return true;
    }
    
    public static boolean test_magazine() {
        try {
            // Attempt to join the supplement without having the magazine
            PayingCustomer pc = new PayingCustomer("pc", "pc@cool.com", new DirectDebit("c", "1111-1111", 111));
            Customer c = new AssociateCustomer("c", "c@cool.com", pc);
            
            Magazine m = new Magazine("M1", 1.0f);
            Supplement s = new Supplement(m, "S1", 1.0f);
            
            c.addSupplement(s);
            
            return c.getMagazines().indexOf(m) >= 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static boolean test_supplements() {
        try {
            
            Magazine m = new Magazine("Magazine", 1.00f);
            ArrayList<Supplement> s = new ArrayList<>(); 
            
            s.add(new Supplement(m, "Supplement 1", 0.125f));
            s.add(new Supplement(m, "Supplement 2", 0.25f));
            s.add(new Supplement(m, "Supplement 3", 0.5f));
            s.add(new Supplement(m, "Supplement 4", 0.75f));
            s.add(new Supplement(m, "Supplement 5", 1.0f));
            
            PaymentMethod pm = new CreditCard("Name", "1111111111111111", 1, 2024, 1);
            PayingCustomer pc = new PayingCustomer("Name", "name@example.com", pm);
            
            AssociateCustomer[] ac = {
                new AssociateCustomer("AC1", "ac1@example.com", pc),
                new AssociateCustomer("AC2", "ac2@example.com", pc),
                new AssociateCustomer("AC3", "ac3@example.com", pc),
                new AssociateCustomer("AC4", "ac4@example.com", pc),
                new AssociateCustomer("AC5", "ac5@example.com", pc)
            };
            
            for (int i = 0; i < ac.length; i++) {
                ac[i].addMagazine(m);
                ac[i].addSupplement(s.get(i));
            }
            
            // Ensure we get it all.
            HashSet<Supplement> set = new HashSet<>();
            for (AssociateCustomer c : ac) {
                ArrayList<Supplement> sups = c.getSupplements();
                for (Supplement sup : sups) {
                    if (!set.contains(sup)) {
                        set.add(sup);
                    }
                }
            }
            
            return set.containsAll(s);
        } catch (Exception e) {
            return false;
        }
    }
        
    public static boolean test_relationship() {
        try {
            PayingCustomer pc = new PayingCustomer("PC", "paying@customer.com", new DirectDebit("PC", "1111-1111", 1));
            
            final int n = 100;
            AssociateCustomer[] associates = new AssociateCustomer[n];
            for (int i = 0; i < n; i++) {
                String email = "associate" + i + "@customer.com";
                associates[i] = new AssociateCustomer("Associate " + i, email, pc);
                if (associates[i].getPayer() != pc) {
                    return false;
                }
            }
            
            if (pc.getAssociates().size() != n) {
                return false;
            }
            
            // Remove every even.
            for (int i = 0; i < n/2; i++) {
                associates[2*i].remove();
            }
            
            if (pc.getAssociates().size() != (n / 2)) {
                return false;
            }
            
            // Remove odd.
            for (int i = 0; i < n/2; i++) {
                pc.remove(associates[2*i + 1]);
            }
            
            return pc.getAssociates().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
    
    
}
