def __init__(self):
        self.__visited = lambda x: not x 

def circularArrayLoop(self, nums: List[int]) -> bool:
        
        for i in range(len(nums)):
            if self.__visited(nums[i]):
                continue
            
            direction = nums[i] > 0
            
            slow = fast = i
            while not (self.__visited(nums[slow]) or self.__visited(nums[fast])):
                
                slow = self.__next(nums, slow, direction)
                fast = self.__next(nums, self.__next(nums, fast, direction), direction)
                
                if slow == -1 or fast == -1:
                    break
                
                elif slow == fast:
                    return True
            
            slow = i
            while self.__next(nums, slow, direction) != -1:
                nums[slow], slow = 0, self.__next(nums, slow, direction)
            
        return False
        
    def __next(self, nums, idx, direction):
        if idx == -1:
            return -1
                
        elif (nums[idx] > 0) != direction: 
            return -1
        
        next_idx = (idx + nums[idx]) % len(nums)
        if next_idx < 0:
            next_idx += len(nums)
        
        return -1 if next_idx == idx else next_idx
    			