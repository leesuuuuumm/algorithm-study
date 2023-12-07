import sys
si = sys.stdin.readline

n = int(si())
tower_list = list(map(int, si().split()))
answer = []
mono_stk = []

for i in range(n):
    h = tower_list[i]
    if mono_stk:
        # print(mono_stk)
        while mono_stk:
            if mono_stk[-1][0] < h:
                mono_stk.pop()
                if not mono_stk:
                    print(0, end=' ')

            elif mono_stk[-1][0] > h:
                print(mono_stk[-1][1]+1, end=' ')
                break

            else:
                print(mono_stk[-1][1]+1, end=' ')
                mono_stk.pop()
                break
        mono_stk.append([h, i])
    else:
        print(0, end=' ')
        mono_stk.append([h, i])
