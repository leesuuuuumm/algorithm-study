class Solution:
    def minimumRounds(self, tasks: List[int]) -> int:
        tasks.sort()
        dic = collections.defaultdict(int)
        for i in tasks:
            dic[i] += 1
        days = 0
        for k, v in dic.items():
            if v == 1:
                return -1
            days += v // 3
            if v % 3 > 0:
                days += 1
        return days
