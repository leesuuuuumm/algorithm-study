import sys
si = sys.stdin.readline

n = int(si())
arr = [int(si()) for _ in range(n)] + [sys.maxsize]
res = [0] * n
stk = []
for i in range(len(arr)):
    while stk and stk[-1][1] <= arr[i]:
        idx, val = stk.pop()
        res[idx] = i - idx - 1

    stk.append((i, arr[i]))

print(sum(res))
