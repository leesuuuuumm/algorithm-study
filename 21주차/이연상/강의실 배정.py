# [BOJ] 강의실 배정

import heapq
import sys

heap = []
n = int(sys.stdin.readline())
arr = []

for i in range(n):
      a, b = map(int, sys.stdin.readline().split())
      arr.append([a, b])

arr.sort(key=lambda x: x[0])
print("arr : %s " % arr)

heapq.heappush(heap, arr[0][1])
print("heap : %s " % heap)

for i in range(1, n):
      if heap[0] > arr[i][0]:
            heapq.heappush(heap, arr[i][1])
            print("heap : %s " % heap)
      else:
            heapq.heappop(heap)
            print("heap : %s " % heap)
            
            heapq.heappush(heap, arr[i][1])
            print("heap : %s " % heap)

print(len(heap))