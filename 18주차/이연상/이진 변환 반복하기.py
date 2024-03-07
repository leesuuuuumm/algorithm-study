# [PRG] 이진 변환 반복하기

def solution(s):
    answer = [0 , 0]
    
    while(s != "1"):
        answer[1] += s.count('0')
        s = s.replace('0', "")
        s = bin(len(s))
        s = str(s[2:])
        answer[0] += 1
    
    return answer