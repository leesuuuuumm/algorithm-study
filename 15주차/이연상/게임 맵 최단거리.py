# [PRG] 게임 맵 최단거리

from collections import deque

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]
def bfs(maps, x, y):
    q = deque()
    q.append((x,y))
    while q:
        x, y = q.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            
            if(nx < 0 or nx >= len(maps) or ny < 0 or ny >= len(maps[0])):
                continue
    
    
            if(maps[nx][ny] == 1):
                maps[nx][ny] += maps[x][y]
                q.append((nx, ny))
        

def solution(maps):
    
    bfs(maps, 0, 0)
    answer = maps[len(maps) - 1][len(maps[0]) - 1]
    if answer == 1:
        answer = -1
    return answer