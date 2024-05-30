// [PRG] 야근 지수

import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < works.length; i++) {
            pq.add(works[i]);
        }
        
        while(n > 0) {
            if(pq.peek() == 0) {
                break;
            }
            pq.add(pq.poll() - 1);
            n -= 1;
        }
        
        while(!pq.isEmpty()) {
            long temp = pq.poll();
            answer += temp * temp;
        }
        
        return answer;
    }
}