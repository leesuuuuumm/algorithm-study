# LeetCode

class Solution:
    def minPathSum(self, grid: List[List[int]]) -> int:
        r,c =0,0
        for i in range(len(grid)) :
            r = i
            for j in range(len(grid[i])) :
                c = max(c, j)
        
        r ,c = r+1, c+1
        dp = [[0] * (c) for _ in range(r)]
        
        if r == 0 and c == 0 :
            return grid[0][0]

        dp[0][0] = grid[0][0]

        for i in range(r) :
            for j in range(c) :
                if i == 0 and j == 0 : continue 

                if i == 0 and j > 0:
                    dp[i][j] = dp[i][j-1] + grid[i][j]
                elif i > 0 and j == 0 :
                    dp[i][j] = dp[i-1][j] + grid[i][j]
                else :
                    dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]

        return dp[r-1][c-1]
        
