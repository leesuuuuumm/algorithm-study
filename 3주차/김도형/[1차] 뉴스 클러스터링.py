from collections import defaultdict
import math


def solution(str1, str2):
    s1, s2 = str1.lower(), str2.lower()

    def calculate(data):
        arr = defaultdict(int)
        for i in range(len(data)-1):
            x, y = data[i], data[i+1]
            if not x.isalpha() or not y.isalpha():
                continue
            temp = x + y
            arr[temp] += 1
        return arr

    data1, data2 = calculate(s1), calculate(s2)
    if len(data1) == 0 and len(data2) == 0:
        return 65536

    sum_set, common_set = defaultdict(int), defaultdict(int)
    for k, _ in data1.items():
        if data1[k] >= 1 and data2[k] >= 1:
            common_set[k] += min(data1[k], data2[k])

    for k, _ in data1.items():
        sum_set[k] = max(data1[k], data2[k])
    for k, _ in data2.items():
        sum_set[k] = max(sum_set[k], data2[k])

    a, b = [], []
    for k, v in common_set.items():
        while v >= 1:
            a.append(k)
            v -= 1

    for k, v in sum_set.items():
        while v >= 1:
            b.append(k)
            v -= 1

    return math.floor(len(a)/len(b) * 65536)
