import sys
si = sys.stdin.readline

n, r, c = map(int, si().split())


count = 0


def solve(n, r, c):
    global count

    if n == 0:
        return
    boardSize = 1 << n
    mid = boardSize // 2

    if r < mid and c < mid:
        solve(n-1, r, c)

    elif r < mid and c >= mid:
        count += mid * mid
        solve(n-1, r, c-mid)

    elif r >= mid and c < mid:
        count += mid * mid * 2
        solve(n-1, r-mid, c)

    else:
        count += mid * mid * 3
        solve(n-1, r-mid, c-mid)


solve(n, r, c)
print(count)
