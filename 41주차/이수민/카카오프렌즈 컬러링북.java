import java.util.*;

class Solution {
    class Point{
        int r;
        int c;
        
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    Queue<Point> que;
    
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        v = new boolean[m][n];
        que = new LinkedList<>();
        
        for(int r=0;r<m;r++){
            for(int c=0;c<n;c++){
                if(!v[r][c] && picture[r][c]!=0){
                    v[r][c] = true;
                    numberOfArea++;
                    que.offer(new Point(r,c));
                   maxSizeOfOneArea = Math.max(bfs(m,n,picture),maxSizeOfOneArea);
                }
                
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    static int[] dr = {0,-1,0,1};
    static int[] dc = {1,0,-1,0};
    boolean[][] v;
    private int bfs(int m, int n, int[][] picture){
        int cnt = 1;
        while(!que.isEmpty()){
            Point cur = que.poll();
            
            for(int d=0;d<4;d++){
                int nr = cur.r +dr[d];
                int nc = cur.c + dc[d];
                
                if(!check(m, n, nr,nc) || v[nr][nc] || picture[nr][nc] != picture[cur.r][cur.c]) continue;
                
                v[nr][nc] = true;
                que.offer(new Point(nr,nc));
                cnt++;
             }
        }
        return cnt;
    }
    private boolean check(int m, int n, int nr, int nc){
        return nr>=0 && nr<m && nc>=0 && nc<n;
    }
}
