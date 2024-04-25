import java.util.*;
class Solution {
    class Point{
        int r;
        int c;
        int k;
        StringBuilder sb;
        
        public Point(int r, int c, int k , StringBuilder sb){
            this.r= r;
            this.c =c;
            this.k =k;
            this.sb = sb;
        }
    }
    Queue<Point> que;
    int[] dr = {1,0,0,-1};
    int[] dc = {0,-1,1,0};
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        int dist = Math.abs(x-r) + Math.abs(y-c);
        if((k-dist) %2 ==1 || dist>k) return "impossible";
        
       
        que = new LinkedList<>();
        que.offer(new Point(x-1,y-1,0,new StringBuilder()));
        
        while(!que.isEmpty()){
            Point cur = que.poll();
            if(cur.r == r-1 &&cur.c == c-1 && (k-cur.k)%2==1) return "impossible";
            
            if(cur.k==k &&cur.r == r-1 && cur.c==c-1){
                    return cur.sb.toString();
            }
            
            
            for(int d=0;d<4;d++){
                int nr = cur.r +dr[d];
                int nc = cur.c +dc[d];

                dist = Math.abs(nr-(r-1)) + Math.abs(nc-(c-1));
                
                if(!check(n,m,nr,nc) || cur.k+1+dist>k) continue;
                String s = "";
                if(d == 0) s="d";
                else if(d == 1) s="l";
                else if(d == 2) s="r";
                else if(d == 3) s ="u";
                StringBuilder sb=  new StringBuilder();
                sb.append(cur.sb).append(s);
                
                que.offer(new Point(nr,nc,cur.k+1,sb));
                break;
            }
        }
        
        return answer;
    }
    
    private boolean check(int n,int m, int nr, int nc){
        return nr>=0 && nr<n && nc>=0 && nc<m;
    }
}
