// [PRG] 게임 맵 최단거리

import java.util.*;

class Solution {
    
    private final static int[] dx = {0, 0, 1, -1};
    private final static int[] dy = {1, -1, 0, 0};
    private boolean[][] visit;
    
    public int solution(int[][] maps) {
        int answer = -1;
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        
        visit = new boolean[maps.length][maps[0].length];
        while(!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];
            
            visit[x][y] = true;
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx < 0 || ny < 0 || nx >= maps.length || ny >= maps[0].length)
                    continue;
                
                if(maps[nx][ny] == 0 || visit[nx][ny])
                    continue;
                
                visit[nx][ny] = true;
                maps[nx][ny] += maps[x][y];
                q.add(new int[]{nx, ny});
            }
        }
        
        answer = maps[maps.length-1][maps[0].length - 1];
        if(answer == 1) return -1;
        
        return answer;
    }
}