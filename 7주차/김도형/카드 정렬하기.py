import sys
import heapq
si = sys.stdin.readline

n = int(si())
hq = []
for _ in range(n):
    heapq.heappush(hq, int(si()))

ans = 0
while len(hq) >= 2:
    x = heapq.heappop(hq)
    y = heapq.heappop(hq)
    res = x + y
    ans += res
    heapq.heappush(hq, res)

print(ans)
