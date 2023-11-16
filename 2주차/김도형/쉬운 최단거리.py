from collections import deque
import sys
si = sys.stdin.readline
dir = ((0, 1), (0, -1), (1, 0), (-1, 0))

n, m = map(int, si().split())
checked = [[False] * m for _ in range(n)]
new_board = [[0] * m for _ in range(n)]
board = [list(map(int, si().split())) for _ in range(n)]

a, b = 0, 0
for i in range(n):
    for j in range(m):
        if board[i][j] == 0:
            checked[i][j] = True

        elif board[i][j] == 2:
            a, b = i, j


def is_range(nx, ny):
    return 0 <= nx < n and 0 <= ny < m


def bfs():
    q = deque([])
    q.append((a, b))
    checked[a][b] = True
    while q:
        x, y = q.popleft()
        for dx, dy in dir:
            nx = x + dx
            ny = y + dy
            if is_range(nx, ny) and board[nx][ny] == 1 and not checked[nx][ny]:
                checked[nx][ny] = True
                new_board[nx][ny] += new_board[x][y] + 1
                q.append((nx, ny))


bfs()

for i in range(n):
    for j in range(m):
        if board[i][j] == 1 and not checked[i][j]:
            new_board[i][j] = -1

for i in range(n):
    for j in range(m):
        print(new_board[i][j], end=' ')
    print()
