import java.util.*;

class Solution {
    class Point implements Comparable<Point>{
        int num;
        int cnt;
        
        public Point(int num, int cnt){
            this.num = num;
            this.cnt = cnt;
        }
        
        public int compareTo(Point o){
            return Integer.compare(this.cnt, o.cnt);
        }
    }
    PriorityQueue<Point> pq;
    int[] v;
    int answer;
    public int solution(int x, int y, int n) {
        answer = -1;
        pq = new PriorityQueue<>();
        v = new int[y+1];
        
        Arrays.fill(v,Integer.MAX_VALUE);
        
        pq.offer(new Point(x,0));
        v[x] = 0;
        
        dijk(y,n);
        
        
        return answer;
    }
    private void dijk(int y, int n){
        while(!pq.isEmpty()){
            Point cur = pq.poll();
            
            if(cur.num == y){
                answer = Math.min(cur.cnt,v[y]);
                
                return;
            }
            culcuration(cur, cur.num +n,y);
            culcuration(cur, cur.num *2,y);
            culcuration(cur, cur.num *3,y);
        }
    }
    private void culcuration(Point cur, int next, int y){
            if(next<=y && v[next]>cur.cnt+1){
                v[next] = cur.cnt+1;
                pq.offer(new Point(next,cur.cnt+1));
            }
    }
}
