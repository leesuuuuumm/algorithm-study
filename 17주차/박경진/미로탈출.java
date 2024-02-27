import java.util.*;

class Solution {
    char[][] map;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int X, Y;
    
    
    class Pair {
        int x, y;
        int count;
        Pair(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    public int solution(String[] maps) {
        int answer = 0;
        X = maps.length;
        Y = maps[0].length();
        
        map = new char[X][Y];
        
        Pair start = null;
        Pair lever = null;
        Pair exit = null;
        
        for(int i = 0; i < X; i++) {            
            for(int j = 0; j < Y; j++) {
                map[i][j] = maps[i].charAt(j);
                if(maps[i].charAt(j) == 'S') start = new Pair(i, j, 0);
                if(maps[i].charAt(j) == 'L') lever = new Pair(i, j, 0);
                if(maps[i].charAt(j) == 'E') exit = new Pair(i, j, 0);
            }
        }
        
        
        int temp = bfs(start.x, start.y, lever.x, lever.y);
        if(temp == -1) return temp;
        else answer += temp;
        
        temp = bfs(lever.x, lever.y, exit.x, exit.y);
        if(temp == -1) return temp;
        else answer += temp;
    
        return answer;
    }
    
    
    public int bfs(int sx, int sy, int ex, int ey) {
        boolean[][] visit = new boolean[X][Y];
        
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(sx, sy, 0));
        visit[sx][sy] = true;
        
        while(!q.isEmpty()) {
            Pair p = q.poll();
            int x = p.x;
            int y = p.y;
            int cnt = p.count;
            if(x == ex && y == ey)
            {
                return cnt;
            }
            
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx < 0 || nx > X - 1 || ny < 0 || ny > Y - 1){
                    continue;
                }
                
                if(!visit[nx][ny] && map[nx][ny] != 'X') 
                {
                    q.add(new Pair(nx, ny, cnt + 1));
                    visit[nx][ny] = true;
                }
            }
        }
        return -1;
    }
}
