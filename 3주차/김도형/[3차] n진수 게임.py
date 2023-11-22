def solution(n, t, m, p):
    def solve(x, k):
        s = ""
        while x:
            tmp = str(x % k)

            if tmp == '10':
                s += 'A'
            elif tmp == '11':
                s += 'B'
            elif tmp == '12':
                s += 'C'
            elif tmp == '13':
                s += 'D'
            elif tmp == '14':
                s += 'E'
            elif tmp == '15':
                s += 'F'
            else:
                s += tmp
            x //= k

        return s[::-1]

    num = '0'
    for i in range(1, m * t):
        num += solve(i, n)

    return num[p-1::m][:t]
