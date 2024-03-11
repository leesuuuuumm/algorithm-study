# [PRG] 카펫

def solution(brown, yellow):
    answer = []
    
    val = brown + yellow
    for i in range(1, val):
        if (val % i == 0):
            if(val / i) <= i:
                if 2*i + 2*(val / i) == brown + 4:
                    answer = [i, val / i]
                    break
    
    return answer