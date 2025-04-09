package ICT373Asn1.test;

import ICT373Asn1.object.*;
import ICT373Asn1.exceptions.*;

import java.util.ArrayList;

public class MagazineTest {
    
    public static boolean test_name() {
        // Run all of these names, these are valid names.
        try {
            // True if we succeed all of them
            new Magazine("Magazine", 1.00f);
            new Magazine("Free Magazine", 0.00f);
            
            // Test strings systematically.
            String name = ".";
            for (int i = 0; i < 64; i++) {
                new Magazine(name, 1.00f);
                name += ".";
            }
            
        } catch (Exception e) {
            // False if we failed.
            return false;
        }
        
        // All of these should fail.
        String[] invalid = new String[]{ "", null, "\0" };
        for (int i = 0; i < invalid.length; i++) {
            try {
                // Attempt, this should fail.
                // If it doesn't, return false.
                new Magazine(invalid[i], 0.00f);
                return false;
            } catch (Exception e) {
            
            }
        }
        
        return true;
    }
    
    public static boolean test_cost() {
        // Test valid costs...
        try {
            String name = "Testing Name";
            float cost = 0.00f;
            for (int i = 0; i < 128; i++) {
                new Magazine(name, cost);
                cost = (float)Math.pow(2.00f, i);
            }
        } catch (Exception e) {
            return false;
        }
        
        // Attempt invalid values.
        float[] costs = { -1.00f, Float.NEGATIVE_INFINITY, -2.50f, -(float)Math.E, -(float)Math.PI, -(float)Math.TAU };
        for (int i = 0; i < costs.length; i++) {
            try {
                new Magazine("Test", costs[i]);
                return false;
            } catch (InvalidNameException e) {
                // We do not want this
                return false;
            } catch (InvalidCostException e) {
                // We want this to occur.
            }
        }
        
        return true;
    }
    
    public static boolean test_supplements() {
        Magazine m = null;
        try {
            m = new Magazine("Parent", 0.00f);
        } catch (Exception e) {
            return false;
        }
        
        // Add a bunch of supplements to our magazine.
        final int n = 1000;
        for (int i = 0; i < n; i++) {
            try {
                String name = "S" + i;
                new Supplement(m, name, (float)i);
            } catch (Exception e) {
                return false;
            }
        }
        
        // Check to ensure that everything is there.
        ArrayList<Supplement> supplements = m.getSupplements();
        if (supplements.size() != n) {
            return false;
        }
        for (int i = 0; i < supplements.size(); i++) {
            Supplement s = supplements.get(i);
            if (s.getCost() != (float)i || !s.getName().equals( "S" + i )) {
                return false;
            }
        }
        
        return true;
    }
}
