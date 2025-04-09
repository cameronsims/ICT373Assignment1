package ICT373Asn1;

import ICT373Asn1.manager.MagazineManager;
import java.io.File;
import java.util.Scanner;

/**
 * <p>This is the entry point of the program.</p>
 * 
 * @author Cameron Sims
 * @since 11/03/2025
 */
public class App {
    /**
     * <p>The entry function of the program</p>
     * @param args The arguments passed in by the program.
     */
    public static void main(String[] args) {
        // Show who made the program
        getStudentDetails();
        
        // Initialise the set (BinarySearchTree functionality), we're going to use this to find, remove and add customers.
        MagazineManager manager = new MagazineManager();
        
        // If we have no arguments, just use our basic test 1 csv.
        if (args.length > 0) {
            // Read from all input...
            for (String file : args) {
                manager.openFile(file);
            }
        } else {
            // Check if our manifest is a file 
            // This function will automatically call the default file it has an issue
            manager.openManifest("./data/manifest.txt");
        }
        
        System.out.printf("\nThe program has concluded!\n");
        
    }
    
    /**
     * <p>Prints out author details</p>
     */
    private static void getStudentDetails() {
        System.out.println("#######################################################");
        System.out.println("#                                                     #");
        System.out.println("#               Cameron Sims - 34829454               #");
        System.out.println("#                ICT373 - Assignment 1                #");
        System.out.println("#                   MagazineManager                   #");
        System.out.println("#                                                     #");
        System.out.println("#######################################################");
    }
    
    /**
     * <p>Purely to stop the javadoc from giving us errors, this will not be used.</p>
     **/
    private App() {}
}
