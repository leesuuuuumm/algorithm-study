// [PRG] 스킬트리

import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 1; i <= skill.length(); i++) {
            map.put(skill.charAt(i - 1), i);
        }
        
        for(int i = 0; i < skill_trees.length; i++) {
            int tempMax = 0;
            String sk = skill_trees[i];
            
            boolean isPossible = true;
            for(int j = 0; j < sk.length(); j++) {
                char c = sk.charAt(j); 
                if(map.containsKey(c)) {
                    if(map.get(c) - tempMax != 1) {
                        isPossible = false;
                        break;
                    }
                    tempMax = map.get(c);
                }
            }
            if(isPossible) answer += 1;
            
        }
        return answer;
    }
}