# [PRG] 캐시

def solution(cacheSize, cities):
    answer = 0

    cache = []
    if cacheSize == 0:
        return len(cities) * 5
    
    for city in cities:
        city = city.upper()
        if city in cache:
            cache.pop(cache.index(city))
            cache.append(city)
            answer += 1 
        else:
            if len(cache) == cacheSize:
                cache.pop(0)
            cache.append(city)
            answer += 5
            
    return answer