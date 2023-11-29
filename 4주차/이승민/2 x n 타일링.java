class Solution {
    public int solution(int n) {
        // dp[i]: 2xi 직사각형을 가득 채우는 경우의 수
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }

        return dp[n];
    }
}