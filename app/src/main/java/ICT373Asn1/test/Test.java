package ICT373Asn1.test;

public class Test {
    
    private static class Result {
        public Result() {
            this.passed = 0;
            this.amount = 0;
        }
        
        public void runTest(boolean result) {
            if (result) {
                passed++;
            }
            amount++;
        }
        
        public int getPassed() { return this.passed; }
        public int getAmount() { return this.amount; }
        public float getScore() { return (float)(this.passed / this.amount); }
        public void print(String name) {
            System.out.printf("%s Tests: (%d/%d) = %f%%\n", name, passed, amount, this.getScore());
        }
        
        private int passed;
        private int amount;
    };
    
    private static String m_resultName(boolean b) {
        return (b) ? "Success" : "Failure";
    } 
    
    private static Result m_testCustomer() {
        // Create structure.
        System.out.printf("Customer Tests:\n");
        Result r = new Result();
        
        // Attempt the tests.
        boolean nameResult = CustomerTest.test_name();
        System.out.printf("  Name: %s\n", m_resultName(nameResult));
        r.runTest(nameResult);
        
        boolean emailResult = CustomerTest.test_email();
        System.out.printf("  Email: %s\n", m_resultName(emailResult));
        r.runTest(emailResult);
        
        boolean magazineResult = CustomerTest.test_magazine();
        System.out.printf("  Magazine: %s\n", m_resultName(magazineResult));
        r.runTest(magazineResult);
        
        boolean supplementResult = CustomerTest.test_supplements();
        System.out.printf("  Supplements: %s\n", m_resultName(supplementResult));
        r.runTest(supplementResult);
        
        boolean relationshipResult = CustomerTest.test_relationship();
        System.out.printf("  Relationship: %s\n", m_resultName(relationshipResult));
        r.runTest(relationshipResult);
        
        return r;
    }
    
    private static Result m_testMagazine() {
        // Create structure.
        System.out.printf("Magazine Tests:\n");
        Result r = new Result();
        
        // Attempt the tests.
        boolean nameResult = MagazineTest.test_name();
        System.out.printf("  Name: %s\n", m_resultName(nameResult));
        r.runTest(nameResult);
        
        // Attempt the tests.
        boolean costResult = MagazineTest.test_cost();
        System.out.printf("  Cost: %s\n", m_resultName(costResult));
        r.runTest(costResult);
        
        // Attempt the tests.
        boolean supplementResult = MagazineTest.test_supplements();
        System.out.printf("  Supplement: %s\n", m_resultName(supplementResult));
        r.runTest(supplementResult);
        
        return r;
    }
    
    private static Result m_testSupplement() {
        // Create structure.
        System.out.printf("Supplement Tests:\n");
        Result r = new Result();
        
        // Attempt the tests.
        boolean nameResult = SupplementTest.test_name();
        System.out.printf("  Name: %s\n", m_resultName(nameResult));
        r.runTest(nameResult);
        
        // Attempt the tests.
        boolean costResult = SupplementTest.test_cost();
        System.out.printf("  Cost: %s\n", m_resultName(costResult));
        r.runTest(costResult);
        
        // Attempt the Magazine.
        boolean magazineResult = SupplementTest.test_magazines();
        System.out.printf("  Magazine: %s\n", m_resultName(magazineResult));
        r.runTest(costResult);
        
        return r;
    }
    
    private static Result m_testPaymentMethod() {
        // Create structure.
        System.out.printf("Payment Method (Credit+Debit) Tests:\n");
        Result r = new Result();
        
        // Attempt the tests.
        boolean nameResult = PaymentMethodTest.test_name();
        System.out.printf("  Name: %s\n", m_resultName(nameResult));
        r.runTest(nameResult);
        
        // Attempt the tests.
        boolean numberResult = PaymentMethodTest.test_number();
        System.out.printf("  Number: %s\n", m_resultName(numberResult));
        r.runTest(numberResult);
        
        // Attempt BSB
        boolean bsbResult = PaymentMethodTest.test_bsb();
        System.out.printf("  BSB: %s\n", m_resultName(bsbResult));
        r.runTest(bsbResult);
        
        // Attempt Month/Year
        boolean monthYearResult = PaymentMethodTest.test_monthYear();
        System.out.printf("  Month/Year: %s\n", m_resultName(monthYearResult));
        r.runTest(monthYearResult);
        
        // Attempt Security 
        boolean securityResult = PaymentMethodTest.test_security();
        System.out.printf("  Security: %s\n", m_resultName(securityResult));
        r.runTest(securityResult);
        
        return r;
    }
    
    private static Result m_testMonth() {
        // Create structure.
        System.out.printf("MonthManager:\n");
        Result r = new Result();
        
        // Attempt the day.
        boolean yearResult = MonthTest.test_year();
        System.out.printf("  Year: %s\n", m_resultName(yearResult));
        r.runTest(yearResult);
        
        // Attempt the tests.
        boolean monthResult = MonthTest.test_month();
        System.out.printf("  Month: %s\n", m_resultName(monthResult));
        r.runTest(monthResult);
        
        return r;
    }
    
    public static void main(String[] args) {
        // Run all tests
        
        // Customer Test
        m_testCustomer().print("Customer");
        
        // Magazine Test
        m_testMagazine().print("Magazine");
        
        // Supplement Test
        m_testSupplement().print("Supplement");
        
        // Payment Method Test
        m_testPaymentMethod().print("Payment Method (Credit+Debit)");
        
        // MonthManager Test
        m_testMonth().print("MonthManager");
        
    }
    
}
