import sys
si = sys.stdin.readline


t = int(si())
for _ in range(t):
    p = si().rstrip()
    n = int(si())
    arr = si().rstrip()
    direction = 0  # 0 왼쪽, 1 오른쪽
    flag = False

    if n == 0:
        if 'D' not in p:
            print('[]')
        else:
            print('error')
        continue

    arr = list(map(int, arr[1:len(arr)-1].split(',')))

    for command in range(len(p)):
        if p[command] == 'R':
            if direction == 1:
                direction = 0
            else:
                direction = 1

        else:
            if arr:
                if direction == 1:
                    arr.pop()
                else:
                    arr.pop(0)
            else:
                flag = True
                break

    if direction == 1:
        arr.reverse()

    if not flag:
        print('[', end='')
        print(*arr, sep=',', end='')
        print(']')
    else:
        print('error')
