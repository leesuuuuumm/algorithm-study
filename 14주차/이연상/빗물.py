# [BOJ] 14719번_빗물

h, w = map(int, input().split())
nums = list(map(int, input().split()))

graph = [ [0 for j in range(500)] for i in range(500)]
for i in range(len(nums)):
    for j in range(nums[i]):
        graph[j][i] = 1

res = 0
for y in range(500):
    temp = False
    val = 0
    for x in range(500):
        
        if graph[y][x] == 1: # 1 만났을 때
            if temp == True:
                if val != 0:
                    res += val
                    val = 0
            else:
                temp = True

        else:  # 0 만났을 때
            if temp == True:
                val += 1

print(res)

        







                