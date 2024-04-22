import java.util.*;
class Solution {
    
    class Point implements Comparable<Point>{
        int r;
        int c;
        
        public Point(int r, int c){
            this.r  =r;
            this.c =c;
        }
        
        public int compareTo(Point o){
            return Integer.compare(this.c,o.c);
        }
    }
    public int solution(int[][] targets) {
        int answer = 1;
        PriorityQueue<Point> pq = new PriorityQueue<>();
        
        for(int r=0;r<targets.length;r++){
            pq.offer(new Point(targets[r][0],targets[r][1]));
        }
        int s =pq.peek().r;
        int e = pq.poll().c;
        while(!pq.isEmpty()){
            Point cur = pq.poll();
            
            if(e <= cur.r) {
                answer++;
                e = cur.c;
            }
            
        }
        return answer;
    }
}
