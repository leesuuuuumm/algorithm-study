// [PRG] 할인 행사

import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < discount.length; i++) {
            if(map.containsKey(discount[i])) {
                map.put(discount[i], map.get(discount[i]) + 1);    
            }
            else {
                map.put(discount[i], 1);
            }
            
            if(i >= 10) {
                if(map.containsKey(discount[i - 10])) {
                    if(map.get(discount[i - 10]) != 0)
                        map.put(discount[i - 10], map.get(discount[i - 10]) - 1);
                    else {
                        map.remove(discount[i - 10]);
                    }
                }
            }
            
            for(int j = 0; j < want.length; j++) {
                if(!map.containsKey(want[j]))
                    break;
                
                if(map.get(want[j]) < number[j])
                    break;
                
                if(j == want.length - 1)
                    answer += 1;
            }
        }
        
        return answer;
    }
}