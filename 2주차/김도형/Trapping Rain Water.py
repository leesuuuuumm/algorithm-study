class Solution:
    def trap(self, height: List[int]) -> int:
        if not height:
            return 0

        volume = 0
        left, right = 0, len(height)-1
        left_mx, right_mx = height[left], height[right]

        while left < right:
            left_mx, right_mx = max(height[left], left_mx), max(
                height[right], right_mx)

            if left_mx <= right_mx:
                volume += left_mx - height[left]
                left += 1
            else:
                volume += right_mx - height[right]
                right -= 1

        return volume
