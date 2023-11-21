import java.util.*;
class Solution {
    int[] ryan;
    int[] answer;
    int max;
    public int[] solution(int n, int[] info) {
        answer = new int[11];
        ryan = new int[11];
        max = 0;
        dfs(0,n,info);
        if(max == 0){
            answer = new int[1];
            answer[0] = -1;
        }
        return answer;
    }
  
    private void dfs(int idx, int arrows, int[] apeach){
        if(idx == 11 || arrows == 0){
            ryan[10]+= arrows;
            calcuration(ryan,apeach);
            ryan[10]-= arrows;
            return;
        }
        
        if(apeach[idx]<arrows){
            ryan[idx]+= apeach[idx]+1;
            arrows-= apeach[idx]+1;
            dfs(idx+1, arrows, apeach);
            arrows+=apeach[idx]+1;
            ryan[idx]-=apeach[idx]+1;
        }
       dfs(idx+1,arrows,apeach);
    }
  
    private void calcuration(int[] ryan, int[] apeach){
        int r = 0;
        int p = 0;
        for(int i=0;i<ryan.length;i++){
            if(ryan[i]!=0){
                r+= (10-i);
            }else if(apeach[i]!=0){
                p+= (10-i);
            }
        }
        if(max<r-p){           
            for(int i=0;i<ryan.length;i++){
                answer[i] = ryan[i];
            }
            max = r-p;
        }else if(max == r-p){
            for(int i=10;i>=0;i--){
                if(answer[i]<ryan[i]){
                    for(int j=0;j<ryan.length;j++){
                        answer[j] = ryan[j];
                    }
                    max = r-p;
                    break;
                }else if(answer[i]>ryan[i]){
                    break;
                }
            }
        }
    }
  
}
