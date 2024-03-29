# [BOJ] 파이프 옮기기

import sys
input = sys.stdin.readline

HOR, CRO, VER = 0, 1, 2
n = int(input())
board = [list(map(int, input().split())) for _ in range(n)]
dp = [[[0, 0, 0] for _ in range(n)] for _ in range(n)]
dp[0][1][HOR] = 1

for i in range(n):
    for j in range(n):
        if i == 0 and j == 0:
            continue
        if i+1 < n and board[i+1][j] == 0:
            dp[i+1][j][VER] += dp[i][j][VER]
            dp[i+1][j][VER] += dp[i][j][CRO]
        if j+1 < n and board[i][j+1] == 0:
            dp[i][j+1][HOR] += dp[i][j][HOR]
            dp[i][j+1][HOR] += dp[i][j][CRO]

        if i+1 < n and j+1 < n and board[i+1][j] == 0 and board[i][j+1] == 0 and board[i+1][j+1] == 0:
            dp[i+1][j+1][CRO] += dp[i][j][HOR]
            dp[i+1][j+1][CRO] += dp[i][j][CRO]
            dp[i+1][j+1][CRO] += dp[i][j][VER]

print(sum(dp[n-1][n-1]))