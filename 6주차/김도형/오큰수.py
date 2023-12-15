import sys
si = sys.stdin.readline

n = int(si())
arr = list(map(int, si().split()))

stk = []
res = [-1] * n
for i in range(len(arr)):
    while stk and stk[-1][0] < arr[i]:
        _, idx = stk.pop()
        res[idx] = arr[i]

    stk.append((arr[i], i))

print(*res)
