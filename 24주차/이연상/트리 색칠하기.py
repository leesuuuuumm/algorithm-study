# [BOJ] 트리 색칠하기

import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)
def solution():
    n = int(input())

    dp = [[0] * 16 for i in range(n)]
    
    visit = [False for i in range(n)] 
    
    tree = [[] for i in range(n)]
    for i in range(n - 1):
        a, b = map(int, input().split())
        tree[a-1].append(b-1)
        tree[b-1].append(a-1)
	
    def dfs(here):
        visit[here] = True
        

        for there in tree[here]:
            if not visit[there]:
                dfs(there) 
                
                for pre_color in range(16): 
                    m_num = 100000000
                    for color in range(16):
                        if pre_color != color:
                            if m_num > dp[there][color]: 
                                m_num = dp[there][color] 
                    dp[here][pre_color] += m_num 

        for color in range(16):
            dp[here][color] += color +1
        return

    dfs(0)
    return min(dp[0])

print(solution())