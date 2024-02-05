import sys

n = int(input())
nums = list(map(int, input().split()))
op = list(map(int, input().split()))

maxVal = -1e9
minVal = 1e9

def dfs(i, num):
    global maxVal, minVal

    if i == n:
        maxVal = max(num, maxVal)
        minVal = min(num, minVal)
        return
    
    else:
        if op[0] > 0:
            op[0] -= 1
            dfs(i + 1, num + nums[i])
            op[0] += 1

        if op[1] > 0:
            op[1] -= 1
            dfs(i + 1, num - nums[i])
            op[1] += 1

        if op[2] > 0:
            op[2] -= 1
            dfs(i + 1, num * nums[i])
            op[2] += 1

        if op[3] > 0:
            op[3] -= 1
            dfs(i + 1, int(num / nums[i]))
            op[3] += 1

dfs(1, nums[0])

print(maxVal) 
print(minVal) 

# max, min 함수의 매개변수 순서에 따라 값이 다르다.. 






