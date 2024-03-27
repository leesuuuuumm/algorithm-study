# [BOJ] 암호 만들기

from itertools import combinations

L, C = map(int, input().split())
alphabet = sorted(input().split())

for pw in combinations(alphabet, L):
    vowels = sum(c in 'aeiou' for c in pw)
    if 1 <= vowels <= L - 2:
        print(''.join(pw))