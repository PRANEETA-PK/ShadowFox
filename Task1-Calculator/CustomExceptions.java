/**
 * Base exception class for calculator-related errors
 */
class CalculatorException extends RuntimeException {
    public CalculatorException(String message) {
        super(message);
    }
    
    public CalculatorException(String message, Throwable cause) {
        super(message, cause);
    }
}

/**
 * Exception for division by zero errors
 */
class DivisionByZeroException extends CalculatorException {
    public DivisionByZeroException(String message) {
        super(message);
    }
}

/**
 * Exception for invalid mathematical operations
 */
class InvalidOperationException extends CalculatorException {
    public InvalidOperationException(String message) {
        super(message);
    }
}

/**
 * Exception for invalid user input
 */
class InvalidInputException extends CalculatorException {
    public InvalidInputException(String message) {
        super(message);
    }
}

/**
 * Exception for arithmetic overflow
 */
class OverflowException extends CalculatorException {
    public OverflowException(String message) {
        super(message);
    }
}