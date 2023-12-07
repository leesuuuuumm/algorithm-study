import sys
si = sys.stdin.readline

n = int(si())
m = int(si())

graph = [list(map(int, si().split())) for _ in range(n)]
plan = list(map(int, si().split()))
parent = [i for i in range(n)]


def find(x):
    if parent[x] != x:
        parent[x] = find(parent[x])
    return parent[x]


def union(x, y):
    x = find(x)
    y = find(y)

    if x == y:
        return

    if x < y:
        parent[y] = x
    else:
        parent[x] = y


for i in range(n):
    for j in range(i+1, n):
        if graph[i][j] == 1:
            union(i, j)

flag = False
cur = find(plan[0]-1)
for i in range(1, len(plan)):
    if cur != find(plan[i]-1):
        flag = True
        break

if not flag:
    print('YES')
else:
    print('NO')
