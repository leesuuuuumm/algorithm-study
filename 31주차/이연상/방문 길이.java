// [PRG] 방문 길이

import java.util.*;

class Solution {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    
    public int solution(String dirs) {
        int answer = 0;
        int x = 5; int y = 5;
    
        boolean[][][] visit = new boolean[11][11][4];
        for(int i = 0; i < dirs.length(); i++) {
            char next = dirs.charAt(i);
            int d = 0;
            int dd = 0;
            if(next == 'R') {
                d += 0;
                dd += 1;
            }
            if(next == 'L') {
                d += 1;
                dd += 0;
            }
            if(next == 'U') {
                d += 2;
                dd += 3;
            }
            if(next == 'D') {
                d += 3;
                dd += 2;
            }
            
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(nx < 0 || ny < 0 || nx >= 11 || ny >= 11)
                continue;
            
            if(!visit[ny][nx][d]) {
                visit[ny][nx][d] = true;
                visit[y][x][dd] = true;
                answer += 1;
            }
            x = nx;
            y = ny;
        }
        
        return answer;
    }
}