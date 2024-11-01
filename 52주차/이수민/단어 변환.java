import java.util.*;
class Solution {
    int min;
    public int solution(String begin, String target, String[] words) {
        min = Integer.MAX_VALUE;
        boolean[] v = new boolean[words.length];
        
        dfs(begin,target,0, words,v);
        
        return min==Integer.MAX_VALUE?0:min;
    }
    private void dfs(String start, String target,int depth, String[] words, boolean[] v){
        if(target.equals(start)){
            min = Math.min(depth, min);
            return;
        }
        e:for(int i=0;i<words.length;i++){
            if(v[i]) continue;
            
            if(start.length() == words[i].length()){
                    boolean flag = false;
                for(int j=0;j<words[i].length();j++){
                    if(words[i].charAt(j) != start.charAt(j)){
                        if(!flag){
                            flag = true;
                        }
                       else  continue e;
                    }
                }
                v[i] = true;
                dfs(words[i], target,depth+1, words,v);
                v[i] = false;
            }
        }
    }
}
