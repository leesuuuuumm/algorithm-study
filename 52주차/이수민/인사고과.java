import java.util.*;
class Solution {
    class Point implements Comparable<Point>{
        int a;
        int b;
        public Point(int a, int b){
            this.a = a;
            this.b = b;
        }
        
        public int compareTo(Point o){
            return Integer.compare(o.a+o.b, this.a+this.b);
        }
    }
    public int solution(int[][] scores) {
        int answer = 0;
        int ma=scores[0][0];
        int mb = scores[0][1];
        Arrays.sort(scores, new Comparator<int[]>(){
            
            public int compare(int[] o1, int[] o2){
                if(o1[0] == o2[0]) return Integer.compare(o1[1],o2[1]);
                return Integer.compare(o2[0], o1[0]);
            }
        });
     
        int bm = 0;
        PriorityQueue<Point> pq = new PriorityQueue<>();
        for(int i=0;i<scores.length;i++){
            if(scores[i][1]<bm) continue;
            if(scores[i][1]>bm){
                bm = scores[i][1];
            }
            pq.offer(new Point(scores[i][0],scores[i][1]));            
        }


        int ans = 0;
        while(!pq.isEmpty()){
            Point cur = pq.poll();
            int sum = cur.a+cur.b;
            int cnt = 1;
            if(cur.a == ma && cur.b == mb){
                return ans+1;
            }
            
            while(pq.size()>=1 && pq.peek().a+pq.peek().b == sum){
            
                if(pq.peek().a == ma && pq.peek().b == mb){
                    return ans+1;
                }
                pq.poll();
                cnt++;
            }
            ans+=cnt;
        }
        return -1;
    }
}
