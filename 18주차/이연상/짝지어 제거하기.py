# [PRG] 짝지어 제거하기

def solution(s):
    
    stack = []
    for i in range(len(s)):
        if stack:
            if (stack[-1] == s[i]):
                stack.pop()
                continue
        stack.append(s[i])
    
    if stack: return 0
    return 1