class Solution:
    def rob(self, nums: List[int]) -> int:
        return max(nums[0], self.solve(nums[1:]), self.solve(nums[:-1]))

    def solve(self, nums):
        rob1, rob2 = 0, 0

        for n in nums:
            newRob = max(rob1+n, rob2)
            rob1 = rob2
            rob2 = newRob
        return rob2
