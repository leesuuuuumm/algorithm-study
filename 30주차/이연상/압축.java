// [PRG] 압축

import java.util.*;

class Solution {
    public ArrayList<Integer> solution(String msg) {
        ArrayList<Integer> answer = new ArrayList();
        
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 1; i <= 26; i++) {
            map.put(Character.toString((char) (64 + i)), i);
        }
        
        String temp = "";
        int i = 0;
        while(i < msg.length()) {
            temp += msg.charAt(i);
            String ss = temp;
            for(int j = i + 1; j < msg.length(); j++) {
                ss += msg.charAt(j);
                if(!map.containsKey(ss)) {
                    break;
                }
                temp = ss;
                i++;
            }
            answer.add(map.get(temp));
            
            if(ss.length() != temp.length()) {
                map.put(ss, map.size() + 1);
                temp = "";
            }
            i++;
        }
        
        return answer;
    }
}