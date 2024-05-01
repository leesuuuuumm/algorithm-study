// [PRG] 기능개발

import java.util.*;

class Solution {
    public ArrayList<Integer> solution(int[] progresses, int[] speeds) {    
        ArrayList<Integer> res = new ArrayList<>();
        
        int day = 1;
        int temp = 0;
        int i = 0;
        while(i < progresses.length) {
            while(day * speeds[i] + progresses[i] < 100) {
                day += 1;
            }
            
            while(i < progresses.length) {
                if(day * speeds[i] + progresses[i] >= 100) {
                    temp += 1;
                    i++;
                }
                else {
                    break;
                }
            }
            res.add(temp);
            temp = 0;
        }
        
        return res;
    }
}