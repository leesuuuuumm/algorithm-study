def solution(num):
    if sum(num) == 0: return '0' 

    num = list(map(str, num))
    num.sort(key = lambda x : x*3, reverse = True)
    
    return str(''.join(num))