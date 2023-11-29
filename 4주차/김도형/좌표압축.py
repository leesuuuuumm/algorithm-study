import sys
si = sys.stdin.readline

n = int(si())
arr = list(map(int, si().split()))
dic = dict()

idx = 0
for i in sorted(arr):
    if i not in dic:
        dic[i] = idx
        idx += 1
    else:
        continue

for i in range(len(arr)):
    print(dic[arr[i]], end=' ')
