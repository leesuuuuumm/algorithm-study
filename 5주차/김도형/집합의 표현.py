import sys
sys.setrecursionlimit(10**5)
si = sys.stdin.readline

n, m = map(int, si().split())
arr = [[i] for i in range(n+1)]
parent = [i for i in range(n+1)]


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


for _ in range(m):
    command, a, b = map(int, si().split())
    if command == 0:
        union(a, b)

    else:
        if find(a) == find(b):
            print('yes')
        else:
            print('no')
