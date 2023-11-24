import sys
si = sys.stdin.readline

n, m, x, y, k = map(int, si().split())
board = [list(map(int, si().split())) for _ in range(n)]
dice = [[0, 0, 0],
        [0, 0, 0],
        [0, 0, 0],
        [0, 0, 0]]
commands = list(map(int, si().split()))
ans = []


def move(direction):
    global dice
    if direction == 1:  # 우
        temp = dice[1][2]
        for i in range(2, 0, -1):
            dice[1][i] = dice[1][i-1]
        dice[1][0] = dice[3][1]
        dice[3][1] = temp

    elif direction == 2:  # 좌
        temp = dice[1][0]
        for i in range(2):
            dice[1][i] = dice[1][i+1]
        dice[1][2] = dice[3][1]
        dice[3][1] = temp

    elif direction == 3:  # 하
        temp = dice[3][1]
        for i in range(3, 0, -1):
            dice[i][1] = dice[i-1][1]
        dice[0][1] = temp

    elif direction == 4:  # 상
        temp = dice[0][1]
        for i in range(3):
            dice[i][1] = dice[i+1][1]
        dice[3][1] = temp


def is_range(nx, ny):
    global x, y
    return 0 <= nx < n and 0 <= ny < m


def simulate(direction):
    global x, y, board
    dir = ((0, 0), (0, 1), (0, -1), (-1, 0), (1, 0))
    nx, ny = x + dir[direction][0], y + dir[direction][1]

    if is_range(nx, ny):
        move(direction)
        x, y = nx, ny
    else:
        return

    if board[nx][ny] == 0:
        board[nx][ny] = dice[3][1]
    else:
        dice[3][1] = board[nx][ny]
        board[nx][ny] = 0

    ans.append(dice[1][1])


for command in commands:
    simulate(command)

for i in ans:
    print(i)
