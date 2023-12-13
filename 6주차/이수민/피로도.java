import java.util.*;
class Solution {
    int[] input;
    boolean[] v;
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        
        input = new int[dungeons.length];
        v = new boolean[dungeons.length];
        max = 0;
        nPr(k, 0,dungeons);
        return max;
    }
    int max;
    private void nPr(int k,int cnt,int[][] dungeons){
        if(cnt == input.length){
            int tired = k;
            int count = 0;
            for(int i=0;i<input.length;i++){
                int idx = input[i];
                
                if(tired<dungeons[idx][0]) break;
                else{
                    tired-=dungeons[idx][1];
                    count++;
                }
            }
            max = Math.max(count,max);
            return;
        }
        for(int i=0;i<input.length;i++){
            if(v[i]) continue;
            
            v[i] = true;
            input[cnt] = i;
            nPr(k, cnt+1,dungeons);
            v[i] = false;
        }
        
    }
}
