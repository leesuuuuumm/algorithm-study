// [PRG] 압축

import java.util.*;

class Solution {
    public ArrayList<Integer> solution(String msg) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        HashMap<String, Integer> map = new HashMap<>();
        
        for(int i = 0; i < 26; i++) {
            map.put(Character.toString((char)(65 + i)), i + 1);
        }
        
        int idx = 1;
        String s = msg.substring(0, 1);
        while(idx < msg.length()) {
            
            String nextS = msg.substring(idx, idx + 1);
            if(!map.containsKey(s + nextS)) {
                map.put(s + nextS, map.size() + 1);
                answer.add(map.get(s));
                s = Character.toString(msg.charAt(idx));
            } else s += nextS;
            
            idx += 1;
        }
        if(map.containsKey(s)) answer.add(map.get(s));
        return answer;
    }
}