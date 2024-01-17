import java.util.*;
class Solution {
    String[][] map;
    int answer;
    public int solution(String[] board) {
        answer = 1;
        map = new String[3][3];
        for(int i=0;i<3;i++){
            map[i] = board[i].split("");
        }
        
        int o = 0;
        int x = 0;
       
        for(int r=0;r<3;r++){
            for(int c=0;c<3;c++){
                if(map[r][c].equals("X")) x++;
                else if(map[r][c].equals("O")) o++;
            }
        }
        if(Math.abs(x-o) >1 || x>o) return 0;
        
        for(int r=0;r<3;r++){
            for(int c=0;c<3;c++){
                if(map[r][c].equals(".")) continue;
                String t = bfs(r,c,map[r][c]);
                if(t.equals("X") && (x+o == 9|| o>x)) return 0;
                if(t.equals("O") && x==o) return 0;
            }
        }
        System.out.println(answer);
        
        return answer;
    }
    int[] dr = {0,1,1,1};
    int[] dc = {1,1,0,-1};
    private String bfs(int r, int c, String s){
        e: for(int d=0;d<4;d++){
            int nr = r;
            int nc = c;
            
            for(int i=0;i<2;i++){
                nr +=dr[d];
                nc +=dc[d];
                
                if(!check(nr,nc) || !map[nr][nc].equals(s)) continue e;
            }
            return s;
        }
        return ".";
    }
    private boolean check(int nr, int nc){
        return nr>=0 && nr<3 && nc>=0 && nc<3;
    }

}
