import sys
si = sys.stdin.readline

n = int(si())
arr = list(map(int, si().split()))
m = int(si())
lo, hi = -1, max(arr) + 1
while lo + 1 < hi:
    mid = (lo + hi) // 2
    res = 0

    for i in range(len(arr)):
        if arr[i] <= mid:
            res += arr[i]
        else:
            res += mid

    if not (res > m):
        lo = mid
    else:
        hi = mid


print(lo)
