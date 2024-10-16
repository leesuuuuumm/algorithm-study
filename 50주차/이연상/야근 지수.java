// [PRG] 야근 지수

import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int work : works) {
            pq.add(work);
        }
        
        while(n != 0) {
            int temp = pq.poll();
            if(temp == 0) break;
            pq.add(temp - 1);
            n--;
        }
        
        while(!pq.isEmpty()) {
            int temp = pq.poll();
            answer += temp * temp;
        }
        
        return answer;
    }
}