# [PRG] 의상

def solution(clothes):
    dic = {}
    for i in clothes:
        if i[1] in dic:
            dic[i[1]] += [i[0]]
        else:
            dic[i[1]] = [i[0]]


    answer = 1
    for _, value in dic.items():
        answer *= (len(value) + 1)
        
    return answer - 1