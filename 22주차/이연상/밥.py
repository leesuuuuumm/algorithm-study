# [BOJ] 밥

n, x = map(int, input().split())
bob = sorted([list(map(int, input().split())) for _ in range(n)], key=lambda b: (b[0] - b[1]), reverse=True)
x -= 1000 * n 
answer = sum([i[1] for i in bob]) 
for b in bob:
    if x >= 4000 and b[0] > b[1]:
        answer += b[0] - b[1]
        x -= 4000
    else:
        break
print(answer)