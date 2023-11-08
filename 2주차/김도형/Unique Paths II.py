# LeetCode

class Solution:
    def uniquePathsWithObstacles(self, obstacleGrid: List[List[int]]) -> int:
        n = len(obstacleGrid)
        m = len(obstacleGrid[0])

        if obstacleGrid[0][0] == 1 or obstacleGrid[n-1][m-1] == 1:
            return 0
        if n == 1 and m == 1 and obstacleGrid[0][0] == 0:
            return 1

        dp = [[0] * m for _ in range(n)]
        for i in range(n):
            for j in range(m):
                if obstacleGrid[i][j] == 1:
                    dp[i][j] = -1

        for i in range(n):
            for j in range(m):
                if i == 0 and j == 0:
                    dp[i][j] = 1

                if dp[i][j] == 0:
                    if i == 0 and j > 0:
                        dp[i][j] = dp[i][j-1]
                    elif i > 0 and j == 0:
                        dp[i][j] = dp[i-1][j]
                    else:
                        if dp[i][j-1] == -1 and dp[i-1][j] == -1:
                            dp[i][j] = -1
                        else:
                            dp[i][j] = max(dp[i][j-1], 0) + max(dp[i-1][j], 0)

        return 0 if dp[n-1][m-1] == - 1 else dp[n-1][m-1]
