import java.util.*;
class Solution {
    int[] selc;
    int ansP;
    int ansE;
    public int[] solution(int[][] users, int[] emoticons) {
        selc = new int[emoticons.length];
        int[] answer = new int[2];
        duplicateNPR(0,emoticons.length,users,emoticons);
        answer[0] = ansP;
        answer[1] = ansE;
        return answer;
    }
    
    
    private void duplicateNPR(int cnt, int N, int[][] users, int[] emoticons){
        if(cnt == N){
            int emo = 0;
            int plus = 0;
            int[] tmp = new int[users.length];
            e:for(int i=0;i<users.length;i++){
                for(int j=0;j<selc.length;j++){
                    if(users[i][0]<=(selc[j]+1)*10){
                        int price = (int) (emoticons[j]*((10 - (selc[j]+1))*0.1));
                        
                        if(tmp[i] +price >=users[i][1]){
                        plus++;
                        tmp[i] = 0;
                        continue e;
                    }
                        tmp[i]+=price;
                    }
                }
            }
            for(int i=0;i<tmp.length;i++){
                emo+=tmp[i];
            }
            
            if(ansP<plus){
                ansP = plus;
                ansE = emo;
            }else if(ansP == plus){
                if(ansE<emo){
                    ansE = emo;
                }
            }
            
            return;
        }
        
        for(int i=0;i<5;i++){
            selc[cnt] = i;
            duplicateNPR(cnt+1, N, users,emoticons);
        }
    }
}
