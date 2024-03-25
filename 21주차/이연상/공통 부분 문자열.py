# [BOJ] 공통 부분 문자열

A = input()
B = input()

arr = [ [0 for i in range(len(A))] for j in range(len(B)) ]

M = 0
for i in range(len(B)):
    al = B[i]
    for j in range(len(A)):
        if al == A[j]:
            if i > 0 and j > 0:
                arr[i][j] = arr[i-1][j-1] + 1
            else:
                arr[i][j] += 1
        M = max(arr[i][j], M)
print(M)