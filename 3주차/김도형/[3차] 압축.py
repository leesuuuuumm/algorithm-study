from collections import defaultdict


def solution(msg):
    dic = defaultdict(int)
    lastIdx = 0
    for i in range(65, 91):
        lastIdx += 1
        dic[chr(i)] += lastIdx

    ans = []

    left, right = 0, 0
    val = 0
    while right <= len(msg):
        temp = msg[left:right+1]
        if temp in dic:
            val = dic[temp]
            right += 1
        else:
            ans.append(val)
            lastIdx += 1
            dic[temp] += lastIdx
            left = right
            val = 0
    if val:
        ans.append(val)

    return ans
