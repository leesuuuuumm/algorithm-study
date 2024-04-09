# [BOJ] 연속합2

N = int(input())
a = list(map(int, input().split()))
dp = [[0] * N for _ in range(2)]
dp[0][0] = a[0]
dp[1][0] = -1000
for i in range(1, N):
    dp[0][i] = max(dp[0][i - 1] + a[i], a[i])
    dp[1][i] = max(dp[0][i - 1], dp[1][i - 1] + a[i])
print(max(max(dp[0]), max(dp[1])))
