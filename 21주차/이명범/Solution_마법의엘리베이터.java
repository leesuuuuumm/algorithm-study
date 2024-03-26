import java.util.*;

class Solution {
    
    private static final int LIMIT = 100_000_000;
    
    public int solution(int storey) {
        return solve(storey, 0, (int) Math.log10(storey) + 1);
    }
    
    private int solve(int storey, int depth, int limit) {
        if (depth == limit) {
            if (storey == 0)
                return 0;
            
            return 1;
        }
        
        int unit = storey % pow(depth + 1);
        
        int n1 = unit / pow(depth) + solve(storey - unit, depth + 1, limit);
        int n2 = (pow(depth + 1) - unit) / pow(depth) + solve(storey + pow(depth + 1) - unit, depth + 1, limit);
        
        
        
        return Math.min(n1, n2);
    }
    
    private int pow(int exp) {
        return (int) Math.pow(10, exp);
    }
}