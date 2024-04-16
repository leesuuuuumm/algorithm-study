# [BOJ] 감소하는 수

from collections import deque
d = deque()
count = -1
n = int(input())
for i in range(10):
    d.append(i)
while d:
    now_num = d.popleft()
    count += 1

    if count == n:
        print(now_num)
        exit()

    for i in range(now_num%10):
        d.append(now_num*10 + i)

print(-1)