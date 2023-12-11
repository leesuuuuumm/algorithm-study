import sys
si = sys.stdin.readline

N, L = map(int, si().split())

for n in range(L, 101):
    x = N/n - (n+1)/2
    if int(x) == x:
        x = int(x)
        if x + 1 >= 0:
            for i in range(x+1, x+n+1):
                print(i, end=' ')
            break
else:
    print(-1)
