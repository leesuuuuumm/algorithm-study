# LeetCode
class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        nums.sort()

        data = []

        for s in range(len(nums)) :
            if s>0 and nums[s-1] == nums[s] : continue

            m,e = s+1,len(nums)-1
            
            while m < e :
                if nums[s] + nums[m] + nums[e] == 0 :

                    data.append((nums[s],nums[m],nums[e]))
                    m += 1
                    while nums[m-1] == nums[m] and m < e : 
                        m += 1
                
                elif nums[s] + nums[m] + nums[e] < 0 :
                    m += 1
                
                else :
                    e -= 1
        
        return data
