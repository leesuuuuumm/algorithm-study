import java.util.*;
class Solution {
    class Point implements Comparable<Point>{
        int idx;
        String j;
        int jcnt;
        int mycnt;
        
        public Point(int idx, String j, int jcnt, int mycnt){
            this.idx = idx;
            this.j = j;
            this.jcnt = jcnt;
            this.mycnt = mycnt;
        }
        
        public int compareTo(Point o){
            if(this.jcnt == o.jcnt){
                if(this.mycnt == o.mycnt){
                    return Integer.compare(this.idx, o.idx);
                }
                
                return Integer.compare(o.mycnt, this.mycnt);
            }
            
            return Integer.compare(o.jcnt, this.jcnt);
        }
    }
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0;i<genres.length;i++){
            map.put(genres[i],map.getOrDefault(genres[i],0)+plays[i]);
        }
        
        PriorityQueue<Point> pq = new PriorityQueue<>();
        
        for(int i=0;i<genres.length;i++){
            pq.offer(new Point(i,genres[i],map.get(genres[i]),plays[i]));
        }
        
        int cnt = 0;
        ArrayList<Integer> list = new ArrayList<>();
        while(!pq.isEmpty()){
            Point cur = pq.poll();
            list.add(cur.idx);
            boolean flag = false;
            while(!pq.isEmpty()){
                if(!pq.peek().j.equals(cur.j)){
                    break;
                }else{
                    if(!flag){
                        list.add(pq.poll().idx);
                        flag = true;
                    }else{
                        pq.poll();
                    }
                }
                
            }
        }
        int[] ans = new int[list.size()];
        
        for(int i=0;i<list.size();i++){
            ans[i] = list.get(i);
        }
        
        return ans;
    }
}
