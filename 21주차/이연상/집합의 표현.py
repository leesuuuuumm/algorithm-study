# [BOJ] 집합의 표현

import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)

def find(a):
  if a == parent[a]:
    return a

  parent[a] = find(parent[a])
  return parent[a]

def union(a, b):
  x, y = find(a), find(b)

  if x > y:
    parent[x] = y
  elif x < y:
    parent[x] = y

n, m = map(int,input().split())
parent = [i for i in range(n+1)]

for i in range(m):
  op, a, b = map(int,input().split())
  if op == 1:
    if find(a) == find(b):
      print('yes')
    else:
      print('no')
  else:
    union(a,b)