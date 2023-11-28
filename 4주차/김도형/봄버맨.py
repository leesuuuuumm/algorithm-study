import sys
import copy
si = sys.stdin.readline

r, c, n = map(int, si().split())
board_A = [list(si().rstrip()) for _ in range(r)]
board_B = [['.'] * c for _ in range(r)]
board_C = [['O'] * c for _ in range(r)]
board_D = [['.'] * c for _ in range(r)]


def is_range(nx, ny):
    return 0 <= nx < r and 0 <= ny < c


def spread(tmp):
    board = copy.deepcopy(tmp)

    dir = ((0, 1), (0, -1), (1, 0), (-1, 0))
    visited = [[False] * c for _ in range(r)]
    for i in range(len(board)):
        for j in range(len(board[i])):
            if visited[i][j]:
                continue
            if board[i][j] == 'O':
                visited[i][j] = True
                for dx, dy in dir:
                    nx, ny = i + dx, j + dy
                    if not is_range(nx, ny):
                        continue
                    if board[nx][ny] == '.' and not visited[nx][ny]:
                        visited[nx][ny] = True
                        board[nx][ny] = 'O'

    return board


tmp_board = spread(board_A)
for i in range(r):
    for j in range(c):
        if tmp_board[i][j] == '.':
            board_B[i][j] = 'O'


tmp_board = spread(board_B)


for i in range(r):
    for j in range(c):
        if tmp_board[i][j] == '.':
            board_D[i][j] = 'O'


# N > 1이고 N % 4 == 1일때
if n > 1 and n % 4 == 1:
    for i in board_D:
        print(''.join(i))

# N % 2 == 0 일 때
elif n % 2 == 0:

    for i in board_C:
        print(''.join(i))

# N % 4 == 3 일 때
elif n % 4 == 3:
    for i in board_B:
        print(''.join(i))

# N == 1 일 때
elif n == 1:
    for i in board_A:
        print(''.join(i))
