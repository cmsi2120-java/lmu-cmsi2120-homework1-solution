package financeutils;

public class FinanceUtils {
    
    public static int[] getEvenRedistribution (int[] amounts) {
        int sum = 0;
        for (int counts : amounts) {
            if (counts < 0) {
                throw new IllegalArgumentException("all amounts must be >= 0");
            }
            sum += counts;
        }
        int avg = sum / amounts.length;
        
        int[] result = new int[amounts.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = avg;
        }
        
        int residual = sum - (result.length * avg);
        for (int i = result.length - 1; i > 0 && residual > 0; i--) {
            result[i]++;
            residual--;
        }
        
        return result;
    }
    
    private static final int[] DENOMINATIONS = {1, 5, 10, 25};
    
    public static int[] greedyChangemaker (int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount must be a positive integer");
        }
        int[] result = new int[DENOMINATIONS.length];
        for (int i = DENOMINATIONS.length-1; i >= 0; i--) {
            result[i] = amount / DENOMINATIONS[i];
            amount %= DENOMINATIONS[i];
        }
        return result;
    }
    
}
