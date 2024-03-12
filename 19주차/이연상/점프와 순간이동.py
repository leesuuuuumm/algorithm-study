# [PRG] 점프와 순간이동

def solution(N):
    answer = 0
    while N > 0:
        answer += N % 2
        N //= 2
    return answer