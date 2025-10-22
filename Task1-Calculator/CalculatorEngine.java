/**
 * Core calculation engine with basic and advanced arithmetic operations
 * Implements robust exception handling for all operations
 */
public class CalculatorEngine {
    
    private static final double EPSILON = 1e-10;
    
    // Basic Operations
    public double add(double a, double b) {
        validateNumber(a, "First operand");
        validateNumber(b, "Second operand");
        return a + b;
    }
    
    public double subtract(double a, double b) {
        validateNumber(a, "First operand");
        validateNumber(b, "Second operand");
        return a - b;
    }
    
    public double multiply(double a, double b) {
        validateNumber(a, "First operand");
        validateNumber(b, "Second operand");
        double result = a * b;
        checkOverflow(result);
        return result;
    }
    
    public double divide(double a, double b) throws DivisionByZeroException {
        validateNumber(a, "Dividend");
        validateNumber(b, "Divisor");
        
        if (Math.abs(b) < EPSILON) {
            throw new DivisionByZeroException("Cannot divide by zero");
        }
        
        double result = a / b;
        checkOverflow(result);
        return result;
    }
    
    // Advanced Operations
    public double power(double base, double exponent) {
        validateNumber(base, "Base");
        validateNumber(exponent, "Exponent");
        
        if (base < 0 && !isInteger(exponent)) {
            throw new InvalidOperationException(
                "Cannot raise negative number to non-integer power");
        }
        
        double result = Math.pow(base, exponent);
        checkOverflow(result);
        validateNumber(result, "Result");
        return result;
    }
    
    public double squareRoot(double number) {
        validateNumber(number, "Number");
        
        if (number < 0) {
            throw new InvalidOperationException(
                "Cannot calculate square root of negative number");
        }
        
        return Math.sqrt(number);
    }
    
    public double modulo(double a, double b) throws DivisionByZeroException {
        validateNumber(a, "Dividend");
        validateNumber(b, "Divisor");
        
        if (Math.abs(b) < EPSILON) {
            throw new DivisionByZeroException("Cannot perform modulo with zero divisor");
        }
        
        return a % b;
    }
    
    public double absolute(double number) {
        validateNumber(number, "Number");
        return Math.abs(number);
    }
    
    // Scientific Operations
    public double sine(double degrees) {
        validateNumber(degrees, "Angle");
        double radians = Math.toRadians(degrees);
        return Math.sin(radians);
    }
    
    public double cosine(double degrees) {
        validateNumber(degrees, "Angle");
        double radians = Math.toRadians(degrees);
        return Math.cos(radians);
    }
    
    public double tangent(double degrees) {
        validateNumber(degrees, "Angle");
        double radians = Math.toRadians(degrees);
        
        // Check for asymptotes (90°, 270°, etc.)
        double normalizedDegrees = degrees % 180;
        if (Math.abs(normalizedDegrees - 90) < EPSILON || 
            Math.abs(normalizedDegrees + 90) < EPSILON) {
            throw new InvalidOperationException(
                "Tangent is undefined at " + degrees + "°");
        }
        
        return Math.tan(radians);
    }
    
    public double logarithm(double number) {
        validateNumber(number, "Number");
        
        if (number <= 0) {
            throw new InvalidOperationException(
                "Logarithm is only defined for positive numbers");
        }
        
        return Math.log10(number);
    }
    
    public double naturalLog(double number) {
        validateNumber(number, "Number");
        
        if (number <= 0) {
            throw new InvalidOperationException(
                "Natural logarithm is only defined for positive numbers");
        }
        
        return Math.log(number);
    }
    
    public long factorial(int n) {
        if (n < 0) {
            throw new InvalidOperationException(
                "Factorial is only defined for non-negative integers");
        }
        
        if (n > 20) {
            throw new InvalidOperationException(
                "Factorial of " + n + " exceeds long capacity (max n=20)");
        }
        
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        
        return result;
    }
    
    // Helper methods
    private void validateNumber(double number, String name) {
        if (Double.isNaN(number)) {
            throw new InvalidOperationException(name + " is NaN (Not a Number)");
        }
        if (Double.isInfinite(number)) {
            throw new InvalidOperationException(name + " is infinite");
        }
    }
    
    private void checkOverflow(double result) {
        if (Double.isInfinite(result)) {
            throw new OverflowException("Result exceeds maximum representable value");
        }
    }
    
    private boolean isInteger(double value) {
        return Math.abs(value - Math.round(value)) < EPSILON;
    }
}