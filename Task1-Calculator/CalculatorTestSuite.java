/**
 * Comprehensive test suite for Enhanced Calculator
 * Tests all operations and exception handling
 */
public class CalculatorTestSuite {
    
    private static int totalTests = 0;
    private static int passedTests = 0;
    private static int failedTests = 0;
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║   Enhanced Calculator - Test Suite Runner         ║");
        System.out.println("╚════════════════════════════════════════════════════╝\n");
        
        testBasicOperations();
        testAdvancedOperations();
        testScientificOperations();
        testStatisticalOperations();
        testExceptionHandling();
        testHistoryManager();
        testEdgeCases();
        
        printTestSummary();
    }
    
    // ============ BASIC OPERATIONS TESTS ============
    
    private static void testBasicOperations() {
        System.out.println("\n── Testing Basic Operations ──");
        CalculatorEngine engine = new CalculatorEngine();
        
        // Addition
        test("Addition: 5 + 3", engine.add(5, 3), 8.0);
        test("Addition: -5 + 3", engine.add(-5, 3), -2.0);
        test("Addition: 0.1 + 0.2", engine.add(0.1, 0.2), 0.3, 0.0001);
        
        // Subtraction
        test("Subtraction: 10 - 4", engine.subtract(10, 4), 6.0);
        test("Subtraction: -5 - 3", engine.subtract(-5, 3), -8.0);
        
        // Multiplication
        test("Multiplication: 4 * 5", engine.multiply(4, 5), 20.0);
        test("Multiplication: -3 * 7", engine.multiply(-3, 7), -21.0);
        test("Multiplication: 0 * 100", engine.multiply(0, 100), 0.0);
        
        // Division
        test("Division: 10 / 2", engine.divide(10, 2), 5.0);
        test("Division: 7 / 2", engine.divide(7, 2), 3.5);
        test("Division: -10 / 2", engine.divide(-10, 2), -5.0);
    }
    
    // ============ ADVANCED OPERATIONS TESTS ============
    
    private static void testAdvancedOperations() {
        System.out.println("\n── Testing Advanced Operations ──");
        CalculatorEngine engine = new CalculatorEngine();
        
        // Power
        test("Power: 2^3", engine.power(2, 3), 8.0);
        test("Power: 5^0", engine.power(5, 0), 1.0);
        test("Power: 2^-2", engine.power(2, -2), 0.25);
        test("Power: 4^0.5", engine.power(4, 0.5), 2.0);
        
        // Square Root
        test("Square Root: √16", engine.squareRoot(16), 4.0);
        test("Square Root: √2", engine.squareRoot(2), 1.41421356, 0.00001);
        test("Square Root: √0", engine.squareRoot(0), 0.0);
        
        // Modulo
        test("Modulo: 10 % 3", engine.modulo(10, 3), 1.0);
        test("Modulo: 15 % 4", engine.modulo(15, 4), 3.0);
        
        // Absolute
        test("Absolute: |-5|", engine.absolute(-5), 5.0);
        test("Absolute: |5|", engine.absolute(5), 5.0);
        test("Absolute: |0|", engine.absolute(0), 0.0);
    }
    
    // ============ SCIENTIFIC OPERATIONS TESTS ============
    
    private static void testScientificOperations() {
        System.out.println("\n── Testing Scientific Operations ──");
        CalculatorEngine engine = new CalculatorEngine();
        
        // Trigonometric
        test("Sine: sin(0°)", engine.sine(0), 0.0, 0.0001);
        test("Sine: sin(30°)", engine.sine(30), 0.5, 0.0001);
        test("Sine: sin(90°)", engine.sine(90), 1.0, 0.0001);
        
        test("Cosine: cos(0°)", engine.cosine(0), 1.0, 0.0001);
        test("Cosine: cos(60°)", engine.cosine(60), 0.5, 0.0001);
        test("Cosine: cos(90°)", engine.cosine(90), 0.0, 0.0001);
        
        test("Tangent: tan(0°)", engine.tangent(0), 0.0, 0.0001);
        test("Tangent: tan(45°)", engine.tangent(45), 1.0, 0.0001);
        
        // Logarithms
        test("Log: log₁₀(10)", engine.logarithm(10), 1.0, 0.0001);
        test("Log: log₁₀(100)", engine.logarithm(100), 2.0, 0.0001);
        test("Log: log₁₀(1)", engine.logarithm(1), 0.0, 0.0001);
        
        test("Natural Log: ln(e)", engine.naturalLog(Math.E), 1.0, 0.0001);
        test("Natural Log: ln(1)", engine.naturalLog(1), 0.0, 0.0001);
        
        // Factorial
        test("Factorial: 0!", engine.factorial(0), 1);
        test("Factorial: 5!", engine.factorial(5), 120);
        test("Factorial: 10!", engine.factorial(10), 3628800);
    }
    
    // ============ STATISTICAL OPERATIONS TESTS ============
    
    private static void testStatisticalOperations() {
        System.out.println("\n── Testing Statistical Operations ──");
        
        double[] data1 = {1, 2, 3, 4, 5};
        StatisticalCalculator stats1 = new StatisticalCalculator(data1);
        
        test("Mean: [1,2,3,4,5]", stats1.mean(), 3.0);
        test("Median: [1,2,3,4,5]", stats1.median(), 3.0);
        test("Sum: [1,2,3,4,5]", stats1.sum(), 15.0);
        test("Min: [1,2,3,4,5]", stats1.min(), 1.0);
        test("Max: [1,2,3,4,5]", stats1.max(), 5.0);
        test("Range: [1,2,3,4,5]", stats1.range(), 4.0);
        
        double[] data2 = {2, 4, 4, 4, 5, 5, 7, 9};
        StatisticalCalculator stats2 = new StatisticalCalculator(data2);
        
        test("Mean: [2,4,4,4,5,5,7,9]", stats2.mean(), 5.0);
        test("Median: [2,4,4,4,5,5,7,9]", stats2.median(), 4.5);
        test("Mode: [2,4,4,4,5,5,7,9]", stats2.mode(), 4.0);
    }
    
    // ============ EXCEPTION HANDLING TESTS ============
    
    private static void testExceptionHandling() {
        System.out.println("\n── Testing Exception Handling ──");
        CalculatorEngine engine = new CalculatorEngine();
        
        // Division by zero
        testException("Division by zero", 
            () -> engine.divide(10, 0), 
            DivisionByZeroException.class);
        
        // Square root of negative
        testException("Square root of negative", 
            () -> engine.squareRoot(-4), 
            InvalidOperationException.class);
        
        // Logarithm of zero
        testException("Logarithm of zero", 
            () -> engine.logarithm(0), 
            InvalidOperationException.class);
        
        // Logarithm of negative
        testException("Logarithm of negative", 
            () -> engine.logarithm(-5), 
            InvalidOperationException.class);
        
        // Negative factorial
        testException("Negative factorial", 
            () -> engine.factorial(-5), 
            InvalidOperationException.class);
        
        // Large factorial (overflow)
        testException("Factorial overflow (21!)", 
            () -> engine.factorial(21), 
            InvalidOperationException.class);
        
        // Tangent asymptote
        testException("Tangent at 90°", 
            () -> engine.tangent(90), 
            InvalidOperationException.class);
        
        // Modulo by zero
        testException("Modulo by zero", 
            () -> engine.modulo(10, 0), 
            DivisionByZeroException.class);
        
        // Invalid statistical data
        testException("Empty statistical data", 
            () -> new StatisticalCalculator(new double[]{}), 
            InvalidInputException.class);
    }
    
    // ============ HISTORY MANAGER TESTS ============
    
    private static void testHistoryManager() {
        System.out.println("\n── Testing History Manager ──");
        HistoryManager history = new HistoryManager();
        
        test("Initial history size", history.size(), 0);
        
        history.addEntry("5 + 3", 8.0);
        test("History size after 1 entry", history.size(), 1);
        
        history.addEntry("10 - 2", 8.0);
        history.addEntry("4 * 5", 20.0);
        test("History size after 3 entries", history.size(), 3);
        
        history.clearHistory();
        test("History size after clear", history.size(), 0);
        
        // Test history rotation (max 100 entries)
        for (int i = 0; i < 105; i++) {
            history.addEntry("test", i);
        }
        test("History max size limit", history.size(), 100);
    }
    
    // ============ EDGE CASES TESTS ============
    
    private static void testEdgeCases() {
        System.out.println("\n── Testing Edge Cases ──");
        CalculatorEngine engine = new CalculatorEngine();
        
        // Very small numbers
        test("Small number addition", engine.add(1e-10, 1e-10), 2e-10, 1e-15);
        
        // Very large numbers
        test("Large number multiplication", engine.multiply(1e100, 2), 2e100, 1e95);
        
        // Zero operations
        test("Zero power", engine.power(0, 5), 0.0);
        test("Power to zero", engine.power(5, 0), 1.0);
        
        // Negative operations
        test("Negative absolute", engine.absolute(-123.456), 123.456);
        
        // Precision tests
        double result = engine.add(0.1, 0.2);
        test("Floating point precision", Math.abs(result - 0.3) < 0.0001, true);
    }
    
    // ============ HELPER METHODS ============
    
    private static void test(String description, double actual, double expected) {
        test(description, actual, expected, 0.000001);
    }
    
    private static void test(String description, double actual, double expected, double tolerance) {
        totalTests++;
        boolean passed = Math.abs(actual - expected) < tolerance;
        
        if (passed) {
            passedTests++;
            System.out.printf("✓ PASS: %s%n", description);
        } else {
            failedTests++;
            System.out.printf("✗ FAIL: %s (Expected: %.6f, Got: %.6f)%n", 
                description, expected, actual);
        }
    }
    
    private static void test(String description, long actual, long expected) {
        totalTests++;
        boolean passed = actual == expected;
        
        if (passed) {
            passedTests++;
            System.out.printf("✓ PASS: %s%n", description);
        } else {
            failedTests++;
            System.out.printf("✗ FAIL: %s (Expected: %d, Got: %d)%n", 
                description, expected, actual);
        }
    }
    
    private static void test(String description, int actual, int expected) {
        totalTests++;
        boolean passed = actual == expected;
        
        if (passed) {
            passedTests++;
            System.out.printf("✓ PASS: %s%n", description);
        } else {
            failedTests++;
            System.out.printf("✗ FAIL: %s (Expected: %d, Got: %d)%n", 
                description, expected, actual);
        }
    }
    
    private static void test(String description, boolean actual, boolean expected) {
        totalTests++;
        boolean passed = actual == expected;
        
        if (passed) {
            passedTests++;
            System.out.printf("✓ PASS: %s%n", description);
        } else {
            failedTests++;
            System.out.printf("✗ FAIL: %s (Expected: %b, Got: %b)%n", 
                description, expected, actual);
        }
    }
    
    private static void testException(String description, Runnable code, 
                                      Class<? extends Exception> expectedException) {
        totalTests++;
        try {
            code.run();
            failedTests++;
            System.out.printf("✗ FAIL: %s (No exception thrown)%n", description);
        } catch (Exception e) {
            if (expectedException.isInstance(e)) {
                passedTests++;
                System.out.printf("✓ PASS: %s (Caught %s)%n", 
                    description, e.getClass().getSimpleName());
            } else {
                failedTests++;
                System.out.printf("✗ FAIL: %s (Expected %s, Got %s)%n", 
                    description, expectedException.getSimpleName(), 
                    e.getClass().getSimpleName());
            }
        }
    }
    
    private static void printTestSummary() {
        System.out.println("\n╔════════════════════════════════════════════════════╗");
        System.out.println("║              TEST SUITE SUMMARY                    ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.printf("║ Total Tests:  %-33d║%n", totalTests);
        System.out.printf("║ Passed:       %-33d║%n", passedTests);
        System.out.printf("║ Failed:       %-33d║%n", failedTests);
        System.out.printf("║ Success Rate: %.2f%%%30s║%n", 
            (passedTests * 100.0 / totalTests), "");
        System.out.println("╚════════════════════════════════════════════════════╝");
        
        if (failedTests == 0) {
            System.out.println("\n🎉 All tests passed! Calculator is working perfectly!");
        } else {
            System.out.println("\n⚠️  Some tests failed. Please review the failures above.");
        }
    }
}