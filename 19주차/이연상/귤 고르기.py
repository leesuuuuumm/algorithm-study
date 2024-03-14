# [PRG] 귤 고르기

def solution(k, tangerine):
    answer = 0
    dic = {}
    for i in tangerine:
        if i in dic:
            dic[i] +=1
        else:
            dic[i] = 1
    
    temp = sorted(dic.items(), key = lambda x : x[1], reverse=True)
    
    for i in temp:
        if k <= 0:
            return answer
        k -= i[1]
        answer += 1
        
    
    return answer