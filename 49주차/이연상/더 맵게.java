// [PRG] 더 맵게

import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int n : scoville) {
            pq.add(n);
        }
        
        int temp = 0;
        while(pq.peek() < K) {
            
            if(pq.size() == 1) {
                return -1; 
            }
            
            temp = pq.poll() + (pq.poll() * 2);
            pq.add(temp);
            answer += 1;
        }
        
        
        return answer;
    }
}