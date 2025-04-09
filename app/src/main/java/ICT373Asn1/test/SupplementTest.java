package ICT373Asn1.test;

import ICT373Asn1.object.*;
import ICT373Asn1.exceptions.*;

public class SupplementTest {
    
    public static boolean test_name() {
        // Run all of these names, these are valid names.
        Magazine m;
        try {
            // True if we succeed all of them
            m = new Magazine("m1", 0.00f);
            new Supplement(m, "Supplement", 1.00f);
            new Supplement(m, "Free Supplement", 0.00f);
            
            // Test strings systematically.
            String name = ".";
            for (int i = 0; i < 64; i++) {
                new Supplement(m, name, 1.00f);
                name += ".";
            }
            
        } catch (Exception e) {
            // False if we failed.
            return false;
        }
        
        // All of these should fail.
        String[] names = new String[]{ "", null, "\0" };
        for (int i = 0; i < names.length; i++) {
            try {
                // Attempt, this should fail.
                // If it doesn't, return false.
                new Supplement(m, names[i], 0.00f);
                return false;
            } catch (Exception e) {
            
            }
        }
        
        return true;
    }
    
    public static boolean test_cost() {
        Magazine m;
        try {
            m = new Magazine("m1", 0.00f);
        } catch (Exception e) {
            return false;
        }
        
        // Test valid costs...
        try {
            String name = "Testing Name";
            float cost = 0.00f;
            for (int i = 0; i < 128; i++) {
                new Supplement(m, name, cost);
                cost = (float)Math.pow(2.00f, i);
            }
        } catch (Exception e) {
            return false;
        }
        
        // Attempt invalid values.
        float[] costs = { -1.00f, Float.NEGATIVE_INFINITY, -2.50f, -(float)Math.E, -(float)Math.PI, -(float)Math.TAU };
        for (int i = 0; i < costs.length; i++) {
            try {
                new Supplement(m, "Test", costs[i]);
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
    
    public static boolean test_magazines() {
        try {
            // Test ability.
            Magazine m1 = new Magazine("M1", 0.00f);
            Magazine m2 = new Magazine("M2", 0.00f);
            Magazine m3 = new Magazine("M3", 0.00f);
            
            // Check that these match all the magazines.
            Supplement s1 = new Supplement(m1, "S1", 0.00f);
            Supplement s2 = new Supplement(m2, "S2", 0.00f);
            Supplement s3 = new Supplement(m3, "S3", 0.00f);
            Supplement s4 = new Supplement(new Magazine("M4", 0.00f), "S4", 0.00f);
            
            if (m1.getSupplements().indexOf(s1) < 0 ||
                m2.getSupplements().indexOf(s2) < 0 ||
                m3.getSupplements().indexOf(s3) < 0) 
            { 
                return false; 
            }
            
            // Check if s4 is in any of the previous 
            return ( m1.getSupplements().indexOf(s4) < 0 && 
                     m2.getSupplements().indexOf(s4) < 0 &&
                     m3.getSupplements().indexOf(s4) < 0);
        } catch (Exception e) {
            return false;
        }
    }
}
