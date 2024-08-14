import java.util.*;

class Solution {
    class Point{
        int r;
        int c;
        
        public Point(int r, int c){
            this.r  =r;
            this.c  =c;
        }
    }
    int N,M;
    ArrayList<Integer> list;
    int[][] v;
    Queue<Point> que;
    public int solution(int[][] land) {
        int answer = 0;
        N = land.length;
        M = land[0].length;
        
        list = new ArrayList<>();
        
        v = new int[N][M];
        que = new LinkedList<>();
        
        int cnt = 0;
        for(int r=0;r<N;r++){
            for(int c=0;c<M;c++){
                if(v[r][c] == 0 && land[r][c] == 1){
                    v[r][c] = ++cnt;
                    que.offer(new Point(r,c));
                    list.add(bfs(land, cnt));
                }
            }
        }
        
      
        for(int c=0;c<M;c++){
            HashSet<Integer> set = new HashSet<>();
            for(int r=0;r<N;r++){
                if(v[r][c] ==0) continue;
                set.add(v[r][c]);
            }
            int sum = 0;
            for(Integer i: set){
                sum += list.get(i-1);
            }
            
            answer = Math.max(sum,answer);
        }
        
        return answer;
    }
    int[] dr = {0,1,-1,0};
    int[] dc = {1,0,0,-1};
    private int bfs(int[][] map, int cnt){
        int sum = 1;
        while(!que.isEmpty()){
            Point cur = que.poll();
            
            for(int d=0;d<4;d++){
                int nr = cur.r +dr[d];
                int nc = cur.c +dc[d];
                
                if(!check(nr,nc) || v[nr][nc] !=0 || map[nr][nc]==0) continue;
                
                v[nr][nc] = cnt;
                que.offer(new Point(nr,nc));
                sum++;
            }
        }
        return sum;
        
    }
    private boolean check(int nr, int nc){
        return nr>=0 && nr<N && nc>=0 && nc<M;
    }
}
