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
    int[][] map;
    boolean[][] v;
    ArrayList<Integer> list;
    int R,C;
    public int solution(int[][] land) {
        int answer = 0;
        list = new ArrayList<>();
        R = land.length;
        C = land[0].length;
        v = new boolean[R][C];
        
        map = new int[R][C];
        list.add(-1);
        que = new LinkedList<>();
        int num = 0;
        for(int r=0;r<R;r++){
            for(int c=0; c<C; c++){
                if(!v[r][c] && land[r][c]==1){
                    num++;
                    v[r][c] = true;
                    que.offer(new Point(r,c));
                    map[r][c] = num;
                    bfs(land, num,1);
                }
            }
        }
        
        for(int c=0;c<C;c++){
            boolean[] ck = new boolean[list.size()+1];
            int ans = 0;
            for(int r=0;r<R;r++){
                if(map[r][c]!=0 && !ck[map[r][c]]){
                    ans +=list.get(map[r][c]);
                    ck[map[r][c]] = true;
                }
                
            }
            answer = Math.max(answer,ans);
        }

        return answer;
    }
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    private void bfs(int[][] land, int num, int cnt){
        while(!que.isEmpty()){
            Point cur = que.poll();
            
            for(int d=0;d<4;d++){
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                
                if(!check(nr,nc) || v[nr][nc] || land[nr][nc] ==0) continue;
                    map[nr][nc] = num;
                    v[nr][nc] = true;
                    que.offer(new Point(nr,nc));
                    cnt++;
            }
        }
        list.add(cnt);
    }
    private boolean check(int nr, int nc){
        return nr>=0 && nr<R && nc>=0 && nc<C;
    }
}
