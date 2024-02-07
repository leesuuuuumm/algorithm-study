# [BOJ] 2178_미로 탐색

from collections import deque

n, m = map(int, input().split())

graph = []
for i in range(n):
    graph.append(list(input()))
    
dx = [1, -1, 0 ,0]
dy = [0, 0, 1, -1]

def bfs(x, y):

    q = deque([])
    q.append((x, y))

    while q:
        x, y = q.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if(nx >= n or nx < 0 or ny >= m or ny < 0):
                continue

            if(graph[nx][ny] == '1'):
                graph[nx][ny] = int(graph[x][y]) + int(graph[nx][ny])
                q.append((nx,  ny))

bfs(0, 0)
print(graph[n-1][m-1])