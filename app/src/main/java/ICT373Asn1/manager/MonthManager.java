package ICT373Asn1.manager;

import ICT373Asn1.exceptions.InvalidMonthException;

/**
 * <p>Manages months</p>
 *
 * @author Cameron Sims
 * @since 21/03/2025
 */
public class MonthManager {
    
    /**
     * <p>Determines the number of days in a month</p>
     *
     * @param p_month The month that is registered under the credit card
     * @param p_year The year that is registered.
     * @throws InvalidMonthException Thrown when number of days is not valid.
     * @return Integer number representing the amount of days in a month.
     */
    public static int numberOfDays(final int p_month, final int p_year) throws InvalidMonthException {
        // If it is not a valid question don't answer, punish user for being stupid.
        if (!isValid(p_month)) {
            throw new InvalidMonthException();
        }
        
        // If the day is feburary...
        if (p_month == 2) {
            return (isLeapYear(p_year) ? 29 : 28);
        }
        
        // prior to August...
        if (p_month < 8) {
            // If even return 30 else return 31.
            return (p_month % 2 == 0) ? 30 : 31;
        }
        
        // If it is August-December.
        // Inverse the previous rules.
        return (p_month % 2 == 0) ? 31 : 30;
    }
    
    /**
     * <p>Determines the number of weeks in a month</p>
     *
     * @param p_month The month that is registered under the credit card
     * @param p_year The year that is registered.
     * @return Integer number representing the amount of weeks in a month.
     */
    public static int numberOfWeeks(final int p_month, final int p_year) {
        // Get the amount of SUNDAYS.
        // There is absolutely positively a way better way of doing this, but this works.
        int amountOfDays = 0;
        
        try {
            amountOfDays = numberOfDays(p_month, p_year);
        } catch (Exception e) {
            return 0;
        }
        
        int amountOfSundays = 0;
        for (int i = 1; i <= amountOfDays; i++) {
            if (getWeekDay(i, p_month, p_year) == 0) {
                amountOfSundays++;
            }
        }
        
        return amountOfSundays;
    }
    
    /**
     * <p>Determines the weekday (0-6) (Sunday-Saturday)</p>
     *
     * @param p_day The day we want to take in
     * @param p_month The month that we want to read.
     * @param p_year The year that we want to take in.
     * @return Integer number representing the amount of weeks in a month.
     */
    public static int getWeekDay(final int p_day, final int p_month, final int p_year) {
        // Use University of Waterloo California expression...
        // This is a modified version of Tomohiko Sakatomo's algorithm
        // https://cs.uwaterloo.ca/~alopez-o/math-faq/node73.html
        //
        // WeekDay = (Day + (2.6*Month - 0.2) - 2*(Century-1) + Year + (Year/4) + ((Century-1)/4)) % 7
        //
        
        // These are the month offsets, every month needs this correction value to be corrected.
        int[] dayOffset = { 0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4 };
        
        // If the month is before march, we want to treat it like the preceeding year.
        int month = p_month;
        int year = (month < 3) ? p_year - 1: p_year;
        
        int oneHundredCorrection = p_year/100;
        int fourHundredCorrection =  p_year/400;
        int yearOffset = fourHundredCorrection - oneHundredCorrection;
        int offsettedDay = p_day + dayOffset[month-1];
        int yearBase = year + year / 4;
        return (yearBase + yearOffset + offsettedDay) % 7;
    }
    
    
    /**
     * <p>Determines if a year is a leap year</p>
     *
     * @param p_year The year that is registered under the credit card
     * @return Boolean value depending on if the year is a leap year
     */
    public static boolean isLeapYear(final int p_year) {
        // If the year is divisible by 4, it could be a leap year.
        // If it is not, then if is disqualified.
        boolean divisbleBy4 = p_year % 4 == 0;
        if (!divisbleBy4) {
            return false;
        }
        
        // If the year is divisible by 100 then it could be a leap year
        //    unless it is also divisble by 400.
        boolean divisibleBy100 = p_year % 100 == 0;
        boolean divisibleBy400 = p_year % 400 == 0;
        return !divisibleBy100 || divisibleBy400;
    }
    
    /**
     * <p>Determines if a date is valid</p>
     *
     * @param p_month The month that is registered under the credit card
     * @return Boolean value depending on if the month is valid
     */
    public static boolean isValid(final int p_month) {
        return (0 < p_month && p_month < 13);
    }
    
    
}
