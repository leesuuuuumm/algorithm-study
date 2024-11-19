// [PRG] 단속카메라

import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 1;
        
        Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]);
        int location = routes[0][1];
        
        for(int[] route : routes) {
            if(location < route[0]) {
                location = route[1];
                answer += 1;
            }
        }
        
        return answer;
    }
}