import java.util.*;

class Solution {
    class Point{
        int r;
        int c;
        
        public Point(int r, int c){
            this.r = r;
            this.c =c;
        }
    }
    int R,C;
    int[][] map;
    boolean[][] v;
    public int[] solution(String[] maps) {
        int[] answer = {};
        ArrayList<Integer> list = new ArrayList<>();
        
        R = maps.length;
        C = maps[0].length();
        map = new int[R][C];
        v = new boolean[R][C];
        que = new LinkedList<>();
        for(int r=0;r<R;r++){
            String[] tmp = maps[r].split("");
            for(int c=0;c<C;c++){
                if(tmp[c].equals("X")){
                    map[r][c] = 0;
                }else{
                    map[r][c] = Integer.parseInt(tmp[c]);
                }
            }
        }
        
        int num = 0;
        for(int r=0;r<R;r++){
            for(int c=0;c<C;c++){
                if(!v[r][c] && map[r][c]!=0){
                    v[r][c] = true;
                    que.offer(new Point(r,c));
                    list.add(bfs());
                }
            }
        }
        Collections.sort(list);
        if(list.size() == 0){
            answer = new int[1];
            answer[0] = -1;
        }else{
            answer = new int[list.size()];
            int j = 0;
            for(Integer i: list){
                answer[j++] = i; 
            }
        }
        return answer;
    }
    
    Queue<Point> que;
    int[] dr = {0,1,-1,0};
    int[] dc = {1,0,0,-1};
    private int bfs(){
        int num = map[que.peek().r][que.peek().c];
        while(!que.isEmpty()){
            Point cur = que.poll();
            
            for(int d=0;d<4;d++){
                int nr = cur.r + dr[d];
                int nc = cur.c  +dc[d];
                
                if(!check(nr,nc) || v[nr][nc] || map[nr][nc] == 0) continue;
                
                v[nr][nc] = true;
                que.offer(new Point(nr,nc));
                num+=map[nr][nc];
            }
        }
        return num;
    }
    private boolean check(int nr, int nc){
        return nr>=0 && nr<R && nc>=0 && nc<C;
    }
}
