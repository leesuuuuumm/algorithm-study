# [PRG] 괄호 회전하기

from collections import deque

def check(s):
    while True:
        if "()" in s: s = s.replace("()","")
        elif "{}" in s: s = s.replace("{}","")
        elif "[]" in s: s = s.replace("[]","")
        else: return False if s else True       

def solution(s):
    count = 0
    queue = deque(s)

    for i in range(len(s)):
        if check(''.join(queue)): count+=1
        queue.rotate(-1)
    return count