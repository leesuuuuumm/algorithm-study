# [PRG] 영어 끝말잇기

import math

def solution(n, words):
    temp = []
    for i in range(len(words)):
        if not temp:
            temp.append(words[i])
            continue
        if (words[i] not in temp):
            s = temp[-1]
            now = words[i]
            if(s[-1] == now[0]):
                temp.append(words[i])
                continue
        
        
        if (i + 1) % n == 0: b = n
        else : b = (i + 1) % n
        a = math.ceil((i + 1) / n)
        return [b, a]
        

    return [0, 0]