// [PRG] 의상

import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();
        
        for(int i = 0; i < clothes.length; i++) {
            map.put(clothes[i][1], map.getOrDefault(clothes[i][1], 0) + 1); 
        }
        
        int res = 1;
        for (String key : map.keySet()) {
            res *= (map.get(key) + 1);
        }
        
        res -= 1;
        
        return res;
    }
}