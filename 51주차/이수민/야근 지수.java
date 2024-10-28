import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i=0;i<works.length;i++){
            pq.offer((long)works[i]);
        }
        while(!pq.isEmpty()&& n>0){
            long num = 1;
            if(n>pq.size())
                num = n/pq.size();
            
            long cur = pq.poll();
            if(cur-num>0){
                pq.offer(cur-num);
            }
            n-=num;
        }
        while(!pq.isEmpty()){
            answer+= (long)Math.pow(pq.poll(),2);
        }
        return answer;
    }
}
