class Solution {
    private static final int MOD = 1_234_567;
    
    static long[] dp;
    
    public long solution(int n) {
        if (n == 1)
            return 1;
        
        init(n);
        for (int i = 2; i < n; i++) {
            dp[i] = (dp[i - 2] + dp[i - 1]) % MOD;
        }
        return dp[n - 1];
    }
    
    private void init(int n) {
        dp = new long[n];
        dp[0] = 1;
        dp[1] = 2;
    }
}