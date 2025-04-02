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
        
        private int passed;
        private int amount;
    };
    
    private static String m_resultName(boolean b) {
        return (b) ? "Success" : "Failure";
    } 
    
    private static Result m_testCustomer() {
        // Create structure.
        System.out.printf("Customer Tests:");
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
    
    public static void main(String[] args) {
        // Run all tests
        Result customerResult = m_testCustomer();
        
        
        
    }
    
}
