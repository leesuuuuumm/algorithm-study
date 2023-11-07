# Programmers
from collections import deque
def solution(cacheSize, cities):
    if cacheSize == 0 :
        return 5 * len(cities)
    
    cities = [i.lower() for i in cities]
    
    data = deque([])
    ans = 0
    for x in cities :     
        x = x.lower()
        if data and x in data :
            ans += 1
            data.remove(x)
            data.appendleft(x)
        else :
            if len(data) >= cacheSize :
                if data :
                    data.pop()
                data.appendleft(x)
            else :
                data.appendleft(x)
            ans += 5
    
    return ans