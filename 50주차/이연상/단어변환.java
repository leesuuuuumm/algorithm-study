// [PRG] 단어변환

import java.util.*;

class Solution {
    
    static boolean[] visit;
    static int answer = 987654321;
    
    public int solution(String begin, String target, String[] words) {
        visit = new boolean[words.length];
        
        dfs(0, words, begin, target);
        
        if(answer == 987654321) return 0;
        return answer;
    }
    
    static void dfs(int depth, String[] words, String now, String target) {
        if(now.equals(target)) {
            answer = Math.min(answer, depth);
            return;
        }
        
        if(depth == words.length)
            return;
            
        for(int i = 0; i < words.length; i++) {
            if(visit[i])
                continue;
            
            int lenDiff = 0;
            for(int j = 0; j < words[i].length(); j++) {
                if(words[i].charAt(j) != now.charAt(j))
                    lenDiff += 1;
            }
            
            if(lenDiff == 1) {
                visit[i] = true; 
                dfs(depth + 1, words, words[i], target);
                visit[i] = false;
            }
        }
    }
}