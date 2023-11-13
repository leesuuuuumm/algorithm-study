import sys
si = sys.stdin.readline

s = si().rstrip()

arr = []
tmp = ""
for i in range(len(s)):
    if s[i] == '-':
        arr.append(tmp)
        tmp = s[i]
    else:
        tmp += s[i]

if tmp:
    arr.append(tmp)

ans = 0
for i in range(len(arr)):
    num = 0
    if arr[i][0] == '-':
        tmp = arr[i][1:].split('+')
        for i in tmp:
            num += int(i)
        ans -= num
    else:
        tmp = arr[i].split('+')
        for i in tmp:
            num += int(i)
        ans += num

print(ans)
