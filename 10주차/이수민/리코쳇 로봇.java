import java.util.*;
class Solution {
    class Point{
        int r;
        int c;
        int dist;
        
        public Point(int r, int c, int dist){
            this.r  = r;
            this.c  = c;
            this.dist = dist;
        }
    }
    String[][] map;
    int[][] v;
    Queue<Point> que;
    public int solution(String[] board) {
        int answer = 0;
        map = new String[board.length][board[0].length()];
        v = new int[board.length][board[0].length()];
        for(int i=0;i<board.length;i++){
            map[i] = board[i].split("");
        }
        que = new LinkedList<>();
        
        for(int r=0;r<map.length;r++){
            for(int c=0;c<map[0].length;c++){
                if(map[r][c].equals("R")){
                    que.offer(new Point(r, c, 0));
                }
                v[r][c] = Integer.MAX_VALUE;
                
            }
        }
        
        return bfs();
    }
    int[] dr = {0,1,0,-1};
    int[] dc = {1,0,-1,0};
    private int  bfs(){
        while(!que.isEmpty()){
            Point cur = que.poll();
            if(map[cur.r][cur.c].equals("G")){
                return cur.dist;
            }
            
            for(int d=0;d<4;d++){
                int nr  =cur.r;
                int nc = cur.c;
                
                
                while(true){
                    nr+=dr[d];
                    nc+=dc[d];
                    
                    if(!check(nr,nc)|| map[nr][nc].equals("D")){
                        nr-=dr[d];
                        nc-=dc[d];
                        
                        if(v[nr][nc]>cur.dist+1){
                            v[nr][nc] = cur.dist+1;
                            que.offer(new Point(nr,nc,cur.dist+1));
                        }
                        break;
                    }
                    
                }
            }
        }
        return -1;       
    }
    private boolean check(int nr, int nc){
        return nr>=0 && nr<map.length && nc>=0 && nc<map[0].length;
    }
}
