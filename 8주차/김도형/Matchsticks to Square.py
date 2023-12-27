def makesquare(self, matchsticks: List[int]) -> bool:
    total = sum(matchsticks)
    if total % 4:
        return False

    reqLen = total // 4
    sides = [0] * 4
    matchsticks.sort(reverse=True)

    def recurse(i):
        if i == len(matchsticks):
            return True

        for j in range(4):
            if sides[j] + matchsticks[i] <= reqLen:
                sides[j] += matchsticks[i]

                if recurse(i + 1):
                    return True

                sides[j] -= matchsticks[i]
                if sides[j] == 0:
                    break

        return False
    return recurse(0)
