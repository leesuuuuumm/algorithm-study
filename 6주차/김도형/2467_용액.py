import sys
si = sys.stdin.readline

n = int(si())
arr = list(map(int, si().split()))

lo, hi = 0, len(arr)-1
res = []
while lo < hi:
    mid = arr[lo] + arr[hi]
    res.append((mid, arr[lo], arr[hi]))

    if mid >= 0:
        hi -= 1
    else:
        lo += 1

target = 0
mn = float('inf')
a, b = 0, 0
for i in range(len(res)):
    if mn > abs(target-res[i][0]):
        mn = abs(target-res[i][0])
        a, b = res[i][1], res[i][2]

print(a, b)
