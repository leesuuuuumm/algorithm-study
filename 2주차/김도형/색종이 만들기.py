import sys
si = sys.stdin.readline

n = int(si())
board = [list(map(int, si().split())) for _ in range(n)]

white, blue = 0, 0


def is_same(cur, r, c, n):
    for i in range(r, r + n):
        for j in range(c, c + n):
            if board[i][j] != cur:
                return False
    return True


def rec_func(x, r, c):
    global white, blue
    if x == 1 or is_same(board[r][c], r, c, x):
        if board[r][c] == 1:
            blue += 1
        else:
            white += 1
        return

    mid = x // 2
    rec_func(mid, r, c)
    rec_func(mid, r, c+mid)
    rec_func(mid, r+mid, c)
    rec_func(mid, r+mid, c+mid)


rec_func(n, 0, 0)
print(white, blue, sep='\n')
