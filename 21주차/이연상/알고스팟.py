# [BOJ] 알고스팟

import heapq

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

m, n = map(int, input().split())

dist = [[int(1e9)] * m for i in range(n)]

arr = []

for i in range(n):
    arr.append(list(map(int, input().strip())))


dist[0][0] = 0
q = []
heapq.heappush(q, (0, (0, 0)))

while(q):
    now_dist, xy = heapq.heappop(q)
    x, y = xy

    if dist[x][y] != now_dist:
        continue

    for d in range(4):
        nx = dx[d] + x
        ny = dy[d] + y
        if nx < 0 or nx >= n or ny < 0 or ny >= m:
            continue

        # 다음 위치가 벽인 경우
        if arr[nx][ny] == 1:
            if dist[nx][ny] > dist[x][y] + 1:
                dist[nx][ny] = dist[x][y] + 1
                heapq.heappush(q, (dist[nx][ny], (nx, ny)))

        # 다음 위치가 빈방인 경우
        if arr[nx][ny] == 0:
            if dist[nx][ny] > dist[x][y]:
                dist[nx][ny] = dist[x][y]
                heapq.heappush(q, (dist[nx][ny], (nx, ny)))

print(dist[n - 1][m - 1])

