# [PRG] 멀리 뛰기

def solution(n):
    d = [0] * (n+1)
    d[0] = 1
    d[1] = 2
    
    for i in range(2, n):
        d[i] = d[i - 1] + d[i - 2]
        
    return d[n - 1] % 1234567
