
s = input()

temp = 1
res = 0
st = []
for i in range(len(s)):
    if s[i] == '(':
        temp *= 2
        st.append(s[i])

    elif s[i] == '[':
        temp *= 3
        st.append(s[i])

    elif s[i] == ')':
        if not st or st[-1] != '(':
            res = 0
            break
        if s[i - 1] == '(':
            res += temp
        temp //= 2
        st.pop()
    
    elif s[i] == ']':
        if not st or st[-1] != '[':
            res = 0
            break
        if s[i-1] == '[':
            res += temp
        temp //= 3
        st.pop()

if st:
    print(0)
else:
    print(res)

    