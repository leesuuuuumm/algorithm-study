import sys
from collections import deque
sys.setrecursionlimit(10**5)
si = sys.stdin.readline

n, m = map(int, si().split())
ans = []
data, snakes = [], []
checked = [False] * 101

for _ in range(n):
    x, y = map(int, si().split())
    data.append((x, y))

for _ in range(m):
    x, y = map(int, si().split())
    snakes.append((x, y))


def up(nx):
    for i in range(len(data)):
        if nx == data[i][0]:
            return data[i][1]
    return -1


def down(nx):
    for i in range(len(snakes)):
        if nx == snakes[i][0]:
            return snakes[i][1]
    return -1


def rec_func():
    global data, snakes, ans
    q = deque([])
    q.append([1, 0])
    checked[1] = True

    while q:
        x, cnt = q.popleft()

        if x == 100:
            ans.append(cnt)
            continue

        for i in range(1, 7):
            nx = x + i
            if nx < 1 or nx > 100:
                continue

            if not checked[nx]:
                checked[nx] = True
                tmpX, tmpY = up(nx), down(nx)

                if tmpX > 0 > tmpY:
                    q.append([tmpX, cnt+1])
                elif tmpY > 0 > tmpX:
                    q.append([tmpY, cnt+1])
                else:
                    q.append([nx, cnt+1])


rec_func()
print(*ans)
