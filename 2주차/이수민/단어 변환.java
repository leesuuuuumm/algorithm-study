import java.util.*;
class Solution {
    int answer;
    public int solution(String begin, String target, String[] words) {
        answer = Integer.MAX_VALUE;
        Arrays.sort(words);        
        v = new boolean[words.length];
      
        
        dfs(0, target, begin,words);
        if(answer == Integer.MAX_VALUE){
            return 0;
        }
        
        return answer;
    }
    static boolean[] v;
    private void dfs(int cnt, String target, String now, String[] words){
        for(int i=0;i<words.length;i++){
            String[] str = words[i].split("");
            String[] n = now.split("");
            if(!v[i]){
              int wc = 0; // 단어 개수 
                if(now.equals(target)){
                    answer = Math.min(cnt,answer);
                    return;
                }
                for(int j=0;j<str.length;j++){
                    if(!n[j].equals(str[j])){
                        wc++;
                    }
                }
                
                if(wc == 1){
                    v[i] = true;
                    dfs(cnt+1,target, words[i],words);
                    v[i] = false;
                }
            }
        }
    }
}
