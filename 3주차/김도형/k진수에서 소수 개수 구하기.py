import math


def solution(n, k):
    def solve(n, q):
        rev_base = ''

        while n > 0:
            n, mod = divmod(n, q)
            rev_base += str(mod)

        return rev_base[::-1]

    def is_prime(n):
        if n == 1:
            return False
        for i in range(2, int(math.sqrt(n))+1):
            if n % i == 0:
                return False

        return True

    s = solve(n, k)
    arr = []
    tmp = ''
    for i in range(len(s)):
        if s[i] == '0':
            if len(tmp) == 1 and tmp[-1] == '1':
                tmp = ''
                continue
            if tmp and is_prime(float(tmp)):
                arr.append(tmp)
            tmp = ''

        else:
            tmp += s[i]

    if tmp:
        if is_prime(float(tmp)):
            arr.append(tmp)

    return len(arr)
