# [BOJ] 1학년

import sys
input=sys.stdin.readline

n=int(input())
dp=[[0 for _ in range(21)] for _ in range(n)]
arr=list(map(int, input().split()))


dp[0][arr[0]]=1

for i in range(1, n-1):
    for j in range(21):
        if dp[i-1][j]:

            if 0<=j+arr[i]<=20:
                dp[i][j+arr[i]] += dp[i-1][j]
                
            if 0<=j-arr[i]<=20:
                dp[i][j-arr[i]] += dp[i-1][j]
                
print(dp[n-2][arr[-1]])