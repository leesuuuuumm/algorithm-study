import sys
si = sys.stdin.readline
n, m = map(int, si().split())
tmp = list(map(int, si().split()))

parent = [i for i in range(n+1)]
known_list, arr = [], []

for _ in range(m):
    arr.append(list(map(int, si().split()))[1:])

if tmp[-1] != 0:
    for i in tmp[1:]:
        known_list.append(i)
else:
    print(m)
    exit(0)


def find(x):
    if parent[x] != x:
        parent[x] = find(parent[x])
    return parent[x]


def union(x, y):
    x = find(x)
    y = find(y)
    if x == y:
        return

    if x > y:
        parent[x] = y
    else:
        parent[y] = x


if len(known_list) >= 2:
    for i in range(1, len(known_list)):
        union(known_list[i-1], known_list[i])

for x in arr:
    for i in range(len(x)):
        union(x[i-1], x[i])

res = 0
cur = find(known_list[0])
for x in arr:
    flag = False
    for i in range(len(x)):
        if cur != find(x[i]):
            flag = True
            break

    if flag:
        res += 1

print(res)
