import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Statistical calculator for advanced data analysis
 * Provides mean, median, mode, standard deviation, and more
 */
public class StatisticalCalculator {
    
    private final double[] data;
    private final int size;
    
    public StatisticalCalculator(double[] data) {
        if (data == null || data.length == 0) {
            throw new InvalidInputException("Data array cannot be null or empty");
        }
        
        // Validate all numbers
        for (int i = 0; i < data.length; i++) {
            if (Double.isNaN(data[i]) || Double.isInfinite(data[i])) {
                throw new InvalidInputException(
                    "Data contains invalid number at index " + i);
            }
        }
        
        this.data = Arrays.copyOf(data, data.length);
        this.size = data.length;
    }
    
    /**
     * Calculate arithmetic mean (average)
     */
    public double mean() {
        double sum = 0;
        for (double value : data) {
            sum += value;
        }
        return sum / size;
    }
    
    /**
     * Calculate median (middle value)
     */
    public double median() {
        double[] sorted = Arrays.copyOf(data, size);
        Arrays.sort(sorted);
        
        if (size % 2 == 0) {
            return (sorted[size / 2 - 1] + sorted[size / 2]) / 2.0;
        } else {
            return sorted[size / 2];
        }
    }
    
    /**
     * Calculate mode (most frequent value)
     * Returns the smallest mode if multiple modes exist
     */
    public double mode() {
        Map<Double, Integer> frequencyMap = new HashMap<>();
        
        for (double value : data) {
            frequencyMap.put(value, frequencyMap.getOrDefault(value, 0) + 1);
        }
        
        int maxFrequency = 0;
        double mode = data[0];
        
        for (Map.Entry<Double, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxFrequency || 
                (entry.getValue() == maxFrequency && entry.getKey() < mode)) {
                maxFrequency = entry.getValue();
                mode = entry.getKey();
            }
        }
        
        return mode;
    }
    
    /**
     * Calculate variance
     */
    public double variance() {
        double mean = mean();
        double sumSquaredDiff = 0;
        
        for (double value : data) {
            double diff = value - mean;
            sumSquaredDiff += diff * diff;
        }
        
        return sumSquaredDiff / size;
    }
    
    /**
     * Calculate standard deviation
     */
    public double standardDeviation() {
        return Math.sqrt(variance());
    }
    
    /**
     * Calculate sum of all values
     */
    public double sum() {
        double sum = 0;
        for (double value : data) {
            sum += value;
        }
        return sum;
    }
    
    /**
     * Find minimum value
     */
    public double min() {
        double min = data[0];
        for (int i = 1; i < size; i++) {
            if (data[i] < min) {
                min = data[i];
            }
        }
        return min;
    }
    
    /**
     * Find maximum value
     */
    public double max() {
        double max = data[0];
        for (int i = 1; i < size; i++) {
            if (data[i] > max) {
                max = data[i];
            }
        }
        return max;
    }
    
    /**
     * Calculate range (max - min)
     */
    public double range() {
        return max() - min();
    }
    
    /**
     * Get data size
     */
    public int getSize() {
        return size;
    }
}