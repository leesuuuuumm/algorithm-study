import sys
import heapq
from collections import deque
si = sys.stdin.readline
sys.setrecursionlimit(10**8)

n = int(si())
graph = [[] for _ in range(n+1)]


def dfs(x, wei):
    for i in graph[x]:
        a, b = i
        if distance[a] == -1:
            distance[a] = b + wei
            dfs(a, b + wei)


for _ in range(n-1):
    a, b, c = map(int, si().split())
    graph[a].append([b, c])
    graph[b].append([a, c])

distance = [-1] * (n+1)
distance[1] = 0
dfs(1, 0)

n1 = distance.index(max(distance))
distance = [-1] * (n+1)
distance[n1] = 0
dfs(n1, 0)

print(max(distance))
