# [PRG] H-Index

def solution(citations):
    
    result = []
    citations.sort()
    for i in range(len(citations)):
        if citations[i] >= len(citations) - i and citations[i] > i:
            return len(citations) - i
        
    return 0