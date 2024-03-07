# [PRG] 올바른 괄호

def solution(s):
    answer = True
    
    res = 0
    for i in s:
        if(i == "("):
            res += 1
        else:
            res -= 1
        
        if res < 0:
            return False
    
    if res > 0:
        return False

    return True