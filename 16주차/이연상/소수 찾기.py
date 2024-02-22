# [PRG] 소수 찾기

def solution(n):
    a = [False, False] + [True]*(n-1) # 0, 1 False(소수가 아니므로) / 2부터 소수라고 가정
    primes = [] # 소수
    
    for i in range(2, n+1):
        if a[i]: # 소수이면
            primes.append(i) 
            for j in range(2*i, n+1, i): # 2*i부터 n까지 i의 배수 False로 바꾸기
                a[j] = False
    return len(primes)