import sys
si = sys.stdin.readline

n = int(si())
arr = []
ans = 0

for i in range(n):
    _, y = map(int, si().split())
    arr.append(y)
arr.append(0)

stk = [0]
for i in range(len(arr)):
    height = arr[i]
    while stk[-1] > arr[i]:
        if stk[-1] != height:
            ans += 1
            height = stk[-1]
        stk.pop()
    stk.append(arr[i])

print(ans)
