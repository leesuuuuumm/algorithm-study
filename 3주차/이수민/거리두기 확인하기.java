import java.util.*;
class Solution {

    static class Point{
        int r;
        int c;
        public Point(int r,int c){
            this.r = r;
            this.c = c;
        }
    }
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        
        for(int i=0;i<5;i++){
            char[][] room = new char[5][5];
            boolean[][] v = new boolean[5][5];
            
                for(int rr=0;rr<5;rr++){
                    for(int rc =0;rc<5;rc++){
                        room[rr][rc]= places[i][rr].charAt(rc);
                        //System.out.print(room[rr][rc]);
                    }
                   // System.out.println();
                }
            if(bfs(room,v)){
                answer[i] = 1; // 거리두기 할 때
            }else{
                answer[i] = 0; // 거리두기 안할 때
            }
        }
        return answer;
    }
    private static int[] dr = {0,0,-1,1};
    private static int[] dc = {1,-1,0,0};
    private boolean bfs(char[][] room,boolean[][] v){
        
        Queue<Point> que = new LinkedList<Point>(); 
        for(int rr=0;rr<5;rr++){
            for(int rc =0;rc<5;rc++){
                if(room[rr][rc]=='P'&&!v[rr][rc]){
                   
                    v[rr][rc] = true;
                    que.offer(new Point(rr,rc));
                    while(!que.isEmpty()){
                        
                        Point cur = que.poll();
                        
                        for(int d =0;d<4;d++){
                            int nr = cur.r+dr[d];
                            int nc = cur.c + dc[d];
                            
                            if(!check(nr,nc)) continue;
                            
                            int distance = Math.abs(nr-rr)+Math.abs(nc-rc);
                               
                            //System.out.println(distance);
                
                
                            
                                if(room[nr][nc]=='P'&&distance<=2&&!v[nr][nc]){
                                    return false;
                                }else if(room[nr][nc]=='O'&&distance<2&&!v[nr][nc]){
                                    que.offer(new Point(nr,nc));
                                    v[nr][nc] = true;
                                }
                            }
                        
                        
                    }
                }
                        
                    }
                }
        return true;
    }
    private boolean check(int nr,int nc){
        return nr>=0 && nr<5 && nc>=0 && nc<5;
    }
}
