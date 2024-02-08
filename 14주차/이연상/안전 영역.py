# [BOJ] 2468_안전 영역

from collections import deque
from copy import deepcopy

n = int(input())

graph = []
for _ in range(n):
    graph.append(list(map(int, input().split())))


dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

def bfs(x, y, h):
    
    q = deque([])
    q.append((x, y))
    while q:

        x, y = q.popleft()

        for i in range(4):
            nx = x + dx[i] 
            ny = y + dy[i]

            if(nx < 0 or ny < 0 or nx >= n or ny >= n):
                continue

            if(graph[nx][ny] <= h):
                continue

            graph[nx][ny] = 0
            q.append((nx, ny))

tmpG = deepcopy(graph)
count = 0
res = []
for h in range(101):
    for i in range(n):
        for j in range(n):
            if(graph[i][j] > h):
                bfs(i, j, h)
                count += 1
    
    res.append(count)
    count = 0    
    graph = deepcopy(tmpG)

print(max(res))