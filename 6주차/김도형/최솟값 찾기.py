import sys
from collections import deque
si = sys.stdin.readline

n, l = map(int, si().split())
arr = list(map(int, si().split()))
q = deque()
res = []

for i in range(len(arr)):
    while q and q[-1][0] >= arr[i]:
        q.pop()

    while q and q[0][1] < i - l + 1:
        q.popleft()

    q.append((arr[i], i))
    print(q[0][0], end=' ')
