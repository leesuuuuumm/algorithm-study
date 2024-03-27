# [BOJ] 전화번호 수수께끼

t = int(input())
for i in range(t):
    s = input()
    m = {'Z': 0, 'G': 8, 'X': 6, 'W': 2, 'U': 4, 'F': 5, 'H': 3, 'I': 9, 'V': 7, 'O': 1}
    c = {m[k]: s.count(k) for k in 'ZGXWUHFIVO'}
    c[3] -= c[8]
    c[5] -= c[4]
    c[7] -= c[5]
    c[9] -= c[5] + c[6] + c[8]
    c[1] -= c[0] + c[2] + c[4]
    print(f"Case #{i+1}: {''.join(str(j) * c[j] for j in sorted(c.keys()))}")