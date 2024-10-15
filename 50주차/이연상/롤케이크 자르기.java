// [PRG] 롤케이크 자르기

import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        
        HashMap<Integer, Integer> suffix = new HashMap<>();
        for(int i = 0; i < topping.length; i++) {
            suffix.put(topping[i], suffix.getOrDefault(topping[i], 0) + 1);
        }
        
        HashMap<Integer, Integer> prefix = new HashMap<>();
        for(int i = 0; i < topping.length; i++) {
            int tp = topping[i];
            
            prefix.put(tp, prefix.getOrDefault(tp, 0) + 1);
            if(suffix.containsKey(tp)) {
                suffix.put(tp, suffix.get(tp) - 1);
                
                if(suffix.get(tp) == 0)
                    suffix.remove(tp);
            }
            
            if(prefix.size() == suffix.size())
                answer += 1;
        }
        
        return answer;
    }
}