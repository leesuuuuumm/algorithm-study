class Solution {
    /*
    완전탐색은 효율성 문제, 그리디는 반례가 있으니 DP로 접근
    */
    int solution(int[][] land) {
        int[][] dp = new int[land.length][4];
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < 4; j++) {
                dp[i][j] = land[i][j];
            }
        }
        for (int i = 1; i < land.length; i++) {
            dp[i][0] += Math.max(Math.max(dp[i - 1][1], dp[i - 1][2]), dp[i - 1][3]);
            dp[i][1] += Math.max(Math.max(dp[i - 1][0], dp[i - 1][2]), dp[i - 1][3]);
            dp[i][2] += Math.max(Math.max(dp[i - 1][0], dp[i - 1][1]), dp[i - 1][3]);
            dp[i][3] += Math.max(Math.max(dp[i - 1][0], dp[i - 1][1]), dp[i - 1][2]);
        }

        int max = 0;
        for (int i = 0; i < 4; i++) {
            max = Math.max(max, dp[land.length - 1][i]);
        }

        return max;
    }
}