class Solution:
    def canCompleteCircuit(self, gas: List[int], cost: List[int]) -> int:
        tmp = []
        ans_idx = 0
        
        for i in range(len(gas)) : 
            tmp.append(gas[i]-cost[i])
        # 모두 순환하지 못할 경우
        if sum(tmp) < 0 :
            return -1

        ans = [0] * (len(tmp) + 1)
        min_pos = ans[0]
        min_idx = 0
        for i in range(len(tmp)) :
            ans[i+1] = ans[i] + tmp[i]
            if min_pos > ans[i+1] :
                min_idx = i+1
                min_pos = min(min_pos, ans[i+1])

        return min_idx
        
                    
                    

            