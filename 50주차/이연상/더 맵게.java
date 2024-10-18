// [PRG] 더 멥게

import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < scoville.length; i++) {
            pq.add(scoville[i]);
        }
        
        while(true) {
            int temp1 = pq.poll();
            if(temp1 >= K) {
                break;
            }
            
            if(pq.size() == 0) {
                return -1;
            }
            
            int temp2 = pq.poll();
            pq.add(temp1 + (temp2 * 2));

            answer += 1;
        }
    
        return answer;
    }
}