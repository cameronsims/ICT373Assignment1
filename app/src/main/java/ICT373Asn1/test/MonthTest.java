package ICT373Asn1.test;

import ICT373Asn1.object.*;
import ICT373Asn1.exceptions.*;
import ICT373Asn1.manager.MonthManager;

import java.util.Calendar;
import java.time.*;

public class MonthTest {
    
    public static boolean test_year() {
        // Check leap years.
        try {
            
            Calendar c = Calendar.getInstance();
            
            for (int i = 1969; i < 2050; i++) {
                c.set(Calendar.YEAR, i);
                boolean camLeapYear = MonthManager.isLeapYear(i);
                boolean javaLeapYear =  (c.getActualMaximum(Calendar.DAY_OF_YEAR) > 365);
                if (camLeapYear != javaLeapYear) {
                    return false;
                }
            }
        
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    public static boolean test_month() {
        try {
            // Get our calendar.
            Calendar c = Calendar.getInstance();
            
            // Check amount of days per month
            // Compare to Java Result
            for (int i = 1969; i < 2050; i++)
            for (int j = 1; j < 13; j++) {
                YearMonth ym = YearMonth.of(i, j);
                int javaDays = ym.lengthOfMonth();
                int camDays = MonthManager.numberOfDays(j, i);
                
                // Check if days are the same.
                if (javaDays != camDays) {
                    return false;
                }
                
                // Check week days are the same
                for (int k = 1; k <= javaDays; k++) {
                    c.set(Calendar.YEAR, i);
                    c.set(Calendar.MONTH, j - 1);
                    c.set(Calendar.DATE, k);
                    int javaDate = c.get(Calendar.DAY_OF_WEEK);
                    int camDate = MonthManager.getWeekDay(k, j, i);
                    
                    if (camDate != javaDate - Calendar.SUNDAY) {
                        return false;
                    }
                }
            }
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
