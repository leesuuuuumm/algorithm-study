import java.util.*;

class Solution {
    int[][][] map;
    int cr,cc;
    int dist;
    public int solution(String dirs) {
        char[] ch = dirs.toCharArray();
        map = new int[11][11][4];
        cr = 5;
        cc = 5;
        dist = 0;
        for(int i=0;i<ch.length;i++){
            simulation(ch[i]);
        }
        
        return dist;
    }
    
    // 0상 1하 2좌 3우
    int[] dr = {-1,1,0,0};
    int[] dc = {0,0,-1,1};
    private void simulation(char D){
        int d = 0;
        if(D == 'D') d= 1;
        else if(D == 'L') d = 2;
        else if(D == 'R') d = 3;
        
        int nr = cr + dr[d];
        int nc = cc + dc[d];
        
        if(!check(nr,nc)) return;
    
       
        if(map[nr][nc][d] == 0){
            map[nr][nc][d] = 1;
            if(d == 0 || d == 2) map[cr][cc][d+1] = 1;
            else if(d == 1 || d ==3) map[cr][cc][d-1] = 1;
            dist++;
        }
        cr = nr;
        cc = nc;
    }
    
    private boolean check(int nr, int nc){
        return nr>=0 && nr<11 && nc>=0 && nc<11;
    }
}
