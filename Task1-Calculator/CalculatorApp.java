import java.util.Scanner;

/**
 * Main entry point for the Enhanced Console Calculator
 * Demonstrates modular OOP design with advanced features
 */
public class CalculatorApp {
    private final Scanner scanner;
    private final CalculatorEngine engine;
    private final HistoryManager historyManager;
    private boolean running;

    public CalculatorApp() {
        this.scanner = new Scanner(System.in);
        this.engine = new CalculatorEngine();
        this.historyManager = new HistoryManager();
        this.running = true;
    }

    public void start() {
        displayWelcome();
        
        while (running) {
            try {
                displayMenu();
                int choice = getUserChoice();
                handleMenuChoice(choice);
            } catch (InvalidInputException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Unexpected error: " + e.getMessage());
            }
        }
        
        scanner.close();
        System.out.println("\nThank you for using Enhanced Calculator!");
    }

    private void displayWelcome() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   Enhanced Console Calculator v2.0    ║");
        System.out.println("║     Advanced OOP Design Edition        ║");
        System.out.println("╚════════════════════════════════════════╝\n");
    }

    private void displayMenu() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│          MAIN MENU                  │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.println("│ 1. Basic Operations                 │");
        System.out.println("│ 2. Advanced Operations              │");
        System.out.println("│ 3. Scientific Operations            │");
        System.out.println("│ 4. Statistical Operations           │");
        System.out.println("│ 5. View Calculation History         │");
        System.out.println("│ 6. Clear History                    │");
        System.out.println("│ 7. Exit                             │");
        System.out.println("└─────────────────────────────────────┘");
        System.out.print("Enter your choice: ");
    }

    private int getUserChoice() throws InvalidInputException {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Please enter a valid number");
        }
    }

    private void handleMenuChoice(int choice) throws InvalidInputException {
        switch (choice) {
            case 1 -> handleBasicOperations();
            case 2 -> handleAdvancedOperations();
            case 3 -> handleScientificOperations();
            case 4 -> handleStatisticalOperations();
            case 5 -> displayHistory();
            case 6 -> clearHistory();
            case 7 -> exitApplication();
            default -> throw new InvalidInputException("Invalid choice. Please select 1-7");
        }
    }

    private void handleBasicOperations() throws InvalidInputException {
        System.out.println("\n── Basic Operations ──");
        System.out.println("1. Addition  2. Subtraction  3. Multiplication  4. Division");
        System.out.print("Select operation: ");
        
        int op = getUserChoice();
        double num1 = getNumber("Enter first number: ");
        double num2 = getNumber("Enter second number: ");
        
        double result;
        String operation;
        
        switch (op) {
            case 1 -> {
                result = engine.add(num1, num2);
                operation = num1 + " + " + num2;
            }
            case 2 -> {
                result = engine.subtract(num1, num2);
                operation = num1 + " - " + num2;
            }
            case 3 -> {
                result = engine.multiply(num1, num2);
                operation = num1 + " × " + num2;
            }
            case 4 -> {
                result = engine.divide(num1, num2);
                operation = num1 + " ÷ " + num2;
            }
            default -> throw new InvalidInputException("Invalid operation");
        }
        
        displayResult(operation, result);
        historyManager.addEntry(operation, result);
    }

    private void handleAdvancedOperations() throws InvalidInputException {
        System.out.println("\n── Advanced Operations ──");
        System.out.println("1. Power  2. Square Root  3. Modulo  4. Absolute Value");
        System.out.print("Select operation: ");
        
        int op = getUserChoice();
        double result;
        String operation;
        
        switch (op) {
            case 1 -> {
                double base = getNumber("Enter base: ");
                double exp = getNumber("Enter exponent: ");
                result = engine.power(base, exp);
                operation = base + " ^ " + exp;
            }
            case 2 -> {
                double num = getNumber("Enter number: ");
                result = engine.squareRoot(num);
                operation = "√" + num;
            }
            case 3 -> {
                double num1 = getNumber("Enter dividend: ");
                double num2 = getNumber("Enter divisor: ");
                result = engine.modulo(num1, num2);
                operation = num1 + " mod " + num2;
            }
            case 4 -> {
                double num = getNumber("Enter number: ");
                result = engine.absolute(num);
                operation = "|" + num + "|";
            }
            default -> throw new InvalidInputException("Invalid operation");
        }
        
        displayResult(operation, result);
        historyManager.addEntry(operation, result);
    }

    private void handleScientificOperations() throws InvalidInputException {
        System.out.println("\n── Scientific Operations ──");
        System.out.println("1. Sine  2. Cosine  3. Tangent  4. Logarithm  5. Natural Log  6. Factorial");
        System.out.print("Select operation: ");
        
        int op = getUserChoice();
        double result;
        String operation;
        
        switch (op) {
            case 1 -> {
                double angle = getNumber("Enter angle (degrees): ");
                result = engine.sine(angle);
                operation = "sin(" + angle + "°)";
            }
            case 2 -> {
                double angle = getNumber("Enter angle (degrees): ");
                result = engine.cosine(angle);
                operation = "cos(" + angle + "°)";
            }
            case 3 -> {
                double angle = getNumber("Enter angle (degrees): ");
                result = engine.tangent(angle);
                operation = "tan(" + angle + "°)";
            }
            case 4 -> {
                double num = getNumber("Enter number: ");
                result = engine.logarithm(num);
                operation = "log₁₀(" + num + ")";
            }
            case 5 -> {
                double num = getNumber("Enter number: ");
                result = engine.naturalLog(num);
                operation = "ln(" + num + ")";
            }
            case 6 -> {
                int num = (int) getNumber("Enter non-negative integer: ");
                result = engine.factorial(num);
                operation = num + "!";
            }
            default -> throw new InvalidInputException("Invalid operation");
        }
        
        displayResult(operation, result);
        historyManager.addEntry(operation, result);
    }

    private void handleStatisticalOperations() throws InvalidInputException {
        System.out.println("\n── Statistical Operations ──");
        System.out.print("Enter count of numbers: ");
        int count = getUserChoice();
        
        if (count <= 0) {
            throw new InvalidInputException("Count must be positive");
        }
        
        double[] numbers = new double[count];
        for (int i = 0; i < count; i++) {
            numbers[i] = getNumber("Enter number " + (i + 1) + ": ");
        }
        
        StatisticalCalculator stats = new StatisticalCalculator(numbers);
        
        System.out.println("\n── Statistical Results ──");
        System.out.printf("Mean:     %.4f%n", stats.mean());
        System.out.printf("Median:   %.4f%n", stats.median());
        System.out.printf("Mode:     %.4f%n", stats.mode());
        System.out.printf("Std Dev:  %.4f%n", stats.standardDeviation());
        System.out.printf("Variance: %.4f%n", stats.variance());
        System.out.printf("Sum:      %.4f%n", stats.sum());
        System.out.printf("Min:      %.4f%n", stats.min());
        System.out.printf("Max:      %.4f%n", stats.max());
        
        historyManager.addEntry("Statistical Analysis", stats.mean());
    }

    private double getNumber(String prompt) throws InvalidInputException {
        System.out.print(prompt);
        try {
            return Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid number format");
        }
    }

    private void displayResult(String operation, double result) {
        System.out.println("\n╭─────────────────────────────────╮");
        System.out.printf("│ %s = %.6f%n", operation, result);
        System.out.println("╰─────────────────────────────────╯");
    }

    private void displayHistory() {
        historyManager.displayHistory();
    }

    private void clearHistory() {
        historyManager.clearHistory();
        System.out.println("✓ History cleared successfully!");
    }

    private void exitApplication() {
        System.out.println("\nExiting calculator...");
        running = false;
    }

    public static void main(String[] args) {
        CalculatorApp app = new CalculatorApp();
        app.start();
    }
}