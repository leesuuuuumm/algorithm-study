// [PRG] 단어 변환

import java.util.*;

class Solution {
    public static int answer = Integer.MAX_VALUE;
    public boolean[] visit;
    
    public int solution(String begin, String target, String[] words) {
        visit = new boolean[words.length];
        dfs(words, target, begin, 0);
        if(answer == Integer.MAX_VALUE) return 0;
        return answer;
    }
    
    public void dfs(String[] words, String target, String s, int depth) {
        
        if(s.equals(target)) {
            answer = Math.min(answer, depth);
            return;
        }
        
        for(int i = 0; i < words.length; i++) {
            if(visit[i]) continue;
            
            int notMatch = 0;
            for(int j = 0; j < target.length(); j++) {
                if(s.charAt(j) != words[i].charAt(j))
                    notMatch += 1;
            }
            
            if(notMatch == 1) {
                visit[i] = true;
                dfs(words, target, words[i], depth + 1);
                visit[i] = false;
            }
        }
    }
}