# [BOJ] 1629_곱셈

import sys
sys.setrecursionlimit(10**7)

a, b, c = map(int, input().split())

def go(a, b):
    if b == 1:
      return a % c
    else:
      tmp = go(a, b // 2)
      if b % 2 ==0:
          return (tmp * tmp) % c
      else:
          return (tmp  * tmp * a) % c

print(go(a, b))
