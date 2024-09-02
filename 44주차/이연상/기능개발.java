// [PRG] 기능개발

import java.util.*;

class Solution {
    public ArrayList<Integer> solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        int idx = 0;
        while(idx < progresses.length) {
            for(int i = idx; i < speeds.length; i++) {
                progresses[i] += speeds[i];
            }
            
            int temp = 0;
            for(int i = idx; i < speeds.length; i++) {
                if(progresses[i] >= 100) {
                    idx++;
                    temp++;
                }
                else
                    break;
            }
            if(temp != 0)
                answer.add(temp);

        }
        
        return answer;
    }
}