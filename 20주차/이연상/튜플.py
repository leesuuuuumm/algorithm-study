# [PRG] 튜플

def solution(s):
    
    s = s[1:len(s) - 1]
    s = list(s.split("},"))
    s[0] = s[0][1:]
    if(len(s) != 1):
        s[len(s) - 1] = s[len(s) - 1][1:len(s[len(s) - 1]) - 1]
    else:
        s[0] = s[0][0:len(s[0]) - 1]
        
    for i in range(1, len(s) - 1):
        s[i] = s[i][1:]
    
    for i in range(len(s)):
        if(len(s[i]) > 1):
            s[i] = list(map(int, s[i].split(",")))
        else:
            s[i] = [int(s[i])]
    
    s.sort(key=lambda x:len(x))
    result = []
    for i in s:
        for j in i:
            if(j in result):
                continue
            else:
                result.append(j)
    
    
    return result