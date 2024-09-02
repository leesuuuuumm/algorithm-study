// [PRG] 캐시

import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        Deque<String> dq = new ArrayDeque<>();
        
        if (cacheSize == 0)
            return cities.length * 5;
        
        for(int i =0; i < cities.length; i++) {
            String now = cities[i].toLowerCase();
            if(dq.contains(now)) {
                dq.removeFirstOccurrence(now);
                dq.add(now);
                answer += 1;
                continue;
            }
            dq.add(now);
            if(dq.size() > cacheSize) {
                dq.pollFirst();
            }
            answer += 5;
        }
        
        return answer;
    }
}
