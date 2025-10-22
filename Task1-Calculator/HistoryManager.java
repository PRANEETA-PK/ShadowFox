import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages calculation history with timestamps
 * Demonstrates encapsulation and data management
 */
public class HistoryManager {
    
    private final List<HistoryEntry> history;
    private final DateTimeFormatter formatter;
    private static final int MAX_HISTORY_SIZE = 100;
    
    public HistoryManager() {
        this.history = new ArrayList<>();
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }
    
    /**
     * Add a new calculation entry to history
     */
    public void addEntry(String operation, double result) {
        if (operation == null || operation.trim().isEmpty()) {
            throw new InvalidInputException("Operation cannot be null or empty");
        }
        
        HistoryEntry entry = new HistoryEntry(operation, result);
        history.add(entry);
        
        // Maintain max history size
        if (history.size() > MAX_HISTORY_SIZE) {
            history.remove(0);
        }
    }
    
    /**
     * Display all calculation history
     */
    public void displayHistory() {
        if (history.isEmpty()) {
            System.out.println("\n── Calculation History ──");
            System.out.println("No calculations in history yet.");
            return;
        }
        
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                    CALCULATION HISTORY                         ║");
        System.out.println("╠════════════════════════════════════════════════════════════════╣");
        
        for (int i = history.size() - 1; i >= Math.max(0, history.size() - 20); i--) {
            HistoryEntry entry = history.get(i);
            System.out.printf("║ [%d] %s%n", i + 1, entry.toString());
        }
        
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        
        if (history.size() > 20) {
            System.out.printf("(Showing last 20 of %d entries)%n", history.size());
        } else {
            System.out.printf("(Total entries: %d)%n", history.size());
        }
    }
    
    /**
     * Clear all history
     */
    public void clearHistory() {
        history.clear();
    }
    
    /**
     * Get history size
     */
    public int size() {
        return history.size();
    }
    
    /**
     * Get specific history entry
     */
    public HistoryEntry getEntry(int index) {
        if (index < 0 || index >= history.size()) {
            throw new InvalidInputException("Invalid history index: " + index);
        }
        return history.get(index);
    }
    
    /**
     * Inner class representing a single history entry
     */
    private class HistoryEntry {
        private final String operation;
        private final double result;
        private final LocalDateTime timestamp;
        
        public HistoryEntry(String operation, double result) {
            this.operation = operation;
            this.result = result;
            this.timestamp = LocalDateTime.now();
        }
        
        public String getOperation() {
            return operation;
        }
        
        public double getResult() {
            return result;
        }
        
        public LocalDateTime getTimestamp() {
            return timestamp;
        }
        
        @Override
        public String toString() {
            return String.format("%-30s = %-15.6f [%s]", 
                operation, result, timestamp.format(formatter));
        }
    }
}