// [PRG] 등굣길

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n][m];
        
        // 물웅덩이 위치는 -1로 표시
        for (int[] puddle : puddles) {
            dp[puddle[1] - 1][puddle[0] - 1] = -1;
        }
        
        // 시작점 초기화
        dp[0][0] = 1;
        
        // DP로 경로 수 계산
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dp[i][j] == -1) {
                    dp[i][j] = 0;  // 물웅덩이는 경로가 없음
                    continue;
                }
                // 위에서 오는 경우
                if (i > 0) dp[i][j] += dp[i - 1][j];
                // 왼쪽에서 오는 경우
                if (j > 0) dp[i][j] += dp[i][j - 1];
                // 모듈러 연산
                dp[i][j] %= 1000000007;
            }
        }
        
        // 도착 지점까지의 경로 수 리턴
        return dp[n - 1][m - 1];
    }
}