import sys
si = sys.stdin.readline

n = int(si())
x = int(si())
arr = list(map(int, si().split()))
q = []
score = []
cnt = 0
for i in range(x):
    if arr[i] in q:
        for j in range(len(q)):
            if arr[i] == q[j]:
                score[j] += 1
    else:
        if len(q) >= n:
            for j in range(n):
                if score[j] == min(score):
                    del q[j]
                    del score[j]
                    break

        q.append(arr[i])
        score.append(1)

q.sort()
print(' '.join(map(str, q)))
