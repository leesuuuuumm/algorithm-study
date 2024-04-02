# [BOJ] 퇴사2

N = int(input())
arr = [tuple(map(int, input().split())) for _ in range(N)]
dp = [0] * (N+1)
for i in range(N-1, -1, -1):
    t, p = arr[i]
    if i+t > N:
        dp[i] = dp[i+1]
        continue
    dp[i] = max(dp[i+t] + p, dp[i+1])
print(max(dp))